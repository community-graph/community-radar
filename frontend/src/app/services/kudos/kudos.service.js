(function () {
    'use strict';

    angular
        .module('kudos')
        .factory('kudosService', kudosService);

    /** @ngInject */
    function kudosService($log, $http, serviceConfig) {

        var loading = false;
        var service = {
            loadCommunityMember: loadCommunityMember,
            endpointUrl: endpointUrl,
            loading: loading
        };

        return service;

        function loadCommunityMember(memberId) {
            var url = service.endpointUrl(memberId);
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

        function endpointUrl(memberId) {
            if (memberId) {
                return serviceConfig.baseUrl + '/kudos/member/' + memberId;
            }
            else {
                return serviceConfig.baseUrl + '/kudos/random';
            }
        }
    }
})();

