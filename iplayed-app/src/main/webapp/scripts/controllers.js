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
        $scope.searchString;
        $scope.searchResult;
        $scope.search = function() {
            $scope.searchResult = Players.query({queryString: $scope.searchString});
            //$scope.searchResult = Players.get();
        };
    }]);

iPlayedControllers.controller('AboutController', ['$scope', 'About', function ($scope, About) {
        
    }]);
