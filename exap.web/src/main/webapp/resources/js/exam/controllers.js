'use strict';

/* Controllers */

var exapControllers = angular.module('exap-exam.controllers', []);

exapControllers.controller('FormController', ['$scope', '$stateParams', function ($scope, $stateParams) {
    console.debug("Form controller initialized");

    $scope.formData = {};

    $scope.processForm = function() {
        alert('awesome!');
    };

    $scope.codemirrorOptions = {
        lineNumbers: true,
        indentWithTabs: true,
        lineWrapping : true,
        readOnly: 'nocursor',
        theme:'elegant',
        mode: 'application/json'
    };
}]);
exapControllers.controller('ProfileController', ['$scope', '$stateParams', function ($scope, $stateParams) {
    console.debug("Profile controller initialized");
}]);
exapControllers.controller('ExamController', ['$scope', '$stateParams', function ($scope, $stateParams) {
    console.debug("Exam controller initialized");
}]);
