(function () {
    'use strict';

    angular
        .module('kudos')
        .directive('vprFooter', vprFooter);

    /** @ngInject */
    function vprFooter() {
        var directive = {
            restrict: 'E',
            templateUrl: 'app/components/footer/footer.html',
            controller: FooterController,
            controllerAs: 'vprFooter',
            bindToController: true
        };

        return directive;

        /** @ngInject */
        function FooterController() {

        }
    }

})();
