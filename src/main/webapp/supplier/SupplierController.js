app.controller('SupplierController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {
    var modalInstance = null;
    $scope.suppliers = true;

    $scope.showAll = function() {
        $http.get("/supplier/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.items = response.data;
                    }
                },
                function (response) {
                    DialogService.handle(response, 'supplier', 'all');
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
        $http.get("/supplier/one/" + item.id)
            .then(function(response){
                $scope.item = item;
                modalInstance = $modal.open({
                    templateUrl: 'modals/edit-record.html',
                    controller: 'EditRecordController',
                    backdrop: 'static',
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
        $http.put("/supplier/update", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        item = response.data;
                        $scope.item.name = response.data.name;
                        $scope.item.emailAddress = response.data.emailAddress;
                        $scope.item.postalCode = response.data.postalCode;
                        $scope.item.country = response.data.country;
                        $scope.item.street = response.data.street;
                        $scope.item.telephone = response.data.telephone;
                        $scope.item.city = response.data.city;
                    }
                },
                function(response){
                    DialogService.handle(response, 'supplier', 'update');
                }
            );
    };

    $scope.deleteRecord = function(id) {
        let dlg = $dialogs.confirm('Are you sure you want to delete this record?');
        dlg.result.then(function(btn) {
            $http.delete("/supplier/delete/" + id)
                .then(
                    function (response) {
                        if (response.data) {
                            console.log(response);
                            $scope.deleteRow(id);
                        }
                    },
                    function (response) {
                        DialogService.handle(response, 'supplier', 'delete');
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

        $http.post("/supplier/create", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("created supplier successfully!");
                        item = response.data;
                        $scope.items.push({
                            id: item.id,
                            name: item.name,
                            telephone: item.telephone,
                            city: item.city,
                            country: item.country,
                            postalCode: item.postalCode,
                            street: item.street,
                            emailAddress: item.emailAddress,
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
        var rows = $('.filter-row')

        rows.each(function(){
            var cells = $(this).find('.filter')
            var counter = 0;
            cells.each(function(){
                var x = this.innerHTML;

                if(x.toLowerCase().includes(txt.toLowerCase()))
                    counter++;
            })
            if(counter > 0)
                $(this).show();
            else
                $(this).hide();
        })
    })

}]);