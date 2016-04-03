/**
 * Created by rjozwiak on 2016-02-03.
 */
angular.module('DBServices', ['ngResource'])
    .factory('DBService', function($http) {
        var service = {};

        service.getQueriesType = function(callback){
            return $http.get(APP_CONTEXT + "sql/getTypes").then(function(response){
                callback(response);
            })
        };

        service.update = function(callback){
            return $http.patch(APP_CONTEXT + "sql/updateDatabase").then(function(response){
                callback(response);
            })
        };

        service.generateSQL = function(callback){
            return $http.put(APP_CONTEXT + "sql/generateSQL").then(function(response){
                callback(response);
            })
        };

        service.removeAllChangeLogs = function(callback){
            return $http.delete(APP_CONTEXT + "sql/removeAllChangeLogs").then(function(response){
                callback(response);
            })
        };

        service.loadQueries = function(callback){
            return $http.get(APP_CONTEXT + "sql/loadQueries").then(function(response){
                callback(response);
            })
        };

        service.getQueriesTypeForTest = function(callback){
            return $http.get(APP_CONTEXT + "sql/getTypesForTests").then(function(response){
                callback(response);
            })
        };

        service.loadTables = function(callback){
            return $http.get(APP_CONTEXT + "sql/loadTables").then(function(response){
                callback(response);
            })
        };

        service.testSelect = function(options, callback){
            return $http.post(APP_CONTEXT + "testDatabase/select", options).then(function(response){
                callback(response);
            })
        };

        service.testInsert = function(options, callback){
            return $http.post(APP_CONTEXT + "testDatabase/insert", options).then(function(response){
                callback(response);
            })
        };

        service.testUpdate = function(options, callback){
            return $http.put(APP_CONTEXT + "testDatabase/update", options).then(function(response){
                callback(response);
            })
        };

        service.testDelete = function(options, callback){
            return $http.put(APP_CONTEXT + "testDatabase/delete", options).then(function(response){
                callback(response);
            })
        };

        return service;
    });