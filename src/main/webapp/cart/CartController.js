app.directive('fileModel', ['$parse', function ($parse) {
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

.controller('CartController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {
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

    function getImage(image){
        $http.get("/file_management/"+image)
                    .then(
                        function (response) {
                            if (response.data) {
                                return response.data;
                            }
                        },
                        function (response) {
                            DialogService.generalServerError();
                        }
                    );
    }

    $scope.saveRecord = function(item) {
        var file = $scope.item.image;
        item.critical_value = 0;
        item.notifications_on = false;
        item.ordering_on = false;
        var fd = new FormData();
        fd.append('file', file);
            $http.post("/file_management/", fd, {
                headers: {'Content-Type': undefined },
                transformRequest: angular.identity})
                    .then(
                        function(response){
                         $http.post("/resource/create", JSON.stringify(item))
                                    .then(
                                        function(response){
                                                item = response.data;
                                                $scope.items.push({
                                                    id: item.id,
                                                    name: item.name,
                                                    description: item.description,
                                                    quantity: item.quantity,
                                                    image: item.image,
                                                    unit: item.unit
                                                });
                                        },
                                        function(response){
                                            DialogService.handle(response, 'resource', 'create');
                                        }
                                    );

                        },
                     function(response){
                        DialogService.handle(response, 'resource', 'image');
                    }
                );


    }

     $scope.list = {
            suppliers: []
        };



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