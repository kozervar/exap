var exapControllers = angular.module('exap-admin.controllers.creator', []);

exapControllers.controller('CreatorController', ['$scope', '$stateParams', 'RESTfacade', function ($scope, $stateParams, RESTfacade) {

    // language modes for codemirror
    $scope.cmModes = CONSTANTS.CODEMIRROR_LANGUAGES;
    $scope.cmMode = $scope.cmModes[0];

    $scope.codemirrorOption = {
        lineNumbers: true,
        indentWithTabs: true,
        lineWrapping: true,
        theme: 'elegant',
        mode: $scope.cmMode.code.toLowerCase(),
        onLoad: function (_cm) {
            // HACK to have the codemirror instance in the scope...
            $scope.modeChanged = function (cmMode) {
                $scope.cmMode = cmMode;
                _cm.setOption("mode", $scope.cmMode.code.toLowerCase());
            };
        }
    };

}]);

exapControllers.controller('QuestionCreatorController', ['$scope', '$stateParams', 'RESTfacade', function ($scope, $stateParams, RESTfacade) {

    function QuestionDetail(){
    }
    $scope.totalScore = 0;
    $scope.question = {};
    $scope.questionDetails = [];

    RESTfacade.query({entityName: "QuestionType"}).$promise.then(function (result) {
        $scope.questionTypes = result;
        if ($scope.questionTypes != null && $scope.questionTypes.length > 0)
            $scope.question.questionType = $scope.questionTypes[0];
    });

    $scope.addQuestionDetail = function(){
        $scope.questionDetails.push(new QuestionDetail());
    };
    $scope.setOrder = function(questionDetail){
        questionDetail.sortOrder =  $scope.questionDetails.indexOf(questionDetail);
    };
    $scope.cancelQuestionDetail = function(questionDetail){
        var index = $scope.questionDetails.indexOf(questionDetail);
        $scope.questionDetails.splice(index,1);
        $scope.questionDetails.forEach(function (entry) {
            $scope.setOrder(entry);
        });
    };
    $scope.updateTotalScore = function(){
        $scope.totalScore = 0;
        $scope.questionDetails.forEach(function (entry) {
            if(entry.score !== undefined)
            $scope.totalScore += entry.score;
        });
    };

}]);