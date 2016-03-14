/**
 * Created by rjozwiak on 2016-02-02.
 */
angular.module('crmApp.menu', [])
    .directive('menuDctv', function ($http){
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: "app/directives/main/main-tpl.html",
            controller: function ($scope, $location) {
                $scope.redirect = function(){
                    $location.path("/login");
                }
            }
        }
    })