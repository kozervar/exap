var exapControllers = angular.module('exap-admin.controllers.crud.question', []);

exapControllers.controller('Question_CREATE_CRUDController', ['$scope', '$state', '$stateParams', 'QuestionTypeFactory', 'QuestionFactory', '$http', function ($scope, $state, $stateParams, QuestionTypeFactory, QuestionFactory, $http) {
    console.debug("Question_CREATE_CRUDController");

    $scope.updateEntityName($stateParams.entityName);

    function QuestionDetail() {
    }

    function QuestionAnswer() {
        this.longTextValue = "";
    }

    $scope.totalScore = 0;
    $scope.question = {
        questionType: {
            name: CONSTANTS.QUESTION_TYPE.OPEN
        }
    };
    $scope.questionDetails = [];
    $scope.questionAnswer = {};
    $scope.tags = [];

    QuestionTypeFactory.getQuestionTypes(function (data, status, headers, config) {
        $scope.questionTypes = data;
        if ($scope.questionTypes != null && $scope.questionTypes.length > 0)
            $scope.question.questionType = $scope.questionTypes[0];
    });

    $scope.isOpenQuestionType = function () {
        if ($scope.question.questionType.name === CONSTANTS.QUESTION_TYPE.OPEN)
            return true;
        else
            return false;
    };

    $scope.addQuestionDetail = function () {
        $scope.questionDetails.push(new QuestionDetail());
    };
    $scope.addQuestionAnswer = function () {
        $scope.questionAnswer = new QuestionAnswer();
    };
    $scope.setOrder = function (questionDetail) {
        questionDetail.sortOrder = $scope.questionDetails.indexOf(questionDetail);
    };
    $scope.cancelQuestionDetail = function (questionDetail) {
        var index = $scope.questionDetails.indexOf(questionDetail);
        $scope.questionDetails.splice(index, 1);
        $scope.questionDetails.forEach(function (entry) {
            $scope.setOrder(entry);
        });
    };
    $scope.updateTotalScore = function () {
        $scope.totalScore = 0;
        $scope.questionDetails.forEach(function (entry) {
            if (entry.score !== undefined)
                $scope.totalScore += entry.score;
        });
    };
    $scope.saveQuestion = function () {
        if ($scope.isOpenQuestionType()) {
            $scope.question.questionAnswer = $scope.questionAnswer;
        }
        else {
            $scope.question.questionDetails = $scope.questionDetails;
        }
        if ($scope.form_new.$valid) {
            var questionDTO = {
                question : $scope.question,
                tags : $scope.tags
            };
            QuestionFactory.createQuestion(questionDTO, function (data, status, headers, config) {
                $scope.goToPreviousState();
            }, function (data, status, headers, config) {
                console.error("entity not saved")
            });
        }
    };
    $scope.loadTags = function (query) {
        return QuestionFactory.getAvailableTagsByQuery(query, function(){},function(){});
    };
}]);

exapControllers.controller('Question_READ_CRUDController', ['$scope', '$state', '$stateParams', 'QuestionTypeFactory', 'QuestionFactory', function ($scope, $state, $stateParams, QuestionTypeFactory, QuestionFactory) {
    console.debug("Question_READ_CRUDController");

    $scope.updateEntityName($stateParams.entityName);

    $scope.entities = {};

    QuestionFactory.getQuestions(function (data, status, headers, config) {
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
        QuestionFactory.getQuestions(function (data, status, headers, config) {
            $scope.entities = data;
        });
    });

}]);
exapControllers.controller('Question_UPDATE_CRUDController', ['$scope', '$state', '$stateParams', '$modal', 'QuestionTypeFactory', 'QuestionFactory', function ($scope, $state, $stateParams, $modal, QuestionTypeFactory, QuestionFactory) {
    console.debug("Question_UPDATE_CRUDController");

    $scope.updateEntityName($stateParams.entityName);

    $scope.isDisabled = true;
    if ($scope.entityToUpdate != null)
        $scope.isDisabled = false;
    else
        return;

    function QuestionDetail() {
    }

    function QuestionAnswer() {
        this.longTextValue = "";
    }

    $scope.totalScore = 0;
    $scope.question = $scope.entityToUpdate;
    $scope.questionDetails = $scope.question.questionDetails;
    $scope.questionAnswer = {};

    QuestionTypeFactory.getQuestionTypes(function (data, status, headers, config) {
        $scope.questionTypes = data;
        if ($scope.question.questionType !== null) {
            $scope.questionTypes.forEach(function (entry) {
                if ($scope.question.questionType.id == entry.id) {
                    $scope.question.questionType = entry;
                    return;
                }
            });
        }
        console.log("Question types received")
    });

    $scope.isOpenQuestionType = function () {
        if ($scope.question.questionType.name === CONSTANTS.QUESTION_TYPE.OPEN)
            return true;
        else
            return false;
    };

    $scope.addQuestionDetail = function () {
        $scope.questionDetails.push(new QuestionDetail());
    };
    $scope.addQuestionAnswer = function () {
        $scope.questionAnswer = new QuestionAnswer();
    };
    $scope.setOrder = function (questionDetail) {
        questionDetail.sortOrder = $scope.questionDetails.indexOf(questionDetail);
    };
    $scope.cancelQuestionDetail = function (questionDetail) {
        var index = $scope.questionDetails.indexOf(questionDetail);
        $scope.questionDetails.splice(index, 1);
        $scope.questionDetails.forEach(function (entry) {
            $scope.setOrder(entry);
        });
    };
    $scope.updateTotalScore = function () {
        $scope.totalScore = 0;
        $scope.questionDetails.forEach(function (entry) {
            if (entry.score !== undefined)
                $scope.totalScore += entry.score;
        });
    };
    $scope.updateQuestion = function () {
        if ($scope.isOpenQuestionType()) {
            $scope.question.questionAnswer = $scope.questionAnswer;
        }
        else {
            $scope.question.questionDetails = $scope.questionDetails;
        }
        QuestionFactory.updateQuestion($scope.question, function (data, status, headers, config) {
            $scope.goToPreviousState();
        }, function (data, status, headers, config) {
            console.error("entity not updated")
        });
    };
    $scope.deleteQuestion = function () {
        QuestionFactory.deleteQuestion($scope.question.id, function (data, status, headers, config) {
            $scope.goToPreviousState();
        }, function (data, status, headers, config) {
            console.error("entity not updated")
        });
    };

    $scope.deleteQuestionModal = function () {
        var modalInstance = $modal.open({
            templateUrl: 'resources/partials/admin/CRUD/delete.confirmation.modal.html',
            controller: 'DeleteEntityModalController',
            backdrop: 'static',
            scope: $scope
        });

        modalInstance.result.then(function (result) {
            $scope.deleteQuestion();
        });
    };

}]);

exapControllers.controller('DeleteEntityModalController', ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.cancel = function () {
        $modalInstance.dismiss();
    };
    $scope.confirm = function () {
        $modalInstance.close();
    };
}]);
