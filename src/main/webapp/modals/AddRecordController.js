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

.controller('AddRecordController',  ['$scope', '$http','$uibModalInstance', function($scope, $http, modalInstance) {
    $scope.saveEmp = function () {
        $scope.newRecord = {};

        $scope.cancelModal();
        if($scope.list && $scope.list.roles){
            $scope.item.roles = $scope.list.roles;
        }

       // if($scope.list && $scope.list.supplier){
           // $scope.item.supplier = $scope.list.supplier[0];
      //  }

        if($scope.item.startDate && $scope.item.endDate){
            $scope.item.startDate = new Date($scope.item.startDate).getTime();
            $scope.item.endDate = new Date($scope.item.endDate).getTime();
        }

        $scope.saveRecord($scope.item);
    };

    $scope.cancelModal = function(){
        modalInstance.close();
    }
}]);