app.controller('EditRecordController',  ['$scope', '$http', '$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {
	function init(){
		$scope.item = item;
		$scope.item.name = item.name;
		$scope.item.description = item.description;
        $scope.item.quantity = parseInt(item.quantity);
        $scope.item.unit = item.unit;
        $scope.item.image = item.image;
        $scope.item.critical_value = item.critical_value;
        $scope.item.notifications_on = item.notifications_on;
    }
	$scope.update = function () {
		$scope.cancelModal();
		if(/*!angular.isDefined($scope.record.name) ||*/ $scope.item.name === '') {
                return;
            }
            else if(/*!angular.isDefined($scope.record.description) ||*/ $scope.item.description === '') {
                return;
            }else if(/*!angular.isDefined($scope.record.value) ||*/ $scope.item.value === '') {
                return;
            }
            else if(/*!angular.isDefined($scope.record.value) ||*/ $scope.item.image === '') {
                return;
            }
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