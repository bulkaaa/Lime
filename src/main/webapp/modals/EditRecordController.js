app.directive('validFile',function(){
  return {
    require:'ngModel',
    link:function(scope,el,attrs,ngModel){
      //change event is fired when file is selected
      el.bind('change',function(){
        scope.$apply(function(){
          ngModel.$setViewValue(el.val());
          ngModel.$render();
        });
      });
    }
  }
})
.directive('fileModel', ['$parse', function ($parse) {
       return {
           restrict: 'A',
           link: function(scope, element, attrs) {
               var model = $parse(attrs.fileModel);
               var modelSetter = model.assign;

               element.bind('change', function(){
                   scope.$apply(function(){
                       modelSetter(scope, element[0].files[0]);
                   });
               });
           }
       };
   }])
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

	};

    $scope.cancelModal = function(){
               modalInstance.close();
    };

	init();

}]);