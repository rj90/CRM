/**
 * Created by rjozwiak on 2016-02-29.
 */
angular.module('crmApp.complaintNew', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/newComplaint', {
            templateUrl: 'app/views/customers/complaints/new/complaintNew.html',
            controller: 'ComplaintNewCtrl'
        });
    }).controller('ComplaintNewCtrl', function($scope){

});