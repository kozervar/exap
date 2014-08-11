'use strict';


// Declare app level module which depends on filters, and services
var routeProvider;
var app = angular.module('exap-admin', [
//    'ngRoute',
    'ui.router',
    'ui.bootstrap',
    'ui.codemirror',
    'ui.sortable',
    'ngAnimate',
    'ngTagsInput',
    'ng-context-menu',
    'exap-admin.filters',
    'exap-admin.services',
    'exap-admin.directives',
    'exap-admin.controllers',
    'exap-admin.controllers.overview',
    'exap-admin.controllers.crud',
    'exap-admin.controllers.crud.question',
    'exap-admin.controllers.crud.examPaper'
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
        });

    $stateProvider
        .state('CRUD', {
            url: "/CRUD",
            views: {
                "CRUD": {
                    controller : "CRUDController",
                    templateUrl: 'resources/partials/admin/CRUD.html'
                }
            }
        })
        .state('CRUD.operation', {
            url: "/:operation",
            views: {
                "CRUD_operation": {
                    controllerProvider: function($stateParams){
                        return $stateParams.operation + "_CRUDController";
                    },
                    templateUrl: function ($stateParams) {
                        return 'resources/partials/admin/CRUD.' + $stateParams.operation + '.html';
                    }
                }
            }
        })
        /* CRUD: Entity */
        .state('CRUD.operation.Entity', {
            url: "/:entityName",
            views: {
                "CRUD_operation_entity": {
                    controllerProvider: function($stateParams){
                        return $stateParams.entityName + "_" + $stateParams.operation + "_CRUDController";
                    },
                    templateUrl: function ($stateParams) {
                        return 'resources/partials/admin/CRUD/' + $stateParams.entityName + '.' + $stateParams.operation + '.html';
                    }
                },
                "CRUD_READ_table_header": {
                    templateUrl: 'resources/partials/admin/CRUD/READ.table.header.html'
                }
            }
        })
}]);