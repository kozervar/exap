'use strict';

/* Controllers */

var exapControllers = angular.module('exap-exam.controllers', []);

exapControllers.controller('FormController', ['$scope', '$state', '$stateParams', 'ExamFormFactory', '$timeout', function ($scope, $state, $stateParams, ExamFormFactory, $timeout) {
    console.debug("Form controller initialized");

    $scope.isEmptyObject = function (obj) {
        for (var prop in obj) {
            if (obj.hasOwnProperty(prop))
                return false;
        }
        return true;
    };

    $scope.formData = {};
    $scope.personalData = {};
    $scope.examData = {};

    $scope.processForm = function () {
        alert('awesome!');
    };

    $scope.codemirrorOptions = {
        lineNumbers: true,
        indentWithTabs: true,
        lineWrapping: true,
        readOnly: 'nocursor',
        theme: 'elegant',
        mode: 'text/x-java'
    };

    $scope.storePersonalData = function () {
        ExamFormFactory.storePersonalData($scope.personalData, function (data, status, headers, config) {
            $scope.examData = data;
            console.log(data);
            $state.go('form.exam');
        }, function (data, status, headers, config) {
            console.error("personal data not saved")
        });
    };
    $scope.saveQuestionAnswer = function (submittedQuestionAnswer) {
        ExamFormFactory.saveQuestionAnswer(submittedQuestionAnswer, function (data, status, headers, config) {
            console.log("Question answer saved");
        }, function (data, status, headers, config) {
            console.error("Question answer not saved")
        });
    };
    $scope.stopExam = function(){
        console.debug("Stopping exam");
        $state.go("form.finish");
        $scope.stopTimer();
    };

    /* PROGRESS BAR */
    $scope.autostart = false;
    $scope.timerRunning = false;
    $scope.timerInterval = 1000;
    $scope.max = 15;
    $scope.dynamic = -2;
    $scope.isProgBarInitialized = false;

    $scope.isTimerRunning = function () {
        return $scope.timerRunning;
    };

    $scope.updateProgressbarValue = function () {
        $scope.dynamic++;
        if ($scope.dynamic > $scope.max) {
            console.debug('timeout');
            $scope.$broadcast(EVENTS.EXAM_TIMEOUT);
            $scope.stopTimer();
        }
    };

    $scope.startTimer = function () {
        $scope.$broadcast('timer-start');
        $scope.timerRunning = true;
        $timeout(function () {
            $scope.updateProgressbarValue();
        });
    };

    $scope.stopTimer = function () {
        $scope.$broadcast('timer-stop');
        $scope.$broadcast('timer-clear');
        $scope.timerRunning = false;
        $scope.dynamic = -2;
    };

    $scope.$on('timer-tick', function (event, args) {
        $timeout(function () {
            $scope.updateProgressbarValue();
        });
    });
}]);
exapControllers.controller('ProfileController', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {
    console.debug("Profile controller initialized");

    $scope.submitProfileData = function () {
        if ($scope.exam_form.$valid) {
            $state.go('form.terms');
        }
        else {
            console.log("exam form invalid");
        }
    };
}]);
exapControllers.controller('TermsController', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {
    console.debug("Profile controller initialized");

    $scope.submitTerms = function () {
        if ($scope.exam_form.$valid) {
            $scope.storePersonalData();
        }
        else {
            console.log("exam form invalid");
        }
    };
}]);
exapControllers.controller('ExamController', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {
    console.debug("Exam controller initialized");

    $scope.questionData = $scope.examData.questionData;

    $scope.$on(EVENTS.EXAM_TIMEOUT, function(ev,args){
        $scope.stopExam();
    });

    $scope.startTimer();
    $scope.currentIndex = 0;
    $state.go('form.exam.question', {index: $scope.currentIndex });
}]);
exapControllers.controller('QuestionController', ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {
    console.debug("Question controller initialized");
    $scope.$on('$locationChangeStart', function (event, newUrl, oldUrl) {
        // event.preventDefault();
        // TODO Prevent BACK into the form.
    });
    $scope.active = false;
    $scope.questionIndex = parseInt($stateParams.index);
    if ($scope.questionIndex === -1) {
        $state.go("form.finish");
        $scope.stopTimer();
        return;
    }
    $scope.active = true;

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
    $scope.getIndexValue = function () {
        if ($scope.questionIndex + 1 < $scope.questionData.length) {
            $scope.questionIndex++;
            return $scope.questionIndex;
        }
        console.debug("No more questions. Exam finished");
        return -1;
    };
    $scope.goToNextQuestion = function () {

        var answer = {
            token: $scope.examData.token,
            questionId: $scope.question.questionId
        };
        if (!$scope.isEmptyObject($scope.answer)) answer.answer = $scope.answer;
        if (!$scope.isEmptyObject($scope.selectedAnswer)) answer.selectedAnswer = $scope.selectedAnswer;
        if (!$scope.isEmptyObject($scope.selectedAnswers)) answer.selectedAnswers = $scope.selectedAnswers;

        $scope.saveQuestionAnswer(answer);
        $state.go("form.exam.question", { index: $scope.getIndexValue() });
    };


}]);
