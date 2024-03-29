(function () {
    'use strict';

    angular
        .module('kudos')
        .constant('serviceConfig', {baseUrl: 'https://kotlin-kudos-backend.herokuapp.com' || 'http://localhost:8080'})
        .constant('kudosConfig', {
            defaultDescription: 'Kotlin community member',
            defaultImage: 'https://cdn.worldvectorlogo.com/logos/kotlin.svg',
            defaultHashTag: '#kotlin',
            cycleSeconds: 15
        });
})();

