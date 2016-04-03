/**
 * Created by Rav on 2016-04-03.
 */
angular.module('crmApp.dashboard', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/dashboard', {
            templateUrl: 'app/views/dashboard/dashboard.html',
            controller: 'DashboardCtrl'
        });
    }).controller('DashboardCtrl', function($scope, $rootScope, $window, DashboardSrv) {
    $scope.$watch(function () {
        return $window.innerWidth;
    }, function () {
        var newHeight = $window.innerHeight - 64;
        angular.element(document.getElementById('main-div')).css('height', newHeight + 'px');
    });
    
    DashboardSrv.getContractorsPerUser($rootScope.user.name, function(response){
        $scope.contractCountLabels = response.data.contractorLabels;
        $scope.contractCountLabels = response.data.contractorLabels;
        $scope.contractCountData = response.data.contractorCount;
        $scope.contractAmountData = response.data.contractorAmount;
    });

    DashboardSrv.getContractorsPerMonthChart(function(response){
        $scope.contractPerMonthLabels = response.data.contractorLabels;
        $scope.contractPerMonthData = response.data.contractorAmount;
    });

    })