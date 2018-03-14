app.controller('JobController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {

var modalInstance = null;
    $scope.job = true;

    $scope.showAll = function() {
        $http.get("/job/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.items = response.data;
                        if (!$scope.items.length)
                            $dialogs.notify('Currently there are no products added in LIME', "You can add products here by clicking on 'Add New Product' button");
                    }
                },
                function (response) {
                    DialogService.generalServerError();
                }
            );
    };

    $scope.showAll();


    $scope.addRecord = function(){
        $scope.item={};
        modalInstance = $modal.open({
            templateUrl: 'modals/add-record.html',
            controller: 'AddRecordController',
            scope: $scope,
            size: '',
            resolve: {
            }
        });
    };

    $scope.saveRecord = function(item) {

        $http.post("/job/create", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("created PRODUCT successfully!");
                        item = response.data;
                        $scope.items.push({
                            id: item.id,
                            productName: item.product,
                            jobDescription: item.details,
                            resultValue: item.resultValue,
                            startDate: item.startDate,
                            endDate: item.endDate,
                        });
                    }
                },
                function(response){
                    DialogService.handle(response, 'product', 'create');
                }
            );
    }

}]);
