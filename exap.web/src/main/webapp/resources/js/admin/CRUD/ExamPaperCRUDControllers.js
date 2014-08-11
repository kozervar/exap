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

exapControllers.controller('ExamPaper_CREATE_CRUDController', ['$scope', '$state', '$stateParams', 'ExamPaperFactory', 'ExamTypeFactory', 'QuestionFactory', function ($scope, $state, $stateParams, ExamPaperFactory, ExamTypeFactory, QuestionFactory) {
    console.debug("ExamPaper_CREATE_CRUDController");

    $scope.updateEntityName($stateParams.entityName);

    $scope.examPaper = {
        questionType: {
            name: CONSTANTS.QUESTION_TYPE.OPEN
        }
    };

    $scope.examPaper = {
        name: "",
        description: "",
        active: false,
        examType: {},
        examPaperQuestions: []
    };
    $scope.examTypes = [];
    $scope.questions = [];
    $scope.questionsAdded = []; //[{subject:'test', questionType: {name : 'OPEN'}}];

    $scope.sortableOptions = {
        placeholder: "question",
        connectWith: ".question-container"
    };

    ExamTypeFactory.getExamTypes(function (data, status, headers, config) {
        $scope.examTypes = data;
        if ($scope.examTypes != null && $scope.examTypes.length > 0)
            $scope.examPaper.examType = $scope.examTypes[0];
    });
    QuestionFactory.getQuestions(function (data, status, headers, config) {
        $scope.questions = data;
    });

    $scope.isOpenExamType = function () {
        if ($scope.question.questionType.name === CONSTANTS.EXAM_TYPE.OPEN)
            return true;
        else
            return false;
    };

    $scope.saveExamPaper = function () {
        if ($scope.form_new.$valid) {
            var examPaperDTO = {
                examPaper: $scope.examPaper,
                questions: $scope.questionsAdded
            };
            ExamPaperFactory.createExamPaper(examPaperDTO, function (data, status, headers, config) {
                $scope.goToPreviousState();
            }, function (data, status, headers, config) {
                console.error("entity not saved")
            });
        }
    };

}]);
exapControllers.controller('ExamPaper_UPDATE_CRUDController', ['$scope', '$state', '$stateParams', '$modal', 'ExamPaperFactory', 'ExamTypeFactory', 'QuestionFactory', function ($scope, $state, $stateParams, $modal, ExamPaperFactory, ExamTypeFactory, QuestionFactory) {
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
    $scope.questions = [];
    $scope.questionsAdded = []; //[{subject:'test', questionType: {name : 'OPEN'}}];

    $scope.sortableOptions = {
        placeholder: "question",
        connectWith: ".question-container"
    };

    $scope.entityToUpdate.examPaperQuestions.forEach(function (entry) {
        $scope.questionsAdded.push(entry.question);
    });
    $scope.entityToUpdate.examPaperQuestions = [];

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
    QuestionFactory.getQuestions(function (data, status, headers, config) {
        $scope.questions = data.slice();
        $scope.questionsAdded.forEach(function (questionAdded) {
            data.forEach(function (question) {
                if (question.id == questionAdded.id) {
                    var index = $scope.questions.indexOf(question);
                    $scope.questions.splice(index, 1);
                }
            });
        });
    });

    $scope.isOpenExamType = function () {
        if ($scope.question.questionType.name === CONSTANTS.EXAM_TYPE.OPEN)
            return true;
        else
            return false;
    };

    $scope.updateExamPaper = function () {
        if ($scope.form_update.$valid) {
            var examPaperDTO = {
                examPaper: $scope.examPaper,
                questions: $scope.questionsAdded
            };
            ExamPaperFactory.updateExamPaper(examPaperDTO, function (data, status, headers, config) {
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


