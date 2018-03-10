app.controller('CartController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {
    var modalInstance = null;
    $scope.resource = true;

    $scope.showAll = function() {
        $http.get("/resource/all")
            .then(
                function (response) {
                    if (response.data) {
                        console.log("updated record successfully!");
                        $scope.items = response.data;
                    }
                },
                function (response) {
                    DialogService.generalServerError();
                }
            );
    };

    $scope.showAll();

    $scope.viewRecord = function(item){

        modalInstance = $modal.open({
            templateUrl: 'modals/view-record.html',
            controller: 'ViewRecordController',
            scope: $scope,
            size: 'md',
            resolve: {
                item: function () {
                    return item; //response.data;
                }
            }
        });
    };

    $scope.editRecord = function(item){

        $http.get("/supplier/all")
                        .then(
                            function (response) {
                                if (response.data) {
                                   $scope.suppliersList = response.data;
                                }
                            },
                            function (response) {
                                DialogService.generalServerError();
                            }
                        );
        $http.get("resource/one/" + item.id)
            .then(function(response){
                $scope.item = item;
                modalInstance = $modal.open({
                    templateUrl: 'modals/edit-record.html',
                    controller: 'EditRecordController',
                    scope: $scope,
                    size: '',
                    resolve: {
                        item: function () {
                            return response.data;
                        }
                    }
                });
            });
    };

    $scope.updateRecord = function(item) {
        $http.put("/resource/update", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("updated record successfully!");
                        $scope.item.name = response.data.name;
                        $scope.item.description = response.data.description;
                        $scope.item.quantity = response.data.quantity;
                        $scope.item.unit = response.data.unit;
                        $scope.item.image = response.data.image;
                        $scope.item.supplier = response.data.supplier;
                    }
                },
                function(response){
                    DialogService.handle(response,'resource', 'update');
                }
            );
    };

    $scope.deleteRecord = function(id) {
        let dlg = $dialogs.confirm('Are you sure you want to delete this record?');
        dlg.result.then(function(btn){
            $http.delete("/resource/delete/" +id)
                .then(
                    function(response){
                        if (response.data){
                            console.log(response);
                            $scope.deleteRow(id);
                        }
                    },
                    function(response){
                        DialogService.generalServerError();
                    }
                );
        });
    };

    $scope.deleteRow = function(id) {

        document.getElementById(id).remove();
    };

    $scope.addRecord = function(){
        $scope.item={};
        $http.get("/supplier/all")
                .then(
                    function (response) {
                        if (response.data) {
                           $scope.suppliersList = response.data;
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
        item.critical_value = 0;
        item.notifications_on = false;
        item.ordering_on = false;
        $http.post("/resource/create", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("created resource successfully!");
                        item = response.data;

                        $scope.items.push({
                            id: item.id,
                            name: item.name,
                            description: item.description,
                            quantity: item.quantity,
                            image: item.image,
                            unit: item.unit
                        });
                    }
                },
                function(response){
                    DialogService.handle(response, 'resource', 'create');
                }
            );
    }

     $scope.list = {
            suppliers: []
        };

}]);