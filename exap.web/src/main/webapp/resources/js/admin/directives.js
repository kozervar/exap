'use strict';

/* Directives */


var exapDirectives = angular.module('exap-admin.directives', []);

exapDirectives.directive('appVersion', ['version', function (version) {
    return function (scope, elm) {
        elm.text(version);
    };
}]);

exapDirectives.directive('appName', ['name', function (name) {
    return function (scope, elm) {
        elm.text(name);
    };
}]);

exapDirectives.directive('postRender', [ '$timeout', function ($timeout) {
    var def = {
        restrict: 'A',
        terminal: true,
        transclude: true,
        link: function (scope, element, attrs) {
            $timeout(scope.resize, 0);  //Calling a scoped method
        }
    };
    return def;
}]);

exapDirectives.directive('optional', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            elm.addClass('ng-optional');
            ctrl.$parsers.unshift(function (viewValue) {
                if (viewValue != "") {
                    elm.removeClass('ng-optional');
                    elm.removeClass('ng-valid');
                } else {
                    elm.addClass('ng-optional');
                }
                return viewValue;
            });
        }
    };
});

var INTEGER_REGEXP = /^\-?\d+$/;
exapDirectives.directive('integer', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue) {
                if (INTEGER_REGEXP.test(viewValue)) {
                    // it is valid
                    ctrl.$setValidity('integer', true);
                    return viewValue;
                } else {
                    // it is invalid, return undefined (no model update)
                    ctrl.$setValidity('integer', false);
                    return undefined;
                }
            });
        }
    };
});

//var FLOAT_REGEXP = /^\-?\d+((\.|\,)\d+)+$/; // require dot/coma
var FLOAT_REGEXP = /^\-?\d+((\.|\,)\d+)?$/; // do not require dot/coma
exapDirectives.directive('float', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue) {
                if (FLOAT_REGEXP.test(viewValue)) {
                    ctrl.$setValidity('float', true);
                    return parseFloat(viewValue.replace(',', '.'));
                } else {
                    ctrl.$setValidity('float', false);
                    return undefined;
                }
            });
        }
    };
});
//
//exapDirectives.directive('overviewEntityName', ['$stateParams', function ($stateParams) {
//    return function (scope, element, attributes) {
//        var Name = {};
//        if ($stateParams.hasOwnProperty('entityName')) {
//            Name.entityName = $stateParams.entityName;
//            var name = "NAZWA_ENCJI";
//
//            switch ($stateParams.entityName) {
//                case 'ExamType' :
//                    name = 'Typy Egzaminów';
//                    break;
//                case 'QuestionType' :
//                    name = 'Typy Pytań';
//                    break;
//                default :
//                    name =  'NAZWA_ENCJI';
//                    break;
//            }
//            Name.name = name;
//        }
//        element.text(Name.name);
//    };
//}]);
