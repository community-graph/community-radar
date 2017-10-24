(function () {
    'use strict';

    angular
        .module('kudos')
        .config(routerConfig);

    /** @ngInject */
    function routerConfig($stateProvider, $urlRouterProvider, $locationProvider) {

        $stateProvider
            .state('showProfile', {
                url: '/s/:id',
                templateUrl: 'app/kudos/kudos.html',
                controller: 'KudosController',
                controllerAs: 'vm'
            });

        $urlRouterProvider.otherwise('/s/86d35b40-6fcd-47a5-8054-de9f293cdfc8');

        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        })
    }

})();
