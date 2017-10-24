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

        var timeout = null;


        vm.loaded = false;

        vm.loadCommunityMember = function () {
            vm.loaded = false;
            kudosService.loadCommunityMember(vm.memberId)
                .then(function (kudos) {
                    alert(JSON.stringify(kudos));
                    vm.kudos = kudos;
                    vm.loaded = true;
                })
                .catch(function (error) {
                    $log.error("Got error: " + error);
                });
        };

        vm.titleText = function () {
            var result = vm.kudos.stageName;
            if (vm.kudos.birthday != null) {
                // var age = moment.utc(vm.kudos.birthday).fromNow().split(" ")[0];
                var ageDifMs = Date.now() - new Date(vm.kudos.birthday).getTime();
                var ageDate = new Date(ageDifMs)
                var age = Math.abs(ageDate.getUTCFullYear() - 1970)
                result += ", " + age;
            }
            return result;
        };


        vm.instruments = function () {
            if (vm.kudos !== null) {
                var instruments = _.map(vm.kudos.instruments, function (instrument) {
                    return instrument.name;
                });
                return instruments.slice(0, 5).join(", ");
            }
            return null;
        };

        vm.genres = function () {
            if (vm.genres !== null) {
                var genres = _.map(vm.kudos.genres, function (genre) {
                    return genre.name;
                });
                return genres.slice(0, 5).join(", ");
            }
            return null;
        };

        vm.onImageLoaded = function () {
            vm.loaded = true;
        };

        angular.element(document).ready(function () {
            vm.loadCommunityMember();
        });

    }
})();
