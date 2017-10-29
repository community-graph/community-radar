(function () {
    'use strict';

    angular
        .module('kudos')
        .constant('serviceConfig', {baseUrl: 'http://localhost:8080'})
        .constant('kudosConfig', {cycleSeconds: 15});


})();
