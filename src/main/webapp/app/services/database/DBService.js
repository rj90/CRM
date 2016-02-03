/**
 * Created by Rav on 2016-02-03.
 */
angular.module('DBServices', ['ngResource'])
    .factory('DBService', function($http) {
        var service = {};

        service.getQueriesType = function(callback){
            return $http.get("http://localhost:8080" + "/rest/getDBQueriesType").then(function(response){
                callback(response);
            })
        };

        return service;
    });