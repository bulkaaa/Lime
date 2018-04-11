app.controller('ViewRecordController',  ['$scope', '$http', '$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {
	function init(){

        $scope.item = item;
        if($scope.formula)
            var formulas = [];
            $http.get("/formula/one/" + item.id)
                        .then(
                            function (response) {
                                 formulas = response.data;
                                 $scope.formulas = formulas;
                            },
                            function(response)  {
                                  DialogService.generalServerError();
                            }
                        )

        $scope.cancelModal = function(){
                   modalInstance.close();
        }
    }

	init();

}]);