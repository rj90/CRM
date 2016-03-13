/**
 * Created by Rav on 2016-02-02.
 */

'use strict';
angular.module('crmApp.login', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'app/views/login/login.html',
            controller: 'LoginCtrl'
        });
    })

    .controller('LoginCtrl', function($scope, $rootScope, $cookieStore, $location, UserService, DBService, usSpinnerService){
        $scope.rememberMe = false;
        $rootScope.isAuthenticated = false;

        DBService.getQueriesType(function(response){
            console.log(response.data)
            $scope.dbConnOptions = response.data;
            $scope.dbConnType = $scope.dbConnOptions[0];
        });

        $scope.login = function() {
            usSpinnerService.spin('login-spinner');
            //console.log($scope.username + $scope.password);
            $cookieStore.put('dbConnType', $scope.dbConnType.id);
            UserService.authenticate({username: $scope.username, password: $scope.password}, function(authenticationResult) {

                if(authenticationResult.status == 401){ // Invalid login or password
                    usSpinnerService.stop('login-spinner');
                    $rootScope.loginErr = true;
                    return;
                }

                var authToken = authenticationResult.data.token;
                $rootScope.authToken = authToken;
                $cookieStore.put('authToken', authToken);
                UserService.getUser(function(response) {
                    usSpinnerService.stop('login-spinner');
                    $rootScope.user = response.data;
                    $rootScope.isAuthenticated = true;
                    $rootScope.appInfo = response.data.appInfo;
                    $location.path($rootScope.homePath);
                });
            });
        };
    });