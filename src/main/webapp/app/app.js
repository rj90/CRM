angular.module('crmApp', [
    'ngCookies',
    'ngRoute',
    'ngSanitize',
    'pascalprecht.translate',
    'ui.bootstrap',

    'crmApp.directives',
    'crmApp.views',
    'crmApp.security',
    'crmApp.services'
]).config(['$translateProvider', function($translateProvider){
    $translateProvider.useUrlLoader(APP_CONTEXT + '/i18n/messageBundle');
    $translateProvider.preferredLanguage('pl');
    $translateProvider.fallbackLanguage('pl');
    $translateProvider.useSanitizeValueStrategy('sanitize');
}]);