/**
 * Created by Rav on 2016-02-22.
 */
angular.module('crmApp.contractList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/contractList', {
            templateUrl: 'app/views/customers/contracts/list/contractList.html',
            controller: 'ContractListCrtl'
        });
    }).controller('ContractListCrtl', function($scope){

});