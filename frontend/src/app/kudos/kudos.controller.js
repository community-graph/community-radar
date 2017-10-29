(function () {
    'use strict';

    angular
        .module('kudos')
        .controller('KudosController', KudosController);

    /** @ngInject */
    function KudosController(kudosService, $stateParams, $log, kudosConfig) {

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
                    if (!vm.memberId) {
                        setTimeout(function () {
                            vm.kudos = {};
                            vm.loadCommunityMember();
                        }, kudosConfig.cycleSeconds * 1000);
                    }

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
