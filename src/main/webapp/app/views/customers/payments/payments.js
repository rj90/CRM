/**
 * Created by Rav on 2016-02-22.
 */
angular.module('crmApp.payments', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/payments', {
            templateUrl: 'app/views/customers/payments/payments.html',
            controller: 'PaymentsCrtl'
        });
    }).controller('PaymentsCrtl', function($scope){

});