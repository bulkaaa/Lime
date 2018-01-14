app.controller('ReportController', ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {

$scope.products = function() {
        $http.get("/product/all")
            .then(
                function (response) {
                    if (response.data) {
                        console.log("updated product successfully!");
                        $scope.items = response.data;
                    }
                },
                function (response) {
                    alert("failure message: " + JSON.stringify(response));
                }
            );
    };

  $scope.roles = [
    {id: 1, text: 'guest'},
    {id: 2, text: 'user'},
    {id: 3, text: 'customer'},
    {id: 4, text: 'admin'}
  ];
  $scope.user = {
    roles: [2, 4]
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



}])