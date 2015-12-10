angular.module('crmApp', [
    'ngRoute',
    'ngCookies',
    'ui.bootstrap',
    'crmApp.contact',
    'crmApp.directives',
    'crmApp.views',
    'crmApp.services',
    'crmApp.request',
    'crmApp.modal',
    'crmApp.filters'
]).
    config( function($routeProvider, $resourceProvider, $httpProvider) {
        $routeProvider.otherwise({redirectTo: '/view1'});
        $resourceProvider.defaults.stripTrailingSlashes = false;
        $httpProvider.defaults.withCredentials = true;
    })
    .run(function ($rootScope, commonSrv, $cookieStore) {
        $rootScope.common = commonSrv;
        if($cookieStore.get('logged')){
            $rootScope.common.access = true;
            $rootScope.common.user = $cookieStore.get('logged');
        }
        $rootScope._ = _;
    })