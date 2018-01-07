app.controller('EditRecordController',  ['$scope', '$http', '$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {
	function init(){
		$scope.item = item;
		$scope.item.name = item.name;
		$scope.item.description = item.description;
        $scope.item.quantity = parseInt(item.quantity);
        $scope.item.unit = item.unit;
        $scope.item.image = item.image;
    }
	$scope.update = function () {
		$scope.cancelModal();
		if(/*!angular.isDefined($scope.record.name) ||*/ $scope.item.name === '') {
                alert('item name is empty');
                return;
            }
            else if(/*!angular.isDefined($scope.record.description) ||*/ $scope.item.description === '') {
                alert('item description is empty');
                return;
            }else if(/*!angular.isDefined($scope.record.value) ||*/ $scope.item.value === '') {
                alert('item value is empty');
                return;
            }
            else if(/*!angular.isDefined($scope.record.value) ||*/ $scope.item.image === '') {
                alert('item image is empty');
                return;
            }
		$scope.updateRecord($scope.item);
	};

    $scope.cancelModal = function(){
               modalInstance.close();
    };

	init();

}]);