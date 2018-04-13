app
.directive('bindEvent', function() {
      return {
        restrict: 'EAC',
        controller: function($scope, $element, $attrs) {

          $element.on('customEvent', function() {
            console.log('custom event is triggered');
          });
        }
      };
    })


.controller('EditRecordController',  ['$scope', '$http', '$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {

	function init(){
		$scope.item = item;
    }
	$scope.update = function () {
		$scope.cancelModal();

         if($scope.list && $scope.list.roles){
            $scope.item.roles = $scope.list.roles;
         }

		$scope.updateRecord($scope.item);
        angular.element('input').triggerHandler( "customEvent" );

	};

    $scope.cancelModal = function(){
               modalInstance.close();
    };

	init();

}]);