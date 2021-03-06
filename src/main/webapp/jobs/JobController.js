app.controller('JobController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {

var modalInstance = null;
    $scope.job = true;

    $scope.showAll = function() {
        $http.get("/job/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.items = response.data;
                        angular.forEach($scope.items ,function(value, key){
                                        value.startDate = new Date(value.startDate).toLocaleString();
                                        value.endDate = new Date(value.endDate).toLocaleString();
                                        value.username = value.user.username;
                                        value.productName = value.product.name;
                                    });
                        if (!$scope.items.length)
                            $dialogs.notify('Currently there are no products added in LIME', "You can add products here by clicking on 'Add New Product' button");
                    }
                },
                function (response) {
                    DialogService.handle(response, 'job', 'all');
                }
            );
    };

    $scope.showAll();




    $scope.addRecord = function(){
        $scope.item={};
                $http.get("/product/all")
                    .then(
                        function (response) {
                            if (response.data) {
                                $scope.products = response.data.slice();
                            }
                        },
                        function (response) {
                            DialogService.generalServerError();
                        }
                    );
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
                       item = response.data;
                       $scope.items.push({
                           id: item.id,
                           productName: item.product.name,
                           details: item.details,
                           resultValue: item.resultValue,
                           startDate: new Date(item.startDate).toLocaleString(),
                           endDate: new Date(item.endDate).toLocaleString(),
                           username: item.user.username
                       });
                   }
               },
               function(response){
                   DialogService.handle(response, 'job', 'create');
               }
           )
    }

}]);
