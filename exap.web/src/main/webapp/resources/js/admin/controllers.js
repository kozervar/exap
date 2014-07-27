'use strict';

/* Controllers */

var exapControllers = angular.module('exap-admin.controllers', []);

exapControllers.controller('OverviewController', ['$scope', '$stateParams', 'EntityNameFactory', function ($scope, $stateParams, EntityNameFactory) {

    $('#new-entity-modal').on('hidden.bs.modal', function () {
        $(this).removeData('bs.modal');
        $(this).data('bs.modal', null);
    });

    $scope.$on('$stateChangeSuccess',
        function (event, toState, toParams, fromState, fromParams) {
            $scope.entityName = EntityNameFactory.get(toParams.entityName);
            $scope.$broadcast('NAME_SET', {entityName: $scope.entityName});
        });
}]);

exapControllers.controller('OverviewDataController', ['$scope', '$stateParams', '$modal', 'EntityNameFactory', 'RESTfacade', function ($scope, $stateParams, $modal, EntityNameFactory, RESTfacade) {
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
        var restFacade = new RESTfacade(entity);
        restFacade.$save({entityName: $scope.entityName.entityName}, function (result) {
            $scope.entities.push(result);
        });
    };
    $scope.updateEntity = function (entity) {
        entity.$update({entityName: $scope.entityName.entityName, id: entity.id}, function () {
            $scope.refresh();
        });
    };
    $scope.removeEntity = function (entity) {
        entity.$remove({entityName: $scope.entityName.entityName, id: entity.id}, function () {
            $scope.refresh();
        });
    };

    $scope.refresh = function () {
        $scope.getAll($scope.entityName.entityName);
    };

    $scope.editRow = function (entity) {
        $scope.updateEntityModal(entity);
    };

    $scope.newEntityModal = function (args) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/partials/admin/overview/' + $stateParams.entityName + '.new.html',
            controller: 'ModalNewEntityController'
        });

        modalInstance.result.then(function (result) {
            $scope.saveEntity(result);
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    $scope.updateEntityModal = function (args) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/partials/admin/overview/' + $stateParams.entityName + '.new.html',
            controller: 'ModalUpdateEntityController',
            resolve: {
                args: function () {
                    return args;
                }
            }
        });

        modalInstance.result.then(function (result) {
            if (result.action === 'update') {
                $scope.updateEntity(result.entity);
            }
            if (result.action === 'remove') {
                $scope.removeEntity(result.entity);
            }
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };
}]);

exapControllers.controller('ModalNewEntityController', ['$scope', '$stateParams', '$modalInstance', 'EntityNameFactory', function ($scope, $stateParams, $modalInstance, EntityNameFactory) {
    $scope.entityName = EntityNameFactory.get($stateParams.entityName);

    $scope.newEntity = {};
    
    $scope.save = function (entity) {
        $modalInstance.close($scope.newEntity);
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
exapControllers.controller('ModalUpdateEntityController', ['$scope', '$stateParams', '$modalInstance', 'EntityNameFactory', 'args', function ($scope, $stateParams, $modalInstance, EntityNameFactory, args) {
    $scope.entityName = EntityNameFactory.get($stateParams.entityName);

    $scope.editMode = true;

    if (typeof args !== 'undefined') {
        $scope.newEntity = args;
    } else {
        $scope.newEntity = {};
    }

    $scope.save = function (entity) {
        $modalInstance.close({action: 'update', entity: $scope.newEntity });
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    $scope.remove = function () {
        $modalInstance.close({action: 'remove', entity: $scope.newEntity });
    };
}]);
