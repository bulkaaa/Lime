app.controller('PredictionResourcesController', ['$scope', '$http', 'dialogs', 'DialogService', function($scope, $http, $dialogs, DialogService) {

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