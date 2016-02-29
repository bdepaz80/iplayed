var iPlayedControllers = angular.module('iPlayedControllers', []);

iPlayedControllers.controller('NavigationController', ['$scope', '$location', function($scope, $location) {
    $scope.$location = $location;
}]);

iPlayedControllers.controller('StatisticsController', ['$scope', 'Statistics', function ($scope, Statistics) {
        $scope.statistics = Statistics.get();
        //this.statistics = statistics;

        $scope.chartTitle = "My Daily Activities";
        $scope.chartWidth = 500;
        $scope.chartHeight = 320;
    }]);

iPlayedControllers.controller('PlayersController', ['$scope', 'Players', function ($scope, Players) {
        $scope.players = Players.query();
    }]);

iPlayedControllers.controller('MatchesController', ['$scope', 'Matches', function ($scope, Matches) {

    }]);

iPlayedControllers.controller('AboutController', ['$scope', 'About', function ($scope, About) {
        
    }]);
