/**
 * Created by Rav on 2016-04-02.
 */
'use strict';
angular.module('crmApp.databaseTester', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/testDatabase', {
            templateUrl: 'app/views/test/databaseTester.html',
            controller: 'DatabaseTester'
        });
    }).controller('DatabaseTester', function($scope, DBService, ChartJs) {
        $scope.isChartHidden = true;
        $scope.queriesCount = 1000;
        $scope.step = 50;
        // $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
        // $scope.series = ['Series A', 'Series B'];
        // $scope.data = [
        //     [65, 59, 80, 81, 56, 55, 40],
        //     [28, 48, 40, 19, 86, 27, 90]
        // ];
        // $scope.onClick = function (points, evt) {
        //     console.log(points, evt);
        // };

        $scope.loadQueries = function () {
            DBService.loadQueries(function (response) {
                console.log(response.data);
                $scope.queries = response.data;
            });
        };

        $scope.loadTestedTypes = function () {
            DBService.getQueriesTypeForTest(function (response) {
                $scope.testedTypes = response.data;
            });
        };

        $scope.loadTables = function () {
            DBService.loadTables(function (response) {
                $scope.tables = response.data;
            });
        };

        $scope.clearChart = function () {
            $scope.labels = [];
            $scope.series = [];
            $scope.data = [];
            $scope.isChartHidden = true;
        };

        $scope.runTest = function () {
            clearChart();
            var parameters = {
                "step": $scope.step,
                "numberOfQueries": $scope.queriesCount,
                "options": $scope.testedType,
                "serviceType": $scope.table
            };
            switch ($scope.query) {
                case "1":
                    DBService.testSelect(parameters, manageResponse);
                    break;
                case "2":
                    DBService.testInsert(parameters, manageResponse);
                    break;
                case "3":
                    DBService.testUpdate(parameters, manageResponse);
                    break;
                case "4":
                    DBService.testDelete(parameters, manageResponse);
                    break;
            }

        };

        var manageResponse = function (response) {
            clearChart('resultChart');
            var data = response.data;
            data.forEach(function (entry) {
                $scope.series.push(entry.type);
                var chartData = [];
                entry.times.forEach(function (time) {
                    chartData.push(time.value);
                })
                $scope.data.push(chartData);
            });
            data[0].times.forEach(function (label) {
                $scope.labels.push(label.key);
            });
            $scope.isChartHidden = false;
        };

    var clearChart = function (elementId) {
        $scope.clearChart();
        if (document.getElementById(elementId)) {
            var charts = ChartJs.Chart.instances; // Get all chart instances
            for (var key in charts){ // loop looking for the chart you want to remove
                    if (!charts.hasOwnProperty(key)){
                        continue;
                    }
                    var chartAux = ChartJs.Chart.instances[key];
                    if (chartAux.chart.ctx.canvas.id === elementId){
                        // Remove chart-legend before destroying the chart
                        var parent = chartAux.chart.ctx.canvas.parentElement;
                        var legend = chartAux.chart.ctx.canvas.nextElementSibling;
                        parent.removeChild(legend);
                        // Compare id with elementId passed by and if it is the one
                        // you want to remove just call the destroy function
                        ChartJs.Chart.instances[key].destroy();
                    }
                }
            }
        }
    }
);