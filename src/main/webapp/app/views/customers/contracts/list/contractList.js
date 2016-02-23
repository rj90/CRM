/**
 * Created by Rav on 2016-02-22.
 */
angular.module('crmApp.contractList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/contractList', {
            templateUrl: 'app/views/customers/contracts/list/contractList.html',
            controller: 'ContractListCrtl'
        });
    }).controller('ContractListCrtl', function($scope, $rootScope, $http, $translate, $window, $state, $timeout){
    $scope.filter = {};

    $scope.$watch(function () {
        return $window.innerWidth;
    }, function () {
        var newHeight = $window.innerHeight - 330;
        angular.element(document.getElementsByClassName('grid')[0]).css('height', newHeight + 'px');
    });

    var prepareFilter = function () {
        $scope.filter.pageNumber = pagingOptions.pageNumber;
        $scope.filter.pageSize = pagingOptions.pageSize;
        $scope.filter.sortType = pagingOptions.sort;

        var filter = _.clone($scope.filter)

        _.forOwn($scope.filter, function (value, key) {
            if (!value) delete filter[key];
        });

        return filter;
    };

    $scope.loadContracts = function () {
        var filter = prepareFilter();

        console.log(filter);

        $http.post('contracts', filter).then(function (response) {
            $scope.contractsGrid.data = response.data.content;
            $scope.contractsGrid.totalItems = response.data.totalElements;
        });
    };

    $scope.contractsGrid = {
        data: [],
        enableHorizontalScrollbar: 0,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        useExternalPagination: true,
        useExternalSorting: false,
        paginationPageSizes: [20, 50, 75],
        paginationPageSize: 50,
        pagingCurrentPage: 1,
        enableGridMenu: true,
        columnDefs: [
            {
                field: 'id',
                displayName: $translate.instant('contracts.table.id'),
                width: '100%'
            }
        ],

        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;

            gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                pagingOptions.pageNumber = newPage;
                pagingOptions.pageSize = pageSize;
                $scope.loadContracts();
            });

            gridApi.selection.on.rowSelectionChanged($scope, function (row) {

            });

            gridApi.core.on.sortChanged($scope, function (grid, sortColumns) {
                /*if (sortColumns.length == 0) {
                 pagingOptions.sort = null;
                 } else {
                 pagingOptions.sort = {
                 dir: sortColumns[0].sort.direction,
                 name: sortColumns[0].name
                 };
                 }
                 $scope.searchTable();*/
            });
        }
    };
    var pagingOptions = {
        pageNumber: 1,
        pageSize: 50,
        sort: {name: 'id', dir: 'asc'}
    };

    var init = function () {

        $scope.loadContracts();
    }

    init();
});