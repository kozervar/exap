var exapControllers = angular.module('exap-admin.controllers.crud.examPaper', []);

exapControllers.controller('ExamPaper_READ_CRUDController', ['$scope', '$state', '$stateParams', 'ExamTypeFactory', 'ExamPaperFactory', function ($scope, $state, $stateParams, ExamTypeFactory, ExamPaperFactory) {
    console.debug("Question_READ_CRUDController");

    $scope.updateEntityName($stateParams.entityName);

    $scope.entities = {};
    $scope.tags = [];

    ExamPaperFactory.getExamPapers(function (data, status, headers, config) {
        $scope.entities = data;
    });

    $scope.editRow = function (entity) {
        $scope.storeEntityForUpdate(entity);
        $state.go("CRUD.operation.Entity", {
            operation: CRUD.UPDATE,
            entityName: $scope.CRUD.entityName.entityName
        }, {inherit: true});
    };
    $scope.$on(EVENTS.CRUD_READ_ENTITIES, function (e) {
        ExamPaperFactory.getExamPapers(function (data, status, headers, config) {
            $scope.entities = data;
        });
    });

}]);

exapControllers.controller('ExamPaper_CREATE_CRUDController', ['$scope', '$state', '$stateParams', 'ExamPaperFactory', 'ExamTypeFactory', function ($scope, $state, $stateParams, ExamPaperFactory, ExamTypeFactory) {
    console.debug("ExamPaper_CREATE_CRUDController");

    $scope.updateEntityName($stateParams.entityName);

    $scope.examPaper = {
        questionType: {
            name: CONSTANTS.QUESTION_TYPE.OPEN
        }
    };

    $scope.examPaper = {
        name : "",
        description : "",
        active : false,
        examType : {},
        examPaperQuestions : []
    };
    $scope.examTypes = [];

    ExamTypeFactory.getExamTypes(function (data, status, headers, config) {
        $scope.examTypes = data;
        if ($scope.examTypes != null && $scope.examTypes.length > 0)
            $scope.examPaper.examType = $scope.examTypes[0];
    });

    $scope.isOpenExamType = function () {
        if ($scope.question.questionType.name === CONSTANTS.EXAM_TYPE.OPEN)
            return true;
        else
            return false;
    };

    $scope.saveExamPaper = function () {
        if ($scope.form_new.$valid) {
            ExamPaperFactory.createExamPaper($scope.examPaper, function (data, status, headers, config) {
                $scope.goToPreviousState();
            }, function (data, status, headers, config) {
                console.error("entity not saved")
            });
        }
    };

}]);
exapControllers.controller('ExamPaper_UPDATE_CRUDController', ['$scope', '$state', '$stateParams', '$modal', 'ExamPaperFactory', 'ExamTypeFactory', function ($scope, $state, $stateParams, $modal, ExamPaperFactory, ExamTypeFactory) {
    console.debug("Question_UPDATE_CRUDController");

    $scope.updateEntityName($stateParams.entityName);

    $scope.isDisabled = true;
    if ($scope.entityToUpdate != null)
        $scope.isDisabled = false;
    else
        return;
    
    $scope.examPaper = {
        questionType: {
            name: CONSTANTS.QUESTION_TYPE.OPEN
        }
    };
    
    $scope.examPaper = $scope.entityToUpdate;
    $scope.examTypes = [];

    ExamTypeFactory.getExamTypes(function (data, status, headers, config) {
        $scope.examTypes = data;
        if ($scope.examPaper.examType !== null) {
            $scope.examTypes.forEach(function (entry) {
                if ($scope.examPaper.examType.id == entry.id) {
                    $scope.examPaper.examType = entry;
                    return;
                }
            });
        }
    });

    $scope.isOpenExamType = function () {
        if ($scope.question.questionType.name === CONSTANTS.EXAM_TYPE.OPEN)
            return true;
        else
            return false;
    };

    $scope.updateExamPaper = function () {
        if ($scope.form_update.$valid) {
            ExamPaperFactory.updateExamPaper($scope.examPaper, function (data, status, headers, config) {
                $scope.goToPreviousState();
            }, function (data, status, headers, config) {
                console.error("entity not saved")
            });
        }
    };

    $scope.deleteExamPaper = function () {
        ExamPaperFactory.deleteExamPaper($scope.examPaper.id, function (data, status, headers, config) {
            $scope.goToPreviousState();
        }, function (data, status, headers, config) {
            console.error("entity not updated")
        });
    };

    $scope.deleteExamPaperModal = function () {
        var modalInstance = $modal.open({
            templateUrl: 'resources/partials/admin/CRUD/delete.confirmation.modal.html',
            controller: 'DeleteEntityModalController',
            backdrop: 'static',
            scope: $scope
        });

        modalInstance.result.then(function (result) {
            $scope.deleteExamPaper();
        });
    };


}]);


