angular.module('crmApp', [
    'angularSpinner',
    'ngCookies',
    'ngMaterial',
    'ngMessages',
    'ngMdIcons',
    'ngRoute',
    'ngSanitize',
    'pascalprecht.translate',
    'ui.router',
    'ui.bootstrap',
    'ui.grid',
    'ui.grid.edit',
    'ui.grid.pagination',
    'ui.grid.selection',
    'ui.grid.autoResize',
    'ui.grid.resizeColumns',
    'ui.grid.edit',
    'ui.grid.cellNav',
    'ui.grid.moveColumns',

    'crmApp.directives',
    'crmApp.views',
    'crmApp.security',
    'crmApp.services'
]).config(function($translateProvider, $urlRouterProvider){
    $translateProvider.useUrlLoader(APP_CONTEXT + 'i18n/currentLanguage');
    $translateProvider.preferredLanguage('pl-PL');
    $translateProvider.fallbackLanguage('pl');
    $translateProvider.useSanitizeValueStrategy('sanitizeParameters');
    //$urlRouterProvider.otherwise('/');
}).run(function($rootScope, i18nService){
    $rootScope.locale = 'pl';
    i18nService.setCurrentLang($rootScope.locale);
});