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

exapDirectives.directive('postRender', [ '$timeout', function($timeout) {
    var def = {
        restrict : 'A',
        terminal : true,
        transclude : true,
        link : function(scope, element, attrs) {
            $timeout(scope.resize, 0);  //Calling a scoped method
        }
    };
    return def;
}]);
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
