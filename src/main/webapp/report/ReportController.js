app.controller('ReportController', ['$scope', '$http', 'DialogService', function($scope, $http, DialogService) {

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

    $scope.generateReport = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);
        var url = "/report/generate" + "?startDate=" + date.getDate()+"-"+date.getMonth()+1+"-"+date.getFullYear() + "&noDays=" + $scope.noDays;
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
                    DialogService.generalServerError();
                }
            );
    };


    $scope.sendReport = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);
        var url = "/report/send" + "?email=" + $scope.email + "&startDate=" + date.getDate()+"-"+date.getMonth()+1+"-"+date.getFullYear() + "&noDays=" + $scope.noDays;
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
                    DialogService.generalServerError();
                }
            );
    };

    $scope.list = {
        products: []
    };
    $scope.getProducts();


}]);