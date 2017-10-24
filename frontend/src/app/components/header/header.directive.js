(function () {
    'use strict';

    angular
        .module('kudos')
        .directive('vprHeader', vprHeader);

    /** @ngInject */
    function vprHeader() {
        var directive = {
            restrict: 'E',
            templateUrl: 'app/components/header/header.html',
            controller: HeaderController,
            controllerAs: 'vprHeader',
            bindToController: true
        };

        return directive;

        /** @ngInject */
        function HeaderController() {

        }
    }

})();
