angular.module('crmApp', [
    'ngCookies',
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
    'ui.grid.edit',
    'ui.grid.cellNav',
    'ui.grid.moveColumns',

    //'dialogs.main',
    //'dialogs.default-translations',

    'crmApp.directives',
    'crmApp.views',
    'crmApp.security',
    'crmApp.services'
]).config(function($translateProvider, $urlRouterProvider){
    $translateProvider.useUrlLoader(APP_CONTEXT + '/i18n/messageBundle');
    $translateProvider.preferredLanguage('pl');
    $translateProvider.fallbackLanguage('pl');
    $translateProvider.useSanitizeValueStrategy('sanitizeParameters');
    //$urlRouterProvider.otherwise('/');
}).run(function($rootScope, i18nService){
    $rootScope.locale = 'pl';
    i18nService.setCurrentLang($rootScope.locale);
});