'use strict';


// Declare app level module which depends on filters, and services
angular.module('exap', [
  'ngRoute',
  'exap.filters',
  'exap.services',
  'exap.directives',
  'exap.controllers'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {templateUrl: 'resources/partials/home.html', controller: 'HomeController'});
  $routeProvider.otherwise({redirectTo: '/home'});
}]);
