app.controller('PredictionProductsController', ['$scope', '$http', 'dialogs', 'DialogService', function($scope, $http, $dialogs, DialogService) {

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
                    DialogService.generalServerError();
                }
            );
    };

    $scope.generateForecast = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);
        var promise = DialogService.dialogWait();
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
                    DialogService.generalServerError();
                }
            );
    };

    $scope.sendForecast = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);

        DialogService.dialogWait();
        $http.post("/forecast/product/send" + "?email=" + $scope.email + "&startDate=" + date.getDate()+"-"+(parseInt(date.getMonth())+1)+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&noDaysForecast=" + $scope.noDaysForecast + "&chartType=" + $scope.chartType, JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                    }
                },
                function(response){
                    DialogService.generalServerError();
                }
            );
    };

    $scope.list = {
        products: []
    };
    $scope.getProducts();


}]);