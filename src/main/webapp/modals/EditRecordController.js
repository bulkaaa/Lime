app.controller('EditRecordController',  ['$scope', '$http', '$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {

	function init(){
		$scope.item = item;
    }
	$scope.update = function () {
		$scope.cancelModal();

         if($scope.list && $scope.list.roles){
            $scope.item.roles = $scope.list.roles;
         }

		$scope.updateRecord($scope.item);

	};

    $scope.cancelModal = function(){
               modalInstance.close();
    };

	init();

}]);