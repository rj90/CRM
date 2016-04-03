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
    .controller('DatabaseManager', function($scope, $translate, $filter, DBService){
        $scope.dbActions = [
            {id: 1, name: $translate.instant('database.removeAllChangeLogs')},
            {id: 2, name: $translate.instant('database.generate')},
            {id: 3, name: $translate.instant('database.update')}
        ];
        $scope.dbAction = $scope.dbActions[0];

        $scope.logsVisible = true;
        $scope.logs = '';

        var update = function(callback){
            DBService.update(function(response){
                    callback(response);
                }
            )
        };
        var generateSQL = function(callback){
            DBService.generateSQL(function(response){
                    callback(response);
                }
            )
        };
        var removeAllChangeLogs = function(callback){
            DBService.removeAllChangeLogs(function(response){
                    callback(response);
                }
            )
        };

        var manageResponse = function(response){
            if(response.data !== undefined || response.data != null){
                $scope.logs += '<div class="row">' +
                    '<label class="col-md-2">'
                    + $filter('date')(response.data.date,'yyyy-MM-dd HH:mm:ss') +
                    '</label>' +
                    '<label class="col-md-10">' + response.data.log + '</label>' +
                    '</div>\n';
            }
        };

        $scope.doAction = function(){
            if($scope.dbAction.id === 1){
                removeAllChangeLogs(manageResponse);
            }
            if($scope.dbAction.id === 2){
                generateSQL(manageResponse);
            }
            if($scope.dbAction.id === 3){
                update(manageResponse);
            }
        }

        $scope.clearLog = function(){
            $scope.logs = '';
        };
    });
