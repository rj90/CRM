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

    .controller('LoginCrtl', function($scope){

    });