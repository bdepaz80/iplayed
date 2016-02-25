/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function() {
    var app = angular.module('iplayed', ['ngRoute', 'ngResource', 'iPlayedControllers', 'iPlayedDirectives']);

    google.setOnLoadCallback(function() {
      angular.bootstrap(document.body, ['iplayed']);
    });
    google.load('visualization', '1', {packages: ['corechart']});

    app.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                when('/statistics', {
                    templateUrl: 'pages/statistics.html',
                    controller: 'StatisticsController'
                }).
                when('/players', {
                    templateUrl: 'pages/players.html',
                    controller: 'PlayersController'
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
