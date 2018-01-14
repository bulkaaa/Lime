app.controller('ReportController', ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {

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

$scope.getProducts();


}]);