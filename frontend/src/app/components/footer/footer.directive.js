(function () {
    'use strict';

    angular
        .module('kudos')
        .directive('kFooter', kFooter);

    /** @ngInject */
    function kFooter() {
        var directive = {
            restrict: 'E',
            templateUrl: 'app/components/footer/footer.html',
            controller: FooterController,
            controllerAs: 'kFooter',
            bindToController: true
        };

        return directive;

        /** @ngInject */
        function FooterController() {

        }
    }

})();
