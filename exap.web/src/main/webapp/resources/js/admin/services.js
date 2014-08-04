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
