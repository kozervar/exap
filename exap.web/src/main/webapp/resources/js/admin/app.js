'use strict';


// Declare app level module which depends on filters, and services
var routeProvider;
var app = angular.module('exap-admin', [
//    'ngRoute',
    'ui.router',
    'ui.bootstrap',
    'ui.codemirror',
    'ngAnimate',
    'exap-admin.filters',
    'exap-admin.services',
    'exap-admin.directives',
    'exap-admin.controllers'
]);
//app.config(['$routeProvider', function ($routeProvider) {
//    routeProvider = $routeProvider;
//    $routeProvider.when('/overview/:entityGroup/:entityName', {templateUrl: 'resources/partials/admin/Overview.html', controller: 'OverviewController'});
//    $routeProvider.otherwise({redirectTo: '/overview/masterdata/ExamType'});
//}]);
app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('Overview', {
            url: "/overview",
            views: {
                "overview": {
                    templateUrl: "resources/partials/admin/Overview.html",
                    controller: "OverviewController"
                }
            }
        })
        .state('Overview.Masterdata', {
            url: "/masterdata/:entityName",
            views: {
                "overview_list": {
                    templateUrl: function ($stateParams) {
                        return 'resources/partials/admin/overview/' + $stateParams.entityName + '.html';
                    },
                    controller: "OverviewDataController"
                },
                "overview_actions_menu": {
                    templateUrl: 'resources/partials/admin/overview/overview.panel.header.html'
                }
            }
        })
}]);