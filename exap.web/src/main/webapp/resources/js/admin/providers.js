'use strict';

/* Providers */


var exapProviders = angular.module('exap-admin.providers', []);

//exapProviders.provider('OverviewEntityName', function() {
//    // In the provider function, you cannot inject any
//    // service or factory. This can only be done at the
//    // "$get" method.
//    var getEntityName = function (entityName) {
//        var Name = {};
//
//        Name.entityName = entityName;
//        var name = "NAZWA_ENCJI";
//
//        switch (entityName) {
//            case 'ExamType' :
//                name = 'Typ Egzaminu';
//                break;
//            case 'QuestionType' :
//                name = 'Typ Pytania';
//                break;
//            default :
//                name = 'NAZWA_ENCJI';
//                break;
//        }
//        Name.name = name;
//        return Name;
//    };
//
//    this.$get = function() {
//        var self = this;
//        return {
//            get: function(name) {
//                return self.getEntityName(name);
//            }
//        }
//    };
//});