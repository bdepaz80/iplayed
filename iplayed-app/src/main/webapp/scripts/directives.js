var iPlayedDirectives = angular.module('iPlayedDirectives', []);

iPlayedDirectives.directive('chart', function ($timeout) {
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
            data.addColumn({'type':'string', 'label':'Player'});
            data.addColumn({'type':'number', 'label':'Wins'});
            data.addColumn({'type':'number','role':'annotation'});

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
                        var label, value, annotation;
                        data.removeRows(0, data.getNumberOfRows());
                        angular.forEach($scope.data, function (row) {
                            label = row.key.firstname + " " + row.key.lastname;
                            value = row.value;
                            annotation = row.value;
                            if (!isNaN(value)) {
                                data.addRow([label, value, annotation]);
                            }
                        });
                        var options = {'title': $scope.title,
                            'width': $scope.width,
                            'height': $scope.height,
                            'bar': {groupWidth: "95%"},
                            'hAxis': { textPosition: 'none' },
                            'legend': { position: "none" },
                        };
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
