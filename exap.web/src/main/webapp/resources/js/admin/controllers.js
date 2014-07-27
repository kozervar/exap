'use strict';

/* Controllers */

var exapControllers = angular.module('exap-admin.controllers', []);

exapControllers.controller('OverviewController', ['$scope', '$stateParams', 'EntityNameFactory', function ($scope, $stateParams, EntityNameFactory) {
    $scope.$on('$stateChangeSuccess',
        function (event, toState, toParams, fromState, fromParams) {
            $scope.entityName = EntityNameFactory.get(toParams.entityName);
            $scope.$broadcast('NAME_SET', {entityName: $scope.entityName});
        });
}]);

exapControllers.controller('OverviewDataController', ['$scope', '$stateParams', 'EntityNameFactory', 'RESTfacade', function ($scope, $stateParams, EntityNameFactory, RESTfacade) {

    console.log("Data controller");

    $scope.entityName = EntityNameFactory.get($stateParams.entityName);

    $scope.entities = {};

    $scope.$on('$viewContentLoaded',
        function (event) {
            $scope.refresh();
        });

    $scope.getAll = function (entityName) {
        RESTfacade.query({entityName: entityName}).$promise.then(function (result) {
            $scope.entities = result;
        });
    };
    $scope.getEntity = function (entity) {
        entity.$get({entityName: $scope.entityName, id: entity.id});
    };
    $scope.saveEntity = function (entity) {
        entity.$save();
    };
    $scope.updateEntity = function (entity) {
        entity.$update({entityName: $scope.entityName, id: entity.id});
    };
    $scope.removeEntity = function (entity) {
        entity.$remove({entityName: $scope.entityName, id: entity.id});
    };

    $scope.refresh = function () {
        $scope.getAll($scope.entityName.entityName);
    };
}]);
