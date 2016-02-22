/**
 * Created by Rav on 2016-02-22.
 */
angular.module('crmApp.billingList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/billingList', {
            templateUrl: 'app/views/customers/billings/list/billingList.html',
            controller: 'BillingListCrtl'
        });
    }).controller('BillingListCrtl', function($scope){

    });