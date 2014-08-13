'use strict';

/* Controllers */

var exapControllers = angular.module('exap-exam.controllers', []);

exapControllers.controller('FormController', ['$scope', '$state', '$stateParams', 'ExamFormFactory', function ($scope, $state, $stateParams, ExamFormFactory) {
    console.debug("Form controller initialized");

    $scope.formData = {};
    $scope.personalData = {};
    $scope.examData = {};

    $scope.processForm = function() {
        alert('awesome!');
    };

    $scope.codemirrorOptions = {
        lineNumbers: true,
        indentWithTabs: true,
        lineWrapping : true,
        readOnly: 'nocursor',
        theme:'elegant',
        mode: CONSTANTS.CODEMIRROR_LANGUAGES[0]
    };

    $scope.storePersonalData = function(){
        ExamFormFactory.storePersonalData($scope.personalData, function (data, status, headers, config) {
            $scope.examData = data;
            console.log(data);
            $state.go('form.exam');
        }, function (data, status, headers, config) {
            console.error("personal data not saved")
        });
    };
}]);
exapControllers.controller('ProfileController', ['$scope', '$stateParams', function ($scope, $stateParams) {
    console.debug("Profile controller initialized");
}]);
exapControllers.controller('ExamController', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {
    console.debug("Exam controller initialized");

    $scope.questionData = $scope.examData.questionData;

    $scope.saveAnswer = function(answer){
        console.log(answer);
    };

    $scope.currentIndex = 0;
    $state.go('form.exam.question', {index : $scope.currentIndex });
}]);
exapControllers.controller('QuestionController', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {
    console.debug("Question controller initialized");
    $scope.$on('$locationChangeStart', function(event, newUrl, oldUrl) {
        // event.preventDefault();
        // TODO Prevent BACK into the form.
    });
    $scope.questionIndex = parseInt($stateParams.index);
    if($scope.questionIndex === -1)
    {
        $state.go("form.finish");
        return;
    }
    $scope.answer = {};
    $scope.selectedAnswer = {};
    $scope.selectedAnswers = [];
    $scope.question = $scope.questionData[$scope.questionIndex];

    $scope.closedType = $scope.question.questionType.name == CONSTANTS.QUESTION_TYPE.CLOSED;
    $scope.closedMultiType = $scope.question.questionType.name == CONSTANTS.QUESTION_TYPE.CLOSED_MUTLI;
    $scope.openType = $scope.question.questionType.name == CONSTANTS.QUESTION_TYPE.OPEN;

    $scope.toggleSelection = function toggleSelection(question) {
        var idx = $scope.selectedAnswers.indexOf(question);
        // is currently selected
        if (idx > -1) {
            $scope.selectedAnswers.splice(idx, 1);
        }
        // is newly selected
        else {
            $scope.selectedAnswers.push(question);
        }
    };
    $scope.getIndexValue = function(){
        if($scope.questionIndex + 1 < $scope.questionData.length) {
            $scope.questionIndex++;
            return $scope.questionIndex;
        }
        console.log("No more questions!");
        return -1;
    };
    $scope.goToNextQuestion = function(){
        var answer = {
            question : $scope.question,
            answer : $scope.answer,
            selectedAnswer : $scope.selectedAnswer,
            selectedAnswers : $scope.selectedAnswers
        };
        $scope.saveAnswer(answer);
        $state.go("form.exam.question", { index : $scope.getIndexValue() });
    };


}]);
