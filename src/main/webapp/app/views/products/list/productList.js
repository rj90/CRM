/**
 * Created by Rav on 2016-02-25.
 */
angular.module('crmApp.productList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/products/productList', {
            templateUrl: 'app/views/products/list/productList.html',
            controller: 'ProductListCtrl'
        });
    }).controller('ProductListCtrl', function($scope, $rootScope, $http, $translate, $window, $state, $timeout) {
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

    $scope.loadProducts = function () {
        var filter = prepareFilter();

        console.log(filter);

        $http.post('products', filter).then(function (response) {
            $scope.productsGrid.data = response.data.content;
            $scope.productsGrid.totalItems = response.data.totalElements;
        });
    };

    $scope.loadStatuses = function() {
        $http.get('products/getStatuses').then(function (response) {
            $scope.statuses = response.data;
        });
    };

    $scope.clearFilter = function () {
        $scope.filter = {};

        $scope.searchProducts();
    };

    $scope.searchProducts = function () {
        if (!validateFilter()) {
            pagingOptions.pageNumber = 1;
            $scope.productsGrid.paginationCurrentPage = 1;

            $scope.loadProducts();
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

    $scope.productsGrid = {
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
                displayName: $translate.instant('products.table.id'),
                width: '5%',
                type: 'number'
            },
            {
                field: 'code',
                displayName: $translate.instant('products.table.code'),
                width: '20%'
            },
            {
                field: 'name',
                displayName: $translate.instant('products.table.name'),
                width: '20%',
            },
            {
                field: 'desc',
                displayName: $translate.instant('products.table.desc'),
                width: '20%',
            }
        ],

        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;

            gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                pagingOptions.pageNumber = newPage;
                pagingOptions.pageSize = pageSize;
                $scope.loadProducts();
            });

            gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                $scope.gridApi.selection.selectRow($scope.productsGrid.data[0]);
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

        $scope.loadProducts();
    }

    init();
});