app.controller('FormulaController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {
    var modalInstance = null;
    $scope.formula = true;

    $scope.showAll = function() {
        $http.get("/product/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.items = response.data;
                        //$scope.items.formulas = $scope.formulas;
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
        $http.get("/product/one/" + item.id)
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
        $http.put("/product/update", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("updated product successfully!");
                        $scope.item.name = response.data.name;
                        $scope.item.description = response.data.description;
                        $scope.item.expectedValue = response.data.expectedValue;
                        $scope.item.unit = response.data.unit;
                        $scope.item.image = response.data.image;
                    }
                },
                function(response){
                    DialogService.handle(response, 'product', 'update');
                }
            );
    };

    $scope.deleteRecord = function(id) {
        let dlg = $dialogs.confirm('Are you sure you want to delete this record?');
        dlg.result.then(function(btn){
            $http.delete("/product/delete/" +id)
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

        $http.post("/product/create", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("created PRODUCT successfully!");
                        item = response.data;
                        $scope.items.push({
                            id: item.id,
                            name: item.name,
                            description: item.description,
                            expectedValue: item.expectedValue,
                            image: item.image,
                            unit: item.unit
                        });
                    }
                },
                function(response){
                    DialogService.handle(response, 'product', 'create');
                }
            );
    }



    $('#filter_input').keyup(function(event){
        var txt = $(this).val()
        var cells = $('.name')

        cells.each(function(){
            var x = this.innerHTML;
            if(x.toLowerCase().includes(txt.toLowerCase())){
                $(this).parents('.row').show();
            } else {
                $(this).parents('.row').hide();
            }
        })
    })


}]);
