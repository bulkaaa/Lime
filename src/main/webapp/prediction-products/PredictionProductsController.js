app.controller('PredictionProductsController', ['$scope', '$http', 'dialogs', 'DialogService', function($scope, $http, $dialogs, DialogService) {
    datePickerId.max = new Date().toISOString().split("T")[0];
    datePickerId.default = new Date().toISOString().split("T")[0];
    daysPickerId.min = 1;
    daysForecastPickerId.min = 1;

    $scope.date = new Date();

    $scope.getProducts = function() {
        $http.get("/product/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.products = response.data;
                        if (!$scope.products.length)
                            $dialogs.notify('Currently there are no products added in LIME', "You can add products by clicking on 'Add New Product' button on 'Products' view");
                    }
                },
                function (response) {
                    DialogService.handle(response, 'product', 'all');
                }
            );
    };

    $scope.generateForecast = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);

        var promise = DialogService.dialogWait();
        if (!$scope.list.products.length)
            $dialogs.notify("Select product","Please select at least one product from list before generating a report");
        else if (!$scope.chartType)
            $dialogs.notify("Select chart type","Please select a type of chart before generating a report");
        else if (!$scope.noDays)
            $dialogs.notify("Select number of days","Please select number of days greater than 0 before generating a report");
        else if (!$scope.noDaysForecast)
            $dialogs.notify("Select number of days forecasted","Please select number of days forecasted greater than 0 before generating a report");
        else
            $http.post("/forecast/product/generate" + "?startDate=" + date.getDate()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&noDaysForecast=" + $scope.noDaysForecast + "&chartType=" + $scope.chartType, JSON.stringify(item))
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
        item = $scope.list.products;
        var date = new Date($scope.date);

        DialogService.dialogWait();
        if (!$scope.list.products.length)
            $dialogs.notify("Select product","Please select at least one product from list before sending a report");
        else if (!$scope.chartType)
            $dialogs.notify("Select chart type","Please select a type of chart before sending a report");
        else if (!$scope.noDays)
            $dialogs.notify("Select number of days","Please select number of days greater than 0 before sending a report");
        else if (!$scope.noDaysForecast)
            $dialogs.notify("Select number of days forecasted","Please select number of days forecasted greater than 0 before sending a report");
        else if (!$scope.email)
            $dialogs.notify("Enter an email","Please enter a valid email address before sending a report");
        else
            $http.post("/forecast/product/send" + "?email=" + $scope.email + "&startDate=" + date.getDate()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&noDaysForecast=" + $scope.noDaysForecast + "&chartType=" + $scope.chartType, JSON.stringify(item))
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
        products: []
    };
    $scope.getProducts();


}]);