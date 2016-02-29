/**
 * Created by Rav on 2016-02-25.
 */
angular.module('crmApp.serviceList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/services/serviceList', {
            templateUrl: 'app/views/services/list/serviceList.html',
            controller: 'ServiceListCtrl'
        });
    }).controller('ServiceListCtrl', function($scope, $rootScope, $http, $translate, $window, $state, $timeout) {

    });