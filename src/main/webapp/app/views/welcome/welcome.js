/**
 * Created by Rav on 2016-02-07.
 */
angular.module('crmApp.welcome', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/welcomePage', {
            templateUrl: 'app/views/welcome/welcome.html'
        });
    })