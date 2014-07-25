'use strict';

/* Filters */

angular.module('myApp.filters', []).
  filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    };
  }]).
  filter('interpolate', ['name', function(name) {
    return function(text) {
      return String(text).replace(/\%NAME\%/mg, name);
    };
  }]);