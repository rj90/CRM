/**
 * Created by rjozwiak on 2016-03-21.
 */
angular.module('crmApp.productNew', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/products/newProduct', {
            templateUrl: 'app/views/products/new/productNew.html',
            controller: 'ProductNewCtrl'
        });
    }).controller('ProductNewCtrl', function($scope, $rootScope, $http, $translate, $window, $location, $routeParams, $timeout) {

        $scope.goToPreviousState = function () {
            $location.path('products/productList');
        };
    })