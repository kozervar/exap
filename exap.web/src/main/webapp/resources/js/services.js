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
var exapServices = angular.module('exap.services', ['ngResource']);

exapServices.value('version', '0.1');
exapServices.value('name', 'ExAp');

exapServices.factory('ExamType', ['$resource',
    function ($resource) {
        return $resource('rest/ExamType/:entityId', {}, {
            update: { method: 'PUT', params: {entityId: '@id'}},
            query: {method:'GET', params:{entityId:'@id'}, isArray:true},
            get: {method:'GET', params:{entityId:''}, isArray:true},
            remove: {method:'DELETE', params:{entityId:'@id'}}
        });
    }]);