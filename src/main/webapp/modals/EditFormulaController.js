app.controller('EditFormulaController',  ['$scope', '$http','$uibModalInstance', function($scope, $http, modalInstance) {
    $scope.saveEmp = function () {
        $scope.newRecord = {};

        $scope.cancelModal();

        console.log(scope.resources)
        // if($scope.resources && $scope.list.resources){
        //     $scope.item.resources = $scope.list.resources;
        // }
        $scope.saveRecord($scope.item);
    };

    $scope.cancelModal = function(){
        modalInstance.close();
    }

}]);