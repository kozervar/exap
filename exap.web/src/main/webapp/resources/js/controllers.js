'use strict';

/* Controllers */

var exapControllers = angular.module('exap.controllers', []);

exapControllers.controller('HomeController', ['$scope', 'ExamType', function ($scope, ExamType) {
    $scope.examType = new ExamType({"name": "test", "description": Math.random() });

    $scope.examTypes = ExamType.get();

    $scope.saveEntity = function (entity) {
        entity.$save();
    };
    $scope.updateEntity = function (entity) {
        entity.description = "updated";
        var ent = new ExamType(entity);
        entity.$update({id: entity.id});
    };
    $scope.removeEntity = function (entity) {
        entity.$remove({id: entity.id});
    };

    $scope.refresh = function () {
        $scope.examType = new ExamType({"name": "test", "description": Math.random() });
        $scope.examTypes = ExamType.get();
    };
}]);
