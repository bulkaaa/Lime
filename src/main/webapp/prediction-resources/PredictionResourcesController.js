app.controller('PredictionResourcesController', ['$scope', '$http', 'dialogs', 'DialogService', function($scope, $http, $dialogs, DialogService) {
    datePickerId.max = new Date().toISOString().split("T")[0];
    datePickerId.default = new Date().toISOString().split("T")[0];
    daysPickerId.min = 1;
    daysForecastPickerId.min = 1;

    $scope.date = new Date();

    $scope.getResources = function() {
        $http.get("/resource/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.resources = response.data;
                        if (!$scope.resources.length)
                            $dialogs.notify('Currently there are no resources added in LIME', "You can add resources by clicking on 'Add New Resource' button on 'Products' view");
                    }
                },
                function (response) {
                    DialogService.handle(response, 'resource', 'all');
                }
            );
    };

    $scope.generateForecast = function () {
        var item = {};
        item = $scope.list.resources;
        var date = new Date($scope.date);
        var promise = DialogService.dialogWait();
        if (!$scope.list.resources.length)
            $dialogs.notify("Select resource","Please select at least one resource from list before generating a report");
        else if (!$scope.chartType)
            $dialogs.notify("Select chart type","Please select a type of chart before generating a report");
        else if (!$scope.noDays)
            $dialogs.notify("Select number of days","Please select number of days greater than 0 before generating a report");
        else if (!$scope.noDaysForecast)
            $dialogs.notify("Select number of days forecasted","Please select number of days forecasted greater than 0 before generating a report");
        else
            $http.post("/forecast/resource/generate" + "?startDate=" + date.getDate()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&noDaysForecast=" + $scope.noDaysForecast + "&chartType=" + $scope.chartType, JSON.stringify(item))
                .then(
                    function(response){
                        if (response.data){
                            promise.then(function(result) {
                                $scope.image = response.data;
                            });
                        }
                    },
                    function(response){
                        DialogService.handle(response, 'forecast', 'generate');
                    }
                );
    };

    $scope.sendForecast = function () {
        var item = {};
        item = $scope.list.resources;
        var date = new Date($scope.date);

        DialogService.dialogWait();
        if (!$scope.list.resources.length)
            $dialogs.notify("Select resource","Please select at least one resource from list before sending a report");
        else if (!$scope.chartType)
            $dialogs.notify("Select chart type","Please select a type of chart before sending a report");
        else if (!$scope.noDays)
            $dialogs.notify("Select number of days","Please select number of days greater than 0 before sending a report");
        else if (!$scope.noDaysForecast)
        $dialogs.notify("Select number of days forecasted","Please select number of days forecasted greater than 0 before sending a report");
        else if (!$scope.email)
            $dialogs.notify("Enter an email","Please enter a valid email address before sending a report");
        else
            $http.post("/forecast/resource/send" + "?email=" + $scope.email + "&startDate=" + date.getDate()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&noDaysForecast=" + $scope.noDaysForecast + "&chartType=" + $scope.chartType, JSON.stringify(item))
                .then(
                    function(response){
                        if (response.data){
                        }
                    },
                    function(response){
                        DialogService.handle(response, 'forecast', 'generate');
                    }
                );
    };

    $scope.list = {
        resources: []
    };
    $scope.getResources();


}]);