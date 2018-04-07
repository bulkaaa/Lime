app.controller('EditFormulaController',  ['$scope', '$http','$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {
	function init(){
		$scope.item = item;
    }
	$scope.updateFormu = function () {
         $scope.item.resources = [];
         if($scope.list && $scope.list.resources){
            angular.copy($scope.list.resources, $scope.item.resources);
         }
		$scope.cancelModal();

		$scope.updateFormula($scope.item, $scope.formula);
	};

    $scope.cancelModal = function(){
               modalInstance.close();
               $scope.list.resources.length = 0;
    };

    $scope.isUnchecked = function(resource) {
        return $scope.list.resources.findIndex(x=>x.id === resource.id) == -1;
    }

    $scope.getValue = function(resource){
        var res = 0;
        if (!$scope.isUnchecked(resource)){
            angular.forEach($scope.formula, function(value, key){
                 if( value.resource.id == resource.id)
                    res = value.value;
             });
        }
        return res;
    }

	init();

}]);