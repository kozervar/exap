'use strict';


// Declare app level module which depends on filters, and services
var routeProvider;
var app = angular.module('exap-exam', [
//    'ngRoute',
    'ui.router',
    'ui.bootstrap',
    'ui.codemirror',
    'ui.sortable',
    'ngAnimate',
    'ngTagsInput',
    'ng-context-menu',
    'exap-exam.controllers'
]);
app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('form', {
            url: "/form",
            templateUrl: "resources/partials/exam/form.html",
            controller: "FormController"
        })
        .state('form.welcome', {
            url: "/welcome",
            templateUrl: "resources/partials/exam/welcome.html"
        })
        .state('form.profile', {
            url: "/profile",
            templateUrl: "resources/partials/exam/profile.html",
            controller: "ProfileController"
        })
        .state('form.begin', {
            url: "/begin",
            templateUrl: "resources/partials/exam/begin.html"
        })
        .state('form.exam', {
            url: "/exam",
            templateUrl: "resources/partials/exam/exam.html",
            controller: "ExamController"
        })
        .state('form.finish', {
            url: "/finish",
            templateUrl: "resources/partials/exam/finish.html"
        });
        $urlRouterProvider.otherwise('/form/welcome');
}]);