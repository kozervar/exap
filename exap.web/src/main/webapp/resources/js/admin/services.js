'use strict';

/* Services */
/*
 { 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'} };
 * */

// Demonstrate how to register services
// In this case it is a simple value service.
var exapServices = angular.module('exap-admin.services', ['ngResource']);

exapServices.value('version', '0.0.1-SNAPSHOT');
exapServices.value('name', 'ExAp');

exapServices.factory('EntitiesMetaDataFactory', function () {
    function getByEntityName(entityName) {
        var entity = {};
        ENTITIES.forEach(function (entry) {
            if (entry.entityName == entityName) {
                entity = entry;
            }
        });
        return entity;
    }

    return {
        get: getByEntityName
    };
});
exapServices.factory('EntityNameFactory', function () {
    var getName = function (entityName) {
        var Name = {};

        Name.entityName = entityName;
        var name = "NAZWA_ENCJI";

        switch (entityName) {
            case 'ExamType' :
                name = 'Typ Egzaminu';
                break;
            case 'QuestionType' :
                name = 'Typ Pytania';
                break;
            case 'ExamPaper' :
                name = 'Egzamin';
                break;
            case 'QuestionDetail' :
                name = 'Szczegóły pytania';
                break;
            case 'QuestionAnswer' :
                name = 'Odpowiedź na pytanie';
                break;
            case 'Question' :
                name = 'Pytania';
                break;
            case 'SubmitQuestionHeader' :
                name = 'Odpowiedź na pytanie';
                break;
            case 'SubmitQuestionAnswer' :
                name = 'Szczegóły odpowiedzi';
                break;
            case 'ExamPaperQuestion' :
                name = 'Egzamin - Odpowiedzi';
                break;
            default :
                name = 'NAZWA_ENCJI';
                break;
        }
        Name.name = name;
        return Name;
    };
    return { get: getName };
});

exapServices.factory('RESTfacade', ['$resource',
    function ($resource) {
        return $resource('rest/:entityName/:entityId', {}, {
            update: { method: 'PUT', params: {entityName: '@entityName', entityId: '@id'}},
            query: {method: 'GET', params: {entityName: '@entityName'}, isArray: true},
            get: {method: 'GET', params: {entityName: '@entityName', entityId: '@id'}, isArray: false},
            remove: {method: 'DELETE', params: {entityName: '@entityName', entityId: '@id'}},
            save: {method: 'POST', params: {entityName: '@entityName'}}
        });
    }]);
// success & error parameters: data, status, headers, config
exapServices.factory('QuestionFactory', ['$http', function ($http) {
    var urlBase = 'rest/crud/Question';
    var dataFactory = {};

    dataFactory.createQuestion = function (entity, success, error) {
        return $http.post(urlBase, entity).success(success).error(error);
    };

    dataFactory.getQuestions = function (success, error) {
        return $http.get(urlBase).success(success).error(error);
    };

    dataFactory.getQuestionsByTags = function (tags, success, error) {
        return $http.post(urlBase + "/bytags", tags).success(success).error(error);
    };

    dataFactory.getQuestion = function (id, success, error) {
        return $http.get(urlBase + '/' + id).success(success).error(error);
    };

    dataFactory.updateQuestion = function (entity, success, error) {
        return $http.put(urlBase + '/' + entity.question.id, entity).success(success).error(error);
    };

    dataFactory.deleteQuestion = function (id, success, error) {
        return $http.delete(urlBase + '/' + id).success(success).error(error);
    };

    dataFactory.getQuestionDetails = function (id, success, error) {
        return $http.get(urlBase + '/' + id + '/questionDetails').success(success).error(error);
    };

    dataFactory.getAvailableTags = function (success, error) {
        return $http.get(urlBase + '/tags').success(success).error(error);
    };

    dataFactory.getAvailableTagsByQuery = function (query, success, error) {
        var p = $http.get(urlBase + '/tagsquery?query=' + query);
        p.success(success);
        p.error(error);
        return p;
    };

    dataFactory.getQuestionTags = function (id, success, error) {
        return $http.get(urlBase + '/' + id + '/tags').success(success).error(error);
    };

    dataFactory.getQuestionTagsByQuery = function (id, query, success, error) {
        return $http.get(urlBase + '/' + id + '/tagsquery?query=' + query).success(success).error(error);
    };


    return dataFactory;
}]);

exapServices.factory('QuestionTypeFactory', ['$http', function ($http) {
    var urlBase = 'rest/QuestionType';
    var dataFactory = {};

    dataFactory.createQuestionType = function (entity, success, error) {
        return $http.post(urlBase, entity).success(success).error(error);
    };

    dataFactory.getQuestionTypes = function (success, error) {
        return $http.get(urlBase).success(success).error(error);
    };

    dataFactory.getQuestionType = function (id, success, error) {
        return $http.get(urlBase + '/' + id).success(success).error(error);
    };

    dataFactory.updateQuestionType = function (entity, success, error) {
        return $http.put(urlBase + '/' + entity.id, entity).success(success).error(error);
    };

    dataFactory.deleteQuestionType = function (id, success, error) {
        return $http.delete(urlBase + '/' + id).success(success).error(error);
    };

    return dataFactory;
}]);

exapServices.factory('TagFactory', ['$http', function ($http) {
    var urlBase = 'rest/tags';
    var dataFactory = {};

    dataFactory.getTags = function (success, error) {
        return $http.get(urlBase).success(success).error(error);
    };

    dataFactory.getTagsByQuery = function (query, success, error) {
        var p = $http.get(urlBase + '/query?query=' + query);
        p.success(success);
        p.error(error);
        return p;
    };

    return dataFactory;
}]);

exapServices.service('questionTags', [ '$q', 'QuestionFactory', function ($q, QuestionFactory) {

    var promise = QuestionFactory.getAvailableTagsByQuery(query, function (data, status, headers, config) {
        console.log(data)
    });

    this.load = function () {
        var deferred = $q.defer();
        deferred.resolve(promise);
        return deferred.promise;
    };
}]);
