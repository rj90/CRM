/**
 * Created by Rav on 2016-03-21.
 */
angular.module('crmApp.productEdit', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/products/productEdit?:productId', {
            templateUrl: 'app/views/products/edit/productEdit.html',
            controller: 'ProductEditCtrl'
        });
    }).controller('ProductEditCtrl', function($scope, $rootScope, $http, $translate, $window, $location, $timeout) {
        var init = function(){
            $http.get('products?id=' + $location.search().productId).then(function (response) {
                $scope.productVO = response.data;
            });
        }

        init();

        $scope.goToPreviousState = function () {
            $location.path('products/productList');
        };

        $scope.update = function () {
            $http.put('products', $scope.productVO).then(function (response) {

                if(typeof $rootScope.message === 'undefined'){
                    $rootScope.message = {};
                }
                $rootScope.message.products = $translate.instant('products.message.update');

                $scope.goToPreviousState();
            });
        };
    })