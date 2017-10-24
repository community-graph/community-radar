(function() {
  'use strict';

  angular
    .module('kudos')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $animate) {
    $animate.enabled(true);
    $log.debug("Animation is enabled? " + $animate.enabled());
    $log.debug('runBlock end');
  }

})();
