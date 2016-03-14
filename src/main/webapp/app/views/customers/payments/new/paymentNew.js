/**
 * Created by rjozwiak on 2016-02-29.
 */
angular.module('crmApp.paymentNew', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/newPayment', {
            templateUrl: 'app/views/customers/contracts/new/paymentNew.html',
            controller: 'PaymentNewCtrl'
        });
    }).controller('PaymentNewCtrl', function($scope){

});