(function () {
  'use strict';

  angular
    .module('kudos')
    .config(config);

  /** @ngInject */
  function config($logProvider, $locationProvider) {
    // Enable log
    $logProvider.debugEnabled(true);
    
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: true
    });

  }

})();
