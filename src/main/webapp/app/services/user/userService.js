/**
 * Created by Rav on 2016-01-23.
 */
var userServices = angular.module('UserServices', ['ngResource']);

userServices.factory('UserService', function($http) {
    var service = {};

    service.authenticate = function(userAuthenticationVO, callback) {
        return $http.post(APP_CONTEXT + "auth/authenticateUser", userAuthenticationVO).then(function(response){
            callback(response);
        });
    };

    return service;
});