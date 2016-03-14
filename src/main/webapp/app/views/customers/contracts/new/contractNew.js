/**
 * Created by rjozwiak on 2016-02-25.
 */
angular.module('crmApp.contractNew', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/newContract', {
            templateUrl: 'app/views/customers/contracts/new/contractNew.html',
            controller: 'ContractNewCtrl'
        });
    }).controller('ContractNewCtrl', function($scope){

});