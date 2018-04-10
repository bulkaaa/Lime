app.controller('ResourceCategoryController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {
    var modalInstance = null;
    $scope.categoryResource = true;


     $scope.showAll = function() {
            $http.get("/resource-category/all")
                .then(
                    function (response) {
                        if (response.data) {
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
                    return item;
                }
            }
        });
    };

    $scope.editRecord = function(item){
        $http.get("/resource-category/one/" + item.id)
            .then(function(response){
                $scope.item = item;
                modalInstance = $modal.open({
                    templateUrl: 'modals/edit-record.html',
                    controller: 'EditRecordController',
                    scope: $scope,
                    size: '',
                    resolve: {
                        item: function () {
                            return item;
                        }
                    }
                });
            });
    };

    $scope.updateRecord = function(item) {
        $http.put("/resource-category/update", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        item = response.data;
                        $scope.item.name = response.data.name;
                    }
                },
                function(response){
                    DialogService.handle(response, 'category', 'update');
                }
            );
    };

    $scope.deleteRecord = function(id) {
        let dlg = $dialogs.confirm('Are you sure you want to delete this record?');
        dlg.result.then(function(btn) {
            $http.delete("/resource-category/delete/" + id)
                .then(
                    function (response) {
                        if (response.data) {
                            console.log(response);
                            $scope.deleteRow(id);
                        }
                    },
                    function (response) {
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

        $http.post("/resource-category/create", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("created product category successfully!");
                        item = response.data;
                        $scope.items.push({
                            id: item.id,
                            name: item.name,

                        });
                    }
                },
                function(response){
                    DialogService.handle(response, 'supplier', 'create');
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