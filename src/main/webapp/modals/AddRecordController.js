app.controller('AddRecordController',  ['$scope', '$http','$uibModalInstance', function($scope, $http, modalInstance) {
	$scope.saveEmp = function () {
		$scope.newRecord = {};

		if(/*!angular.isDefined($scope.employee_name) ||*/ $scope.item.name === '') {
			alert('resource name is empty');
			return;
		}
		else if(/*!angular.isDefined($scope.employee_age) || */$scope.item.description === '') {
			alert('resource description is empty');
			return;
		}else if(/*!angular.isDefined($scope.employee.salary) ||*/ $scope.item.value === '') {
			alert('resource value is empty');
			return;
		} else {
			$scope.newRecord.name = $scope.item.name;
			$scope.newRecord.description = $scope.item.description;
			$scope.newRecord.value = $scope.item.value;
		}
		$scope.cancelModal();
		$scope.saveRecord($scope.newRecord);
	};

	 $scope.cancelModal = function(){
         modalInstance.close();
     }

}]);