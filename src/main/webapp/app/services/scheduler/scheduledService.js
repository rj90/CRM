/**
 * Created by rjozwiak on 2016-04-20.
 */
angular.module('ScheduledServices', ['ngResource'])
    .factory('ScheduledService', function($http) {
        var service = {};

        service.loadTasks = function (callback) {
            return $http.get(APP_CONTEXT + 'scheduler').then(function (response) {
                callback(response);
            });
        };
        
        service.runTask = function (task, callback) {
            return $http.patch(APP_CONTEXT + 'scheduler?jobName=' + task).then(function (response) {
                callback(response);
            });
        };

        return service;
    });