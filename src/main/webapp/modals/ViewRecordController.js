app.controller('ViewRecordController',  ['$scope', '$http', '$uibModalInstance', 'item', 'formulas', function($scope, $http, modalInstance, item, formulas) {
	function init(){
        $scope.item = item;
        $scope.formulas = formulas;
        $scope.cancelModal = function(){
                   modalInstance.close();
        }
    }

	init();

}]);