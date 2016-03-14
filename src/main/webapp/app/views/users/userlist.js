/**
 * Created by rjozwiak on 2016-02-08.
 */
'use strict';
angular.module('crmApp.userlist', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/userlist', {
            templateUrl: 'app/views/users/userlist.html',
            controller: 'UserListCtrl'
        });
    })
    .controller('UserListCtrl', function($scope){

    });
