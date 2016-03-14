/**
 * Created by rjozwiak on 2016-02-22.
 */
angular.module('crmApp.contractList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/customers/contractList', {
            templateUrl: 'app/views/customers/contracts/list/contractList.html',
            controller: 'ContractListCtrl'
        });
    }).controller('ContractListCtrl', function($scope, $rootScope, $http, $translate, $window, $state, $timeout){
    $scope.filter = {};

    $scope.$watch(function () {
        return $window.innerWidth;
    }, function () {
        var newHeight = $window.innerHeight - 284;
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

    $scope.reloadFilterView = function () {
        $scope.isFilterCollapsed = !$scope.isFilterCollapsed;
        var newHeight;
        if($scope.isFilterCollapsed){
            newHeight = $window.innerHeight - 284;
        }
        else{
            newHeight = $window.innerHeight - 430;
        }
        angular.element(document.getElementsByClassName('grid')[0]).css('height', newHeight + 'px');
    };

    $scope.loadContracts = function () {
        var filter = prepareFilter();

        console.log(filter);

        $http.post('contracts', filter).then(function (response) {
            $scope.contractsGrid.data = response.data.content;
            $scope.contractsGrid.totalItems = response.data.totalElements;
        });
    };

    $scope.loadStatuses = function() {
        $http.get('contracts/getStatuses').then(function (response) {
            $scope.statuses = response.data;
        });
    };

    $scope.clearFilter = function () {
        $scope.filter = {};

        $scope.searchContracts();
    };

    $scope.searchContracts = function () {
        if (!validateFilter()) {
            pagingOptions.pageNumber = 1;
            $scope.contractsGrid.paginationCurrentPage = 1;

            $scope.loadContracts();
        }
    };

    $scope.validateNumber = function (input) {
        var patternNumber = /^\d+$/;

        if (input && !patternNumber.test(input)) return false;
        else return true;
    }

    var validateFilter = function () {
        var errorFlag = false;
        if (!$scope.validateNumber($scope.filter.id)) errorFlag = true;
        //if (!$scope.validateNumber($scope.filter.discount)) errorFlag = true;
        if (!$scope.validateNumber($scope.filter.limit)) errorFlag = true;

        return errorFlag;
    }

    $scope.contractsGrid = {
        data: [],
        enableHorizontalScrollbar: 0,
        enableColumnResizing: true,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        useExternalPagination: true,
        useExternalSorting: false,
        paginationPageSizes: [25, 50, 75],
        paginationPageSize: 25,
        pagingCurrentPage: 1,
        columnDefs: [
            {
                field: 'id',
                displayName: $translate.instant('contracts.table.id'),
                width: '5%',
                type: 'number'
            },
            {
                field: 'customer',
                displayName: $translate.instant('contracts.table.customer'),
                width: '20%'
            },
            {
                field: 'issueDate',
                displayName: $translate.instant('contracts.table.issueDate'),
                width: '20%',
                type: 'date',
                cellFilter: 'date:\'yyyy-MM-dd\''
            },
            {
                field: 'startDate',
                displayName: $translate.instant('contracts.table.startDate'),
                width: '20%',
                type: 'date',
                cellFilter: 'date:\'yyyy-MM-dd\''
            },
            {
                field: 'endDate',
                displayName: $translate.instant('contracts.table.endDate'),
                width: '20%',
                type: 'date',
                cellFilter: 'date:\'yyyy-MM-dd\''
            },
            {
                field: 'status',
                displayName: $translate.instant('contracts.table.status'),
                width: '20%'
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
                $scope.gridApi.selection.selectRow($scope.contractsGrid.data[0]);
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