app.controller('EditRecordController',  ['$scope', '$http', '$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {
	function init(){
		$scope.item = item;
    }
	$scope.update = function () {
		$scope.cancelModal();

         if($scope.list && $scope.list.roles){
            $scope.item.roles = $scope.list.roles;
         }
         if($scope.list && $scope.list.suppliers){
             $scope.item.supplier = $scope.list.suppliers[0];
            // angular.copy($scope.list.suppliers[0], $scope.item.supplier);
         }

		$scope.updateRecord($scope.item);

	};

    $scope.cancelModal = function(){
               modalInstance.close();
               $scope.list.suppliers.length = 0;
    };

	init();

}]);