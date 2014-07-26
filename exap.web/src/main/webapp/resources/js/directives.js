'use strict';

/* Directives */


var exapDirectives = angular.module('exap.directives', []);

exapDirectives.directive('appVersion', ['version', function (version) {
    return function (scope, elm, attrs) {
        elm.text(version);
    };
}]);

exapDirectives.directive('appName', ['name', function (name) {
    return function (scope, elm) {
        elm.text(name);
    };
}]);
