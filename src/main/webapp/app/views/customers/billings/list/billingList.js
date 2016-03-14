/**
 * Created by rjozwiak on 2016-02-22.
 */
angular.module('crmApp.billingList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/billingList', {
            templateUrl: 'app/views/customers/billings/list/billingList.html',
            controller: 'BillingListCtrl'
        });
    }).controller('BillingListCtrl', function($scope){

    });