var iPlayedControllers = angular.module('iPlayedControllers', []);

iPlayedControllers.factory("Statistics", ['$resource', function($resource) {
   return $resource("resources/statistics/:Username",
        {Username: "@Username"}
   ); 
}]);

iPlayedControllers.controller('StatisticsController', ['$scope', 'Statistics', function($scope, Statistics){
    this.statistics = Statistics.get();
    //this.statistics = statistics;
}]);

iPlayedControllers.controller('PlayerController', ['$scope', 'Player', function($scope, Player){

}]);

iPlayedControllers.controller('AboutController', ['$scope', 'About', function($scope, About){

}]);


var statistics = {"totalMatches":3,"totalSets":9,"totalPoints":97,"totalPlayers":3,"playerWins":{"entry":[{"key":{"username":"bdepaz","firstname":"Bert","lastname":"Depaz"},"value":2},{"key":{"username":"dfranssen","firstname":"Dirk","lastname":"Franssen"},"value":1}]}};