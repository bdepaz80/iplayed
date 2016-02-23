var iPlayedControllers = angular.module('iPlayedControllers', []);

iPlayedControllers.factory("Statistics", ['$resource', function ($resource) {
        return $resource("resources/statistics/:Username",
                {Username: "@Username"}
        );
    }]);

iPlayedControllers.controller('StatisticsController', ['$scope', 'Statistics', function ($scope, Statistics) {
        $scope.statistics = Statistics.get();
        //this.statistics = statistics;

        $scope.chartTitle = "My Daily Activities";
        $scope.chartWidth = 500;
        $scope.chartHeight = 320;
    }]);

iPlayedControllers.controller('PlayerController', ['$scope', 'Player', function ($scope, Player) {

    }]);

iPlayedControllers.controller('AboutController', ['$scope', 'About', function ($scope, About) {

    }]);
