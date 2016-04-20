/**
 * Created by rjozwiak on 2016-03-20.
 */
angular.module('crmApp.input', [])
    .directive('inputDctv', function() {
        return {
            restrict: "E",
            templateUrl: "app/directives/input/input-tpl.html",
            transclude: true,
            scope: {
                label: "@",
                type: "@",
                required: "=",
                model: "=",
                errorMsg: "="
            }
        }
    })