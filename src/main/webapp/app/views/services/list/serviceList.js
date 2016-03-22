/**
 * Created by rjozwiak on 2016-02-25.
 */
angular.module('crmApp.serviceList', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider.when('/services/serviceList', {
            templateUrl: 'app/views/services/list/serviceList.html',
            controller: 'ServiceListCtrl'
        });
    }).controller('ServiceListCtrl', function($scope, $rootScope, $http, $translate, $window, $location, $mdDialog) {
    $scope.filter = {};

    $scope.$watch(function () {
        return $window.innerWidth;
    }, function () {
        var newHeight = $window.innerHeight - 284;
        angular.element(document.getElementsByClassName('grid')[0]).css('height', newHeight + 'px');
    });


    $scope.closeMessage = function () {
        if (!_.isUndefined($rootScope.message)) {
            $rootScope.message.services = '';
        }
    }

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

    $scope.loadServices = function () {
        var filter = prepareFilter();

        console.log(filter);

        $http.post('services', filter).then(function (response) {
            $scope.servicesGrid.data = response.data.content;
            $scope.servicesGrid.totalItems = response.data.totalElements;
        });
    };

    $scope.clearFilter = function () {
        $scope.filter = {};

        $scope.searchServices();
    };

    $scope.searchServices = function () {
        if (!validateFilter()) {
            pagingOptions.pageNumber = 1;
            $scope.servicesGrid.paginationCurrentPage = 1;

            $scope.loadServices();
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

    $scope.servicesGrid = {
        data: [],
        enableHorizontalScrollbar: 2,
        enableVerticalScrollbar: 2,
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
                displayName: $translate.instant('services.table.id'),
                width: '5%',
                type: 'number'
            },
            {
                field: 'code',
                displayName: $translate.instant('services.table.code'),
                width: '20%'
            },
            {
                field: 'name',
                displayName: $translate.instant('services.table.name'),
                width: '20%',
            },
            {
                field: 'desc',
                displayName: $translate.instant('services.table.desc'),
                width: '20%',
            },
            {
                name: "actions",
                displayName: '',
                cellTemplate: '<div class="ui-grid-cell-btn-contents">' +
                '<button type="button" class="btn btn-sm btn-list item-view" ng-click="grid.appScope.editService(row.entity)"><i class="fa fa-pencil"></i></button>' +
                '<button type="button" class="btn btn-sm btn-list item-view" ng-click="grid.appScope.removeService(row.entity)"><i class="fa fa-close"></i></button>' +
                '</div>',
                width: '4%',
                enableSorting: false,
                enableHiding: false,
                enableColumnMenu: false,
                enableColumnResizing: false
            }
        ],

        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;

            gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                pagingOptions.pageNumber = newPage;
                pagingOptions.pageSize = pageSize;
                $scope.loadServices();
            });

            gridApi.selection.on.rowSelectionChanged($scope, function (row) {
                $scope.gridApi.selection.selectRow($scope.servicesGrid.data[0]);
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

    $scope.editService = function (service) {
        $scope.closeMessage();
        $rootScope.filterCache.services.selectedId = service.id;
        $location.path('services/serviceEdit').search({serviceId: service.id});
    };

    $scope.removeService = function (service) {
        $scope.closeMessage();
        $mdDialog.show($mdDialog.confirm().title($translate.instant('service.remove.title')).textContent($translate.instant('service.remove.question')).ok($translate.instant('common.dialog.yes'))
            .cancel($translate.instant('common.dialog.no'))).then(function () {
            $http.delete('services?id=' + service.id).then(function (response) {
                $scope.loadServices();
                $mdDialog.show($mdDialog.alert().title($translate.instant('common.deleted')).textContent($translate.instant('services.deleted') + ' service.code').ok($translate.instant('error.close')));
            });
        }, function(){

        });
    };

    var getServicesCache = function () {
        if (_.isUndefined($rootScope.filterCache)) {
            $rootScope.filterCache = {
                services: {
                    filter: $scope.filter
                }
            }
        } else if (_.isUndefined($rootScope.filterCache.services)) {
            $rootScope.filterCache.services = {
                filter: $scope.filter
            };
        }

        return $rootScope.filterCache.services;
    };

    var pagingOptions = {
        pageNumber: 1,
        pageSize: 50,
        sort: {name: 'id', dir: 'asc'}
    };

    var init = function () {
        var serviceCache = getServicesCache();
        $scope.filter = serviceCache.filter;

        $scope.loadServices();
    }

    init();
});