/**
 * Created by Rav on 2016-02-29.
 */
angular.module('crmApp.complaints', ['ngRoute'])
.config(function($routeProvider) {
    $routeProvider.when('/customers/complaintList', {
        templateUrl: 'app/views/customers/complaints/list/complaintList.html',
        controller: 'ComplaintListCtrl'
    });
}).controller('ComplaintListCtrl', function($scope){

});