/**
 * Created by Rav on 2016-01-23.
 */
angular.module('UserServices', ['ngResource'])
    .factory('UserService', function($http) {
        var service = {};

        service.authenticate = function(userAuthenticationVO, callback) {
            return $http.post(APP_CONTEXT + "auth/authenticateUser", userAuthenticationVO).then(function(response){
                callback(response);
            });
        };

        service.getUser = function(callback){
            return $http.get(APP_CONTEXT + "auth/getUser").then(function(response){
                callback(response);
            })
        };

        return service;
    });