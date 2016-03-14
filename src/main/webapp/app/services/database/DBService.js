/**
 * Created by rjozwiak on 2016-02-03.
 */
angular.module('DBServices', ['ngResource'])
    .factory('DBService', function($http) {
        var service = {};

        service.getQueriesType = function(callback){
            return $http.get(APP_CONTEXT + "DBTypeController/getDBQueriesType").then(function(response){
                callback(response);
            })
        };

        return service;
    });