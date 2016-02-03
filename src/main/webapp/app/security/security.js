/**
 * Created by Rav on 2016-02-03.
 */
var UTIL = (function () {

    var getFormattedDate = function (date) {
        var time = date || (new Date()).getTime();
        return (new Date(time)).toLocaleString().slice(0, 10);
    };

    var getFormattedMoney = function (date) {
        return date != null ? date.toFixed(2) : "";
    };

    var getFormattedAmount = function (date) {
        return date != null ? date.toFixed(3) : "";
    };

    var getShortDateFormatPattern = function () {
        return "dd.MM.yyyy";
    };

    // ui grid utils
    var uigrid = (function () {

        var getDateCellFilter = function () {
            return 'date:"' + getShortDateFormatPattern() + '"';
        };

        return {
            getDateCellFilter: getDateCellFilter
        }

    })();

    var LoaderQueue = (function () {

        var num = 0;

        var push = function () {
            num++;
            if (num > 0) NProgress.start();
        };

        var pop = function () {
            if (num > 0) num--;
            if (num == 0) NProgress.done();
        };

        return {
            push: push,
            pop: pop
        }
    })();


    return {
        getFormattedDate: getFormattedDate,
        getFormattedMoney: getFormattedMoney,
        getFormattedAmount: getFormattedAmount,
        getShortDateFormatPattern: getShortDateFormatPattern,
        uigrid: uigrid,
        LoaderQueue: LoaderQueue
    }

})();

var SecurityConfig = {
    /* When set to false a query parameter is used to pass on the auth token.
     * This might be desirable if headers don't work correctly in some
     * environments and is still secure when using https. */
    useAuthTokenHeader: true
};

angular.module('crmApp.security',[])
    .config(function($httpProvider) {

        /* Register error provider that shows message on failed requests or redirects to login page on
         * unauthenticated requests */
        $httpProvider.interceptors.push([ '$q', '$rootScope', '$location',  '$injector', function ($q, $rootScope, $location,  $injector) {
                return {
                    'response' : function(response){
                        UTIL.LoaderQueue.pop();
                        return response;
                    },
                    'responseError': function(rejection) {
                        UTIL.LoaderQueue.pop();
                        var status = rejection.status;
                        var config = rejection.config;
                        var method = config.method;
                        var url = config.url;

                        if(status == 0){ // NO CONNECTION TO SERWER
                            var $dialogs = $injector.get('dialogs');
                            var dlg = $dialogs.error('Błąd', "Brak połączenia z serwerem");
                        }
                        if(status == 500){ // SERVER INTERNAL ERROR
                            var $dialogs = $injector.get('dialogs');
                            var dlg = $dialogs.error('Wewnętrzny błąd serwera', rejection.data.detailMessage);
                            return rejection;
                        }
                        if(status == 409){ // Data integrity violation
                            var $dialogs = $injector.get('dialogs');
                            var dlg = $dialogs.error('Wewnętrzny błąd serwera', rejection.data.detailMessage);
                            return rejection;
                        }
                        if(status == 403){ // FORBIDDEN
                            var $dialogs = $injector.get('dialogs');
                            var dlg = $dialogs.error('Dostęp', rejection.data.detailMessage);
                            return rejection;
                        }
                        if(status == 404){ // NOT FOUND
                            var $dialogs = $injector.get('dialogs');
                            var dlg = $dialogs.error('Nie odnaleźiono strony na serwerze', 'Nie dostępny link ' + rejection.config.url);
                            return rejection;
                        }
                        if (status == 401 ) { // PROBLEM WITH AUTHENTICATION
                            if($location.path() == '/login') // Invalid login or password
                                return rejection;
                            else //
                                $rootScope.logout();
                        }

                        return $q.reject(rejection);
                    }
                };
            }]
        );

        /* Registers auth token interceptor, auth token is either passed by header or by query parameter
         * as soon as there is an authenticated user */
        $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
                return {
                    'request': function(config) {
                        UTIL.LoaderQueue.push();
                        var isRestCall = true;
                        if (isRestCall && angular.isDefined($rootScope.authToken)) {

                            var authToken = $rootScope.authToken;
                            if (SecurityConfig.useAuthTokenHeader)
                                config.headers['X-Auth-Token'] = authToken;
                            else
                                config.url = config.url + "?token=" + authToken;

                        }

                        return config || $q.when(config);
                    }
                };
            }
        );

    })
    .run(function($rootScope, $location, $cookieStore, UserService) {


    $rootScope.homePath = 'welcomePage';
    /* Reset error when a new view is loaded */
    $rootScope.$on('$viewContentLoaded', function() {
        delete $rootScope.error;
        $rootScope.loginErr = false;
    });

    // check if user is login in on view changes
    $rootScope.$on('$stateChangeStart', function (event, toState) {
        if (!$rootScope.user && toState.name != 'login') {
            event.preventDefault();
            $location.path("/login");
        }
    });

    $rootScope.hasRole = function(role) {

        if ($rootScope.user === undefined) {
            return false;
        }

        if ($rootScope.user.roles[role] === undefined) {
            return false;
        }

        return $rootScope.user.roles[role];
    };

    $rootScope.logout = function() {
        delete $rootScope.user;
        delete $rootScope.authToken;
        $rootScope.loginErr = false;
        $rootScope.isAutenticated = false;
        $cookieStore.remove('authToken');
        $location.path("/login");
    };

    /* Try getting valid user from cookie or go to login page */

    var originalPath = $location.path();

    $location.path("/login");

    var authToken = $cookieStore.get('authToken');

    if (authToken !== undefined) {
        $rootScope.authToken = authToken;
        UserService.getUser(function(response) {
            if(response.status == 200){
                console.log(response.data);
                $rootScope.user = response.data;
                $rootScope.isAutenticated = true;
                $rootScope.appInfo = response.data.appInfo;


                if(originalPath.trim().length == 0 || originalPath.trim() == '/')
                    $location.path($rootScope.homePath);
                else
                    $location.path(originalPath);
            }else{
                $rootScope.logout();
            }
        });


    }

    $rootScope.initialized = true;
});
//