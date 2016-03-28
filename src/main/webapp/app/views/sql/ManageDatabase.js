/**
 * Created by Rav on 2016-03-29.
 */
'use strict';
angular.module('crmApp.databaseManager', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/manageDatabase', {
            templateUrl: 'app/views/sql/databaseManager.html',
            controller: 'DatabaseManager'
        });
    })
    .controller('DatabaseManager', function($scope, DBService){
        $scope.update = function(){
            DBService.update(function(response){
                }
            )
        };
        $scope.generateSQL = function(){
            DBService.generateSQL(function(response){
                }
            )
        };
        $scope.removeAllChangeLogs = function(){
            DBService.removeAllChangeLogs(function(response){
                }
            )
        };
    });
