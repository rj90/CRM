/**
 * Created by Rav on 2016-02-02.
 */

'use strict';
angular.module('crmApp.login', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'app/views/login/login.html',
            controller: 'LoginCrtl'
        });
    })

    .controller('LoginCrtl', function($scope, $rootScope, $cookieStore, UserService){
        $scope.rememberMe = false;
        $rootScope.isAutenticated = false;

        $scope.login = function() {
            UserService.authenticate({username: $scope.username, password: $scope.password}, function(authenticationResult) {

                if(authenticationResult.status == 401){ // Invalid login or password
                    $rootScope.loginErr = true;
                    return;
                }

                var authToken = authenticationResult.data.token;
                console.log(authToken);

                $rootScope.authToken = authToken;
                $cookieStore.put('authToken', authToken);
                UserService.getUser(function(response) {
                    $rootScope.user = response.data;
                    $rootScope.isAutenticated = true;
                    $rootScope.appInfo = response.data.appInfo;
                    //$location.path($rootScope.homePath);
                });
            });
        };
    });