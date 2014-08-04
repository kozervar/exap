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
exapControllers.controller('OverviewController', ['$scope', '$stateParams', '$modal', 'EntityNameFactory', 'RESTfacade', '$q', function ($scope, $stateParams, $modal, EntityNameFactory, RESTfacade, $q) {

    $scope.entities = {};

    /* CRUD */

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

    $scope.getAll = function (entityName) {
        RESTfacade.query({entityName: entityName}).$promise.then(function (result) {
            $scope.entities = result;
        });
    };

    $scope.refresh = function () {
        $scope.getAll($scope.entityName.entityName);
    };

    /* MODAL */

    $scope.newEntityModal = function (args) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/partials/admin/overview/' + $scope.entityName.entityName + '.new.html',
            controller: 'ModalNewEntityController',
            backdrop: 'static'
        });

        modalInstance.result.then(function (result) {
            $scope.saveEntity(result);
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
    };

    $scope.updateEntityModal = function (args) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/partials/admin/overview/' + $scope.entityName.entityName + '.new.html',
            controller: 'ModalUpdateEntityController',
            backdrop: 'static',
            resolve: {
                args: function () {
                    return args;
                }
            },
            scope : $scope
        });

        modalInstance.result.then(function (result) {
            if (result.action === 'update') {
                $scope.updateEntity(result.entity);
            }
            if (result.action === 'remove') {
                $scope.removeEntity(result.entity);
            }
        }, function () {
            $scope.refresh();
        });
    };
}]);

exapControllers.controller('OverviewDataController', ['$scope', '$stateParams', 'EntityNameFactory', 'RESTfacade', function ($scope, $stateParams, EntityNameFactory, RESTfacade) {

    $scope.$on('$viewContentLoaded',
        function (event) {
            $scope.refresh();
        });

    $scope.editRow = function (entity) {
        $scope.updateEntityModal(entity);
    };
}]);

/* MODALS */

exapControllers.controller('ModalNewEntityController', ['$scope', '$stateParams', '$modalInstance', 'EntityNameFactory', 'RESTfacade', function ($scope, $stateParams, $modalInstance, EntityNameFactory, RESTfacade) {
    $scope.entityName = EntityNameFactory.get($stateParams.entityName);

    $scope.modalMode = { name : "Nowy" };

    $scope.newEntity = {};

    $scope.collection1 = {};
    $scope.collection2 = {};
    $scope.collection3 = {};

    if($scope.entityName.entityName === "ExamPaper")
    {
        RESTfacade.query({entityName: "ExamType"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
    }
    if($scope.entityName.entityName === "QuestionDetail")
    {
        RESTfacade.query({entityName: "QuestionHeader"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
    }
    if($scope.entityName.entityName === "QuestionHeader")
    {
        RESTfacade.query({entityName: "QuestionType"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
        RESTfacade.query({entityName: "QuestionAnswer"}).$promise.then(function (result) {
            $scope.collection2 = result;
        });
        RESTfacade.query({entityName: "QuestionSubject"}).$promise.then(function (result) {
            $scope.collection3 = result;
        });
    }
    if($scope.entityName.entityName === "QuestionSubject")
    {
        RESTfacade.query({entityName: "QuestionType"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
    }
    if($scope.entityName.entityName === "SubmitQuestionHeader")
    {
        RESTfacade.query({entityName: "SubmitQuestionAnswer"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
    }
    if($scope.entityName.entityName === "ExamPaperQuestionSubject")
    {
        RESTfacade.query({entityName: "ExamPaper"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
        RESTfacade.query({entityName: "QuestionSubject"}).$promise.then(function (result) {
            $scope.collection2 = result;
        });
    }


    $scope.save = function (entity) {
        $modalInstance.close($scope.newEntity);
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
exapControllers.controller('ModalUpdateEntityController', ['$scope', '$stateParams', '$modalInstance', '$modal', 'RESTfacade', 'args', function ($scope, $stateParams, $modalInstance, $modal, RESTfacade, args) {
    // $scope.entityName = EntityNameFactory.get($stateParams.entityName);

    $scope.collection1 = {};
    $scope.collection2 = {};
    $scope.collection3 = {};

    if($scope.entityName.entityName === "ExamPaper")
    {
        RESTfacade.query({entityName: "ExamType"}).$promise.then(function (result) {
            $scope.collection1 = result;
            $scope.collection1.forEach(function(entry){
                if($scope.newEntity.examType.id == entry.id) {
                    $scope.newEntity.examType = entry;
                    return;
                }
            });
        });
    }
    if($scope.entityName.entityName === "QuestionDetail")
    {
        RESTfacade.query({entityName: "QuestionHeader"}).$promise.then(function (result) {
            $scope.collection1 = result;
            $scope.collection1.forEach(function(entry){
                if($scope.newEntity.questionHeader.id == entry.id) {
                    $scope.newEntity.questionHeader = entry;
                    return;
                }
            });
        });
    }
    if($scope.entityName.entityName === "QuestionHeader")
    {
        RESTfacade.query({entityName: "QuestionType"}).$promise.then(function (result) {
            $scope.collection1 = result;
            $scope.collection1.forEach(function(entry){
                if($scope.newEntity.questionType.id == entry.id) {
                    $scope.newEntity.questionType = entry;
                    return;
                }
            });
        });
        RESTfacade.query({entityName: "QuestionAnswer"}).$promise.then(function (result) {
            $scope.collection2 = result;
            $scope.collection2.forEach(function(entry){
                if($scope.newEntity.questionAnswer.id == entry.id) {
                    $scope.newEntity.questionAnswer = entry;
                    return;
                }
            });
        });
        RESTfacade.query({entityName: "QuestionSubject"}).$promise.then(function (result) {
            $scope.collection3 = result;
            $scope.collection3.forEach(function(entry){
                if($scope.newEntity.questionSubject.id == entry.id) {
                    $scope.newEntity.questionSubject = entry;
                    return;
                }
            });
        });
    }
    if($scope.entityName.entityName === "QuestionSubject")
    {
        RESTfacade.query({entityName: "QuestionType"}).$promise.then(function (result) {
            $scope.collection1 = result;
            $scope.collection1.forEach(function(entry){
                if($scope.newEntity.questionType.id == entry.id) {
                    $scope.newEntity.questionType = entry;
                    return;
                }
            });
        });
    }
    if($scope.entityName.entityName === "SubmitQuestionHeader")
    {
        RESTfacade.query({entityName: "SubmitQuestionAnswer"}).$promise.then(function (result) {
            $scope.collection1 = result;
            $scope.collection1.forEach(function(entry){
                if($scope.newEntity.submitQuestionAnswer.id == entry.id) {
                    $scope.newEntity.submitQuestionAnswer = entry;
                    return;
                }
            });
        });
    }
    if($scope.entityName.entityName === "ExamPaperQuestionSubject")
    {
        RESTfacade.query({entityName: "ExamPaper"}).$promise.then(function (result) {
            $scope.collection1 = result;
            $scope.collection1.forEach(function(entry){
                if($scope.newEntity.examPaper.id == entry.id) {
                    $scope.newEntity.examPaper = entry;
                    return;
                }
            });
        });
        RESTfacade.query({entityName: "QuestionSubject"}).$promise.then(function (result) {
            $scope.collection2 = result;
            $scope.collection2.forEach(function(entry){
                if($scope.newEntity.questionSubject.id == entry.id) {
                    $scope.newEntity.questionSubject = entry;
                    return;
                }
            });
        });
    }

    $scope.editMode = true;
    $scope.modalMode = { name : "Edycja" };

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
        $scope.removeEntityModal($scope.newEntity);
    };

    $scope.removeEntityModal = function (args) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/partials/admin/overview/delete.confirmation.modal.html',
            controller: 'ModalDeleteEntityController',
            backdrop: 'static',
            resolve: {
                args: function () {
                    return args;
                }
            }
        });

        modalInstance.result.then(function (result) {
            $modalInstance.close({action: 'remove', entity: $scope.newEntity });
        });
    };
}]);
exapControllers.controller('ModalDeleteEntityController', ['$scope', '$stateParams', '$modalInstance', 'EntityNameFactory', 'args', function ($scope, $stateParams, $modalInstance, EntityNameFactory, args) {

    $scope.entityName = EntityNameFactory.get($stateParams.entityName);

    if (typeof args !== 'undefined') {
        $scope.entity = args;
    } else {
        $scope.entity = {};
    }

    $scope.cancel = function () {
        $modalInstance.dismiss($scope.entity);
    };
    $scope.confirm = function () {
        $modalInstance.close($scope.entity);
    };
}]);
