var exapControllers = angular.module('exap-admin.controllers.crud.question', []);

exapControllers.controller('Question_READ_CRUDController', ['$scope', '$state', '$stateParams', 'QuestionTypeFactory', 'QuestionFactory', 'TagFactory', 'ContextMenuService', function ($scope, $state, $stateParams, QuestionTypeFactory, QuestionFactory, TagFactory, ContextMenuService) {
    console.debug("Question_READ_CRUDController");

    $scope.updateEntityName($stateParams.entityName);

    $scope.entities = {};
    $scope.tags = [];

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
    $scope.$on(EVENTS.CRUD_CONTEXT_MENU_REMOVE_ENTITY, function (e, entity) {
        QuestionFactory.deleteQuestion(entity.id, function (data, status, headers, config) {
            var index = $scope.entities.indexOf(entity);
            if (index > -1) {
                $scope.entities.splice(index, 1);
            }
            console.debug("row deleted")
        }, function (data, status, headers, config) {
            console.error("row not deleted")
        });
    });
    $scope.loadTags = function (query) {
        return TagFactory.getTagsByQuery(query, function (response) {
        }, function () {
        });
    };
    $scope.tagAdded = function ($tag) {
        QuestionFactory.getQuestionsByTags($scope.tags, function (data, status, headers, config) {
            $scope.entities = data;
        });
    };
    $scope.tagRemoved = function ($tag) {
        QuestionFactory.getQuestionsByTags($scope.tags, function (data, status, headers, config) {
            $scope.entities = data;
        });
    };
}]);

exapControllers.controller('Question_CREATE_CRUDController', ['$scope', '$state', '$stateParams', 'QuestionTypeFactory', 'QuestionFactory', 'TagFactory', '$http', function ($scope, $state, $stateParams, QuestionTypeFactory, QuestionFactory, TagFactory, $http) {
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
                question: $scope.question,
                tags: $scope.tags
            };
            QuestionFactory.createQuestion(questionDTO, function (data, status, headers, config) {
                $scope.goToPreviousState();
            }, function (data, status, headers, config) {
                console.error("entity not saved")
            });
        }
    };
    $scope.loadTags = function (query) {
//        return QuestionFactory.getAvailableTagsByQuery(query, function(){},function(){});
        return TagFactory.getTagsByQuery(query, function (response) {
        }, function () {
        });
    };
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
    $scope.questionAnswer = $scope.question.questionAnswer;
    $scope.tags = [];

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
    });

    QuestionFactory.getQuestionTags($scope.question.id, function (data, status, headers, config) {
        $scope.tags = data;
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
        if ($scope.form_update.$valid) {
            var questionDTO = {
                question: $scope.question,
                tags: $scope.tags
            };
            QuestionFactory.updateQuestion(questionDTO, function (data, status, headers, config) {
                $scope.goToPreviousState();
            }, function (data, status, headers, config) {
                console.error("entity not updated")
            });
        }
    };
    $scope.loadTags = function (query) {
        return QuestionFactory.getAvailableTagsByQuery(query, function () {
        }, function () {
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
