'use strict';

/* Controllers */

var exapControllers = angular.module('exap-admin.controllers', []);

exapControllers.controller('StateController', ['$scope', '$stateParams', 'EntityNameFactory', function ($scope, $stateParams, EntityNameFactory) {
    $scope.$on('$stateChangeStart',
        function (event, toState, toParams, fromState, fromParams) {
            $scope.entityName = EntityNameFactory.get(toParams.entityName);
            $scope.$broadcast(EVENTS.OVERVIEW_ENTITY_NAME_SET, {entityName: $scope.entityName});
        });
}]);
