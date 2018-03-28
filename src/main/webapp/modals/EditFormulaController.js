app.controller('EditFormulaController',  ['$scope', '$http','$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {
	function init(){
		$scope.item = item;
    }
	$scope.updateFormu = function () {
		$scope.cancelModal();

		$scope.updateFormula($scope.item);
	};

    $scope.cancelModal = function(){
               modalInstance.close();
    };

	init();

}]);