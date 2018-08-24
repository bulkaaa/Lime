app.controller('ReportResourcesController', ['$scope', '$http', 'dialogs', 'DialogService', function($scope, $http, $dialogs, DialogService) {
    datePickerId.max = new Date().toISOString().split("T")[0];
    datePickerId.default = new Date().toISOString().split("T")[0];
    daysPickerId.min = 1;

    $scope.date = new Date();

    $scope.getResources = function() {
        $http.get("/resource/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.resources = response.data;
                        if (!$scope.resources.length)
                            $dialogs.notify('Currently there are no resources added in LIME', "You can add products by clicking on 'Add New Resource' button on 'Products' view");
                    }
                },
                function (response) {
                    DialogService.handle(response, 'resource', 'all');
                }
            );
    };

    $scope.generateReport = function () {
        var item = {};
        item = $scope.list.resources;
        var date = new Date($scope.date);
        var url = "/report/resource/generate" + "?startDate=" + date.getDate()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&chartType=" + $scope.chartType;

        if (!$scope.list.resources.length)
            $dialogs.notify("Select resource","Please select at least one resource from list before generating a report");
        else if (!$scope.chartType)
            $dialogs.notify("Select chart type","Please select a type of chart before generating a report");
        else if (!$scope.noDays)
            $dialogs.notify("Select number of days","Please select number of days greater than 0 before generating a report");
        else
            var promise = DialogService.dialogWait();
            $http.post(url, JSON.stringify(item))
                .then(
                    function(response){
                        if (response.data){
                            promise.then(function(result) {
                                $scope.image = response.data;
                            });
                        }
                    },
                    function(response){
                        DialogService.handle(response, 'report', 'generate');
                    }
                );
    };


    $scope.sendReport = function () {
        var item = {};
        item = $scope.list.resources;
        var date = new Date($scope.date);
        var url = "/report/resource/send" + "?email=" + $scope.email + "&startDate=" + date.getDate()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&chartType=" + $scope.chartType;

        if (!$scope.list.resources.length)
            $dialogs.notify("Select resource","Please select at least one resource from list before sending a report");
        else if (!$scope.chartType)
            $dialogs.notify("Select chart type","Please select a type of chart before sending a report");
        else if (!$scope.noDays)
            $dialogs.notify("Select number of days","Please select number of days greater than 0 before sending a report");
        else if (!$scope.email)
            $dialogs.notify("Enter an email","Please enter a valid email address before sending a report");
        else
            var promise = DialogService.dialogWait();
            $http.post(url, JSON.stringify(item))
                .then(
                    function(response){
                        if (response.data){
                            promise.then(function(result) {
                                $scope.image = response.data;
                            });
                        }
                    },
                    function(response){
                        DialogService.handle(response, 'report', 'generate');
                    }
                );
    };

    $scope.list = {
        resources: []
    };
    $scope.getResources();


}]);