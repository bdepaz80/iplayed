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

iPlayedControllers.directive('chart', function ($timeout) {
    return {
        restrict: 'EA',
        scope: {
            title: '@title',
            width: '@width',
            height: '@height',
            data: '=data',
            selectFn: '&select'
        },
        link: function ($scope, $elm, $attr) {
            // Create the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Player');
            data.addColumn('number', 'Victories');

            var chart = new google.visualization.BarChart($elm[0]);

            draw();

            $scope.$watch('data', function () {
                draw();
            }, true);
            $scope.$watch('title', function () {
                draw();
            });
            $scope.$watch('width', function () {
                draw();
            });
            $scope.$watch('height', function () {
                draw();
            });

            function draw() {
                if (!draw.triggered) {
                    draw.triggered = true;
                    $timeout(function () {
                        draw.triggered = false;
                        var label, value;
                        data.removeRows(0, data.getNumberOfRows());
                        angular.forEach($scope.data, function (row) {
                            label = row.key.username;
                            value = row.value;
                            if (!isNaN(value)) {
                                data.addRow([label, value]);
                            }
                        });
                        var options = {'title': $scope.title,
                            'width': $scope.width,
                            'height': $scope.height};
                        chart.draw(data, options);
                        // No raw selected
                        $scope.selectFn({selectedRowIndex: undefined});
                    }, 0, true);
                }
            }
            ;
        }
    }
});

iPlayedControllers.controller('PlayerController', ['$scope', 'Player', function ($scope, Player) {

    }]);

iPlayedControllers.controller('AboutController', ['$scope', 'About', function ($scope, About) {

    }]);
