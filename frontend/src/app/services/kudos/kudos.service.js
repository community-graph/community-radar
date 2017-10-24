(function () {
    'use strict';

    angular
        .module('kudos')
        .factory('kudosService', kudosService);

    /** @ngInject */
    function kudosService($log, $http, serviceConfig) {

        var endpointUrl = serviceConfig.baseUrl + '/kudos/member/';
        var loading = false;

        var service = {
            endpointUrl: endpointUrl,
            loadCommunityMember: loadCommunityMember,
            loading: loading
        };

        return service;

        function loadCommunityMember(memberId) {
            var url = endpointUrl + memberId;
            $log.debug("Loading community member " + url);
            service.loading = true;

            return $http({
                method: 'GET',
                url: url,
                headers: {
                    'content-type': 'application/json',
                    'API-Key': serviceConfig.apiKey
                }
            }).then(function(response) {
                service.loading = false;
                return response.data;
            }).catch(function(error) {
                $log.error('Error loading kudos:\n' + angular.toJson(error.data, true));
                return error;
            });

        }
    }
})();

