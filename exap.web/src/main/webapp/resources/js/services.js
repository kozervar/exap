'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var exapServices = angular.module('exap.services', []);

exapServices.value('version', '0.1');
exapServices.value('name', 'ExAp');

