'use strict';

/* Filters */

var exap = angular.module('exap-exam.helpers', []);
exap.factory('ExamFormFactory', ['$http', function ($http) {
    var urlBase = 'rest/exam';
    var dataFactory = {};

    dataFactory.storePersonalData = function (personalData, success, error) {
        return $http.post(urlBase + "/personalData", personalData).success(success).error(error);
    };

    return dataFactory;
}]);
exap.directive("checkboxGroup", function () {
    return {
        restrict: "A",
        link: function (scope, elem, attrs) {
            // Determine initial checked boxes
            if (scope.array.indexOf(scope.item.id) !== -1) {
                elem[0].checked = true;
            }

            // Update array on click
            elem.bind('click', function () {
                var index = scope.array.indexOf(scope.item.id);
                // Add if checked
                if (elem[0].checked) {
                    if (index === -1) scope.array.push(scope.item.id);
                }
                // Remove if unchecked
                else {
                    if (index !== -1) scope.array.splice(index, 1);
                }
                // Sort and update DOM display
                scope.$apply(scope.array.sort(function (a, b) {
                    return a - b
                }));
            });
        }
    }
});