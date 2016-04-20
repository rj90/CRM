/**
 * Created by rjozwiak on 2016-04-20.
 */
'use strict';
angular.module('crmApp.scheduler', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/scheduler', {
            templateUrl: 'app/views/scheduler/scheduler.html',
            controller: 'Scheduler'
        });
    }).controller('Scheduler', function($scope, $translate, $mdDialog, ScheduledService) {
        var loadTasks = function(){
            ScheduledService.loadTasks(function(response){
                $scope.tasks = response.data;
                if($scope.tasks.length>0){
                    $scope.task = $scope.tasks[0];
                }
            })
        };
    
        $scope.runTask = function () {
            if($scope.task !== undefined){
                ScheduledService.runTask($scope.task, function (response) {
                    if(response.status == 200) {
                        $mdDialog.show($mdDialog.alert().title($translate.instant('scheduled.complete')).textContent($translate.instant('scheduled.completeMessage')).ok($translate.instant('scheduled.ok')));
                    }
                });
            }
            else{
                $mdDialog.show($mdDialog.alert().title($translate.instant('scheduled.taskNotChoosen')).textContent($translate.instant('scheduled.taskNotChoosenMessage')).ok($translate.instant('scheduled.ok')));
            }
        };

        loadTasks();
        
    }
);