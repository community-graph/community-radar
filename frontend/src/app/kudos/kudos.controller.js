(function () {
    'use strict';

    angular
        .module('kudos')
        .controller('KudosController', KudosController);

    /** @ngInject */
    function KudosController(kudosService, $stateParams, _, $log) {

        var vm = this;

        vm.kudosService = kudosService;
        vm.memberId = $stateParams.id;
        vm.kudos = {};

        vm.loaded = false;

        vm.loadCommunityMember = function () {
            vm.loaded = false;
            kudosService.loadCommunityMember(vm.memberId)
                .then(function (kudos) {
                    vm.kudos = kudos;
                    vm.loaded = true;
                })
                .catch(function (error) {
                    $log.error("Got error: " + error);
                });
        };
        
        vm.onImageLoaded = function () {
            vm.loaded = true;
        };

        angular.element(document).ready(function () {
            vm.loadCommunityMember();
        });

    }
})();
