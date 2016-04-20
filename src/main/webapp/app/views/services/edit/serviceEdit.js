/**
 * Created by rjozwiak on 2016-03-21.
 */
angular.module('crmApp.serviceEdit', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/services/serviceEdit?:serviceId', {
            templateUrl: 'app/views/services/edit/serviceEdit.html',
            controller: 'ServiceEditCtrl'
        });
    }).controller('ServiceEditCtrl', function($scope, $rootScope, $http, $translate, $window, $location, $timeout) {
        $scope.goToPreviousState = function () {
            $location.path('services/serviceList');
        };

        $scope.update = function () {
            $http.put('services', $scope.serviceVO).then(function (response) {

                if(typeof $rootScope.message === 'undefined'){
                    $rootScope.message = {};
                }
                $rootScope.message.services = $translate.instant('services.message.update');

                $scope.goToPreviousState();
            });
        };

    $scope.loadStatuses = function () {
        $http.get('services/types').then(function (response) {
            $scope.serviceTypes = response.data;
        });
    }

    var init = function(){
        $http.get('services/types').then(function (respData) {
            $scope.serviceTypes = respData.data;
            $http.get('services?id=' + $location.search().serviceId).then(function (response) {
                $scope.serviceVO = response.data;
                $scope.servicetype = $scope.serviceVO.type;
                // for(i = 0; i < $scope.serviceTypes.length; i++){
                //     if($scope.serviceTypes[i].id === $scope.serviceVO.type.id)
                //         $scope.type = $scope.serviceTypes[i];
                // }
            });
        });
    }

    init();
})