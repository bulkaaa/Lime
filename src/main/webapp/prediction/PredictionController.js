app.controller('PredictionController', ['$scope', '$http', function($scope, $http) {

$scope.getProducts = function() {
        $http.get("/product/all")
            .then(
                function (response) {
                    if (response.data) {
                        console.log("fetched product successfully!");
                        $scope.products = response.data;
                    }
                },
                function (response) {
                    alert("failure message: " + JSON.stringify(response));
                }
            );
    };

    $scope.generateForecast = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);

        $http.post("/forecast/generate" + "?startDate=" + date.getDate()+"-"+date.getMonth()+1+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&noDaysForecast=" + $scope.noDaysForecast, JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        $scope.image = response.data;
                    }
                },
                function(response){
                    alert( "failure message: " + JSON.stringify(response));
                }
            );
    };

    $scope.sendForecast = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);

        $http.post("/forecast/send" + "?email=" + $scope.email + "&startDate=" + date.getDate()+"-"+date.getMonth()+1+"-"+date.getFullYear() + "&noDays=" + $scope.noDays + "&noDaysForecast=" + $scope.noDaysForecast, JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                    }
                },
                function(response){
                    alert( "failure message: " + JSON.stringify(response));
                }
            );
    };

    $scope.list = {
        products: []
    };
$scope.getProducts();


}]);