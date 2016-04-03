/**
 * Created by Rav on 2016-04-03.
 */
angular.module('DashboardServices', ['ngResource'])
    .factory('DashboardSrv', function($http) {
        var service = {};

        service.getContractorsPerUser = function(name, callback){
            return $http.get(APP_CONTEXT + "dashboard/getContractorsPerUser?name="+name).then(function(response){
                callback(response);
            })
        };

        service.getContractorsPerMonthChart = function(callback){
            return $http.get(APP_CONTEXT + "dashboard/getContractorsPerMonthChart").then(function(response){
                callback(response);
            })
        };

        service.getContractorsPerMonthTable = function(callback){
            return $http.get(APP_CONTEXT + "dashboard/getContractorsPerMonthTable").then(function(response){
                callback(response);
            })
        };

        return service;
    });