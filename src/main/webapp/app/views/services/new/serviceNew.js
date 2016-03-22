/**
 * Created by Rav on 2016-03-21.
 */
angular.module('crmApp.serviceNew', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/services/newService', {
            templateUrl: 'app/views/services/new/serviceNew.html',
            controller: 'ServiceNewCtrl'
        });
    }).controller('ServiceNewCtrl', function($scope, $rootScope, $http, $translate, $window, $location, $routeParams, $timeout) {

        $scope.goToPreviousState = function () {
            $location.path('services/serviceList');
        };
    
    $scope.loadStatuses = function () {
        $http.get('services/types').then(function (response) {
            $scope.serviceTypes = response.data;
        });
    }
    })