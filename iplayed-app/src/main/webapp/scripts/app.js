/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function() {
    var app = angular.module('iplayed', []);
    
    app.controller('StatisticsController', function(){
        this.statistics = statistics;
    });
    
    var statistics = {"totalMatches":3,"totalSets":9,"totalPoints":97,"totalPlayers":3,"playerWins":{"entry":[{"key":{"username":"bdepaz","firstname":"Bert","lastname":"Depaz"},"value":2},{"key":{"username":"dfranssen","firstname":"Dirk","lastname":"Franssen"},"value":1}]}};
})();