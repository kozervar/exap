var exapControllers = angular.module('exap-admin.controllers.crud', []);

exapControllers.controller('CRUDController', ['$scope', '$state', 'EntitiesMetaDataFactory', '$stateParams', function ($scope, $state, EntitiesMetaDataFactory, $stateParams) {
    console.debug("CRUDController");

    $scope.CRUD = {};
    $scope.updateOperation = function (op) {
        var operation = {operationName: op};
        switch (op) {
            case CRUD.CREATE :
                operation.name = "Dodaj: ";
                break;
            case CRUD.READ :
                operation.name = "Pokaż: ";
                break;
            case CRUD.UPDATE :
                operation.name = "Edytuj: ";
                break;
            case CRUD.DELETE :
                operation.name = "Usuń: ";
                break;
            default :
                operation.name = "";
                break;
        }
        $scope.CRUD.operation = operation;
    };
    $scope.updateEntityName = function (entityName) {
        $scope.CRUD.entityName = EntitiesMetaDataFactory.get(entityName);
    };

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

    $scope.storeEntityForUpdate = function (entity) {
        $scope.entityToUpdate = entity;
    }
    $scope.refresh = function () {
        $scope.$broadcast(EVENTS.CRUD_READ_ENTITIES);
    };

    $scope.previousState = {};
    $scope.currentState = {};
    $scope.previousStateParams = {};
    $scope.currentStateParams = {};

    $scope.$on('$stateChangeSuccess', function(ev, to, toParams, from, fromParams) {
        $scope.previousState = from;
        $scope.currentState = to;
        $scope.previousStateParams = fromParams;
        $scope.currentStateParams = toParams;
    });
    $scope.goToPreviousState = function(){
        if($scope.previousState) {
            $state.go($scope.previousState.name, $scope.previousStateParams);
        }
    };
}]);
exapControllers.controller('CREATE_CRUDController', ['$scope', '$stateParams', function ($scope, $stateParams) {
    console.debug("CREATE_CRUDController");

    $scope.updateOperation($stateParams.operation);

}]);
exapControllers.controller('READ_CRUDController', ['$scope', '$stateParams', function ($scope, $stateParams) {
    console.debug("READ_CRUDController");

    $scope.updateOperation($stateParams.operation);
}]);
exapControllers.controller('UPDATE_CRUDController', ['$scope', '$stateParams', function ($scope, $stateParams) {
    console.debug("UPDATE_CRUDController");

    $scope.updateOperation($stateParams.operation);

}]);

exapControllers.controller('DeleteEntityModalController', ['$scope', '$modalInstance', function ($scope, $modalInstance) {
    $scope.cancel = function () {
        $modalInstance.dismiss();
    };
    $scope.confirm = function () {
        $modalInstance.close();
    };
}]);
