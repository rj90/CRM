/**
 * Created by Rav on 2016-02-29.
 */
angular.module('crmApp.paymentList', ['ngRoute'])
    .config(function($routeProvider) {
    $routeProvider.when('/customers/paymentList', {
        templateUrl: 'app/views/customers/payments/list/paymentList.html',
        controller: 'PaymentListCtrl'
    });
})
    .controller('PaymentListCtrl', function($scope, $translate){
    $scope.paymentsGrid = {
        data: [],
        enableHorizontalScrollbar: 0,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        useExternalPagination: true,
        useExternalSorting: false,
        paginationPageSizes: [25, 50, 75],
        paginationPageSize: 25,
        pagingCurrentPage: 1,
        enableGridMenu: true,
        columnDefs: [
            {
                field: 'id',
                displayName: $translate.instant('payments.table.id'),
                width: '5%',
                type: 'number'
            },
            {
                field: 'customer',
                displayName: $translate.instant('payments.table.customer'),
                width: '25%'
            },
            {
                field: 'startDate',
                displayName: $translate.instant('payments.table.startDate'),
                width: '25%',
                type: 'date',
                cellFilter: 'date:\'yyyy-MM-dd\''
            },
            {
                field: 'endDate',
                displayName: $translate.instant('payments.table.endDate'),
                width: '25%',
                type: 'date',
                cellFilter: 'date:\'yyyy-MM-dd\''
            },
            {
                field: 'amount',
                displayName: $translate.instant('payments.table.amount'),
                width: '20%'
            },
            {
                field: 'paymentDate',
                displayName: $translate.instant('payments.table.paymentDate'),
                width: '25%',
                type: 'date',
                cellFilter: 'date:\'yyyy-MM-dd\''
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
    }
});