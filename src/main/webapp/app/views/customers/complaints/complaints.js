/**
 * Created by Rav on 2016-02-22.
 */
angular.module('crmApp.complaints', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/complaints', {
            templateUrl: 'app/views/customers/complaints/complaints.html',
            controller: 'ComplaintsCrtl'
        });
    }).controller('ComplaintsCrtl', function($scope){

});