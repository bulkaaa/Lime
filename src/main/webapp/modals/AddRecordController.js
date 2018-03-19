app.controller('AddRecordController',  ['$scope', '$http','$uibModalInstance', function($scope, $http, modalInstance) {
	$scope.saveEmp = function () {
		$scope.newRecord = {};

		$scope.cancelModal();

         if($scope.list && $scope.list.roles){
            $scope.item.roles = $scope.list.roles;
         }

         if($scope.list && $scope.list.products){
             $scope.item.product = $scope.list.products[0];
         }

         if($scope.list && $scope.list.suppliers){
             $scope.item.supplier = $scope.list.suppliers[0];
         }

         if($scope.item.startDate && $scope.item.endDate){
               $scope.item.startDate = new Date($scope.item.startDate).getTime();
               $scope.item.endDate = new Date($scope.item.endDate).getTime();
         }

		$scope.saveRecord($scope.item);
	 };

	 $scope.cancelModal = function(){
         modalInstance.close();
     }

}]);