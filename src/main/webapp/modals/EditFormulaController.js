app.controller('EditFormulaController',  ['$scope', '$http','$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {
	function init(){
		$scope.item = item;
    }
	$scope.updateFormu = function () {
		$scope.cancelModal();

         if($scope.list && $scope.list.resources){
            $scope.item.resources = $scope.list.resources;
         }

		$scope.updateFormula($scope.item);
	};

    $scope.cancelModal = function(){
               modalInstance.close();
    };

    $scope.isChecked = (resource) => {
       return $scope.list.resources.indexOf(resource) == -1
    }
	init();

}]);