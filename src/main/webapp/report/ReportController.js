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
        $http.post("/report/generate" + "?email=" + $scope.email + "&startDate=" + date.getDate()+"-"+date.getMonth()+1+"-"+date.getFullYear() + "&noDays=" + $scope.noDays, JSON.stringify(item))
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
  $scope.checkAll = function() {
    $scope.user.roles = $scope.roles.map(function(item) { return item.id; });
  };
  $scope.uncheckAll = function() {
    $scope.user.roles = [];
  };
  $scope.checkFirst = function() {
    $scope.user.roles.splice(0, $scope.user.roles.length);
    $scope.user.roles.push(1);
  };
    $scope.list = {
        products: []
    };
$scope.getProducts();


}]);