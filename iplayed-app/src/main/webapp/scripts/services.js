var iPlayedServices = angular.module('iPlayedServices', []);

//Resources
iPlayedServices.factory("Statistics", ['$resource', function ($resource) {
        return $resource("resources/statistics/:username",
                {Username: "@username"}
        );
    }]);
iPlayedServices.factory("Players", ['$resource', function ($resource) {
        return $resource("resources/players", {}, {
            query: {method: 'GET', params:{queryString: 'queryString'}, isArray:true}
        });
    }]);