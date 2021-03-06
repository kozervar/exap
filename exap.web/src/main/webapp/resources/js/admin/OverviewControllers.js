
var exapControllers = angular.module('exap-admin.controllers.overview', ['exap-admin.controllers']);

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

    // language modes for codemirror
    $scope.cmModes = CONSTANTS.CODEMIRROR_LANGUAGES;
    $scope.cmMode = $scope.cmModes[0];

    $scope.codemirrorOption = {
        lineNumbers: true,
        indentWithTabs: true,
        lineWrapping : true,
        theme:'elegant',
        mode: $scope.cmMode.code.toLowerCase(),
        onLoad : function(_cm){
            // HACK to have the codemirror instance in the scope...
            $scope.modeChanged = function(cmMode){
                $scope.cmMode = cmMode;
                _cm.setOption("mode", $scope.cmMode.code.toLowerCase());
            };
        }
    };

    $scope.newEntityModal = function (args) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/partials/admin/overview/' + $scope.entityName.entityName + '.new.html',
            controller: 'ModalNewEntityController',
            backdrop: 'static',
            scope: $scope
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
        RESTfacade.query({entityName: "Question"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
    }
    if($scope.entityName.entityName === "Question")
    {
        RESTfacade.query({entityName: "QuestionType"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
        RESTfacade.query({entityName: "QuestionAnswer"}).$promise.then(function (result) {
            $scope.collection2 = result;
        });
    }
    if($scope.entityName.entityName === "SubmitQuestionHeader")
    {
        RESTfacade.query({entityName: "SubmitQuestionAnswer"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
    }
    if($scope.entityName.entityName === "ExamPaperQuestion")
    {
        RESTfacade.query({entityName: "ExamPaper"}).$promise.then(function (result) {
            $scope.collection1 = result;
        });
        RESTfacade.query({entityName: "Question"}).$promise.then(function (result) {
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
            if( $scope.newEntity.examType !== null) {
                $scope.collection1.forEach(function (entry) {
                    if ($scope.newEntity.examType.id == entry.id) {
                        $scope.newEntity.examType = entry;
                        return;
                    }
                });
            }
        });
    }
    if($scope.entityName.entityName === "QuestionDetail")
    {
        RESTfacade.query({entityName: "Question"}).$promise.then(function (result) {
            $scope.collection1 = result;
            if( $scope.newEntity.question !== null) {
                $scope.collection1.forEach(function (entry) {
                    if ($scope.newEntity.question.id == entry.id) {
                        $scope.newEntity.question = entry;
                        return;
                    }
                });
            }
        });
    }
    if($scope.entityName.entityName === "Question")
    {
        RESTfacade.query({entityName: "QuestionType"}).$promise.then(function (result) {
            $scope.collection1 = result;
            if( $scope.newEntity.questionType !== null) {
                $scope.collection1.forEach(function (entry) {
                    if ($scope.newEntity.questionType.id == entry.id) {
                        $scope.newEntity.questionType = entry;
                        return;
                    }
                });
            }
        });
        RESTfacade.query({entityName: "QuestionAnswer"}).$promise.then(function (result) {
            $scope.collection2 = result;
            if( $scope.newEntity.questionAnswer !== null) {
                $scope.collection2.forEach(function (entry) {
                    if ($scope.newEntity.questionAnswer.id == entry.id) {
                        $scope.newEntity.questionAnswer = entry;
                        return;
                    }
                });
            }
        });
    }
    if($scope.entityName.entityName === "SubmitQuestionHeader")
    {
        RESTfacade.query({entityName: "SubmitQuestionAnswer"}).$promise.then(function (result) {
            $scope.collection1 = result;
            if( $scope.newEntity.submitQuestionAnswer !== null) {
                $scope.collection1.forEach(function (entry) {
                    if ($scope.newEntity.submitQuestionAnswer.id == entry.id) {
                        $scope.newEntity.submitQuestionAnswer = entry;
                        return;
                    }
                });
            }
        });
    }
    if($scope.entityName.entityName === "ExamPaperQuestionSubject")
    {
        RESTfacade.query({entityName: "ExamPaper"}).$promise.then(function (result) {
            $scope.collection1 = result;
            if( $scope.newEntity.examPaper !== null) {
                $scope.collection1.forEach(function (entry) {
                    if ($scope.newEntity.examPaper.id == entry.id) {
                        $scope.newEntity.examPaper = entry;
                        return;
                    }
                });
            }
        });
        RESTfacade.query({entityName: "Question"}).$promise.then(function (result) {
            $scope.collection2 = result;
            if( $scope.newEntity.question !== null) {
                $scope.collection2.forEach(function (entry) {
                    if ($scope.newEntity.question.id == entry.id) {
                        $scope.newEntity.question = entry;
                        return;
                    }
                });
            }
        });
    }

    $scope.editMode = true;
    $scope.modalMode = { name : "Edycja" };

    if (typeof args !== null) {
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

    if (typeof args !== null) {
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
