/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function() {
    var app = angular.module('iplayed', ['ngRoute', 'ngResource', 'iPlayedControllers']);
    
    app.config(['$routeProvider', 
        function($routeProvider) {
            $routeProvider.
                when('/statistics', {
                    templateUrl: 'pages/statistics.html',
                    controller: 'StatisticsController'
                }).
                when('/player', {
                    templateUrl: 'pages/player.html',
                    controller: 'PlayerController'                        
                }).
                when('/about', {
                    templateUrl: 'pages/about.html',
                    controller: 'AboutController'                        
                }).
                otherwise({
                    redirectTo: 'statistics'
                });
        }]);
})();