/**
 * Created by Rav on 2016-02-25.
 */
angular.module('crmApp.productList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/products/productList', {
            templateUrl: 'app/views/products/list/productList.html',
            controller: 'ProductListCtrl'
        });
    }).controller('ProductListCtrl', function($scope, $rootScope, $http, $translate, $window, $state, $timeout) {

});