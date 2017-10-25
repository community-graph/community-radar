(function () {
    'use strict';

    angular
        .module('kudos')
        .directive('kHeader', kHeader);

    /** @ngInject */
    function kHeader() {
        var directive = {
            restrict: 'E',
            templateUrl: 'app/components/header/header.html',
            controller: HeaderController,
            controllerAs: 'kHeader',
            bindToController: true
        };

        return directive;

        /** @ngInject */
        function HeaderController() {

        }
    }

})();
