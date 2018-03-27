app.controller('ViewFormulaController',  ['$scope', '$http', '$uibModalInstance', 'item', function($scope, $http, modalInstance, item) {
    function init(){
        $scope.item = item;
        $scope.cancelModal = function(){
            modalInstance.close();
        }
    }

    init();

}]);