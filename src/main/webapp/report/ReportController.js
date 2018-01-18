app.controller('ReportController', ['$scope', '$http', function($scope, $http) {

$scope.getProducts = function() {
        $http.get("/product/all")
            .then(
                function (response) {
                    if (response.data) {
                        console.log("updated product successfully!");
                        $scope.products = response.data;
                    }
                },
                function (response) {
                    alert("failure message: " + JSON.stringify(response));
                }
            );
    };

    $scope.generateReport = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);
        var url = "/report/generate" + "?startDate=" + date.getDate()+"-"+date.getMonth()+1+"-"+date.getFullYear() + "&noDays=" + $scope.noDays;
        $http.post(url, JSON.stringify(item))
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


    $scope.sendReport = function () {
        var item = {};
        item = $scope.list.products;
        var date = new Date($scope.date);
        var url = "/report/send" + "?email=" + $scope.email + "&startDate=" + date.getDate()+"-"+date.getMonth()+1+"-"+date.getFullYear() + "&noDays=" + $scope.noDays;
        $http.post(url, JSON.stringify(item))
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

    $scope.list = {
        products: []
    };
$scope.getProducts();


}]);