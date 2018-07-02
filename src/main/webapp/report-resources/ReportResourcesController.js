app.controller('ReportResourcesController', ['$scope', '$http', 'DialogService', function($scope, $http, DialogService) {

    $scope.chartType = "Bar";
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