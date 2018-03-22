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

.controller('CartController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', '$sanitize', function($scope, $rootScope, $http, $modal, $dialogs, DialogService, $sanitize) {
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


    $scope.img = null;

    function getImage(image){
    var path = "/file_management/" + image;
        $http.get(path)
                    .then(
                        function (response) {
                             //var bytearray = new Uint8Array(response.data);
                            var blob = new Blob([response.data], {type: 'image/jpg'});
                             //return arrayBufferToBase64(response.data);
                            // console.log(typeof(response.data));
                            //image = btoa(String.fromCharCode.apply(null, response.data.ClassImage.data));
                            //return blob;
                           //var dataNow64bit = arrayBufferToBase64(blob);
                           var reader = new FileReader();
                            reader.readAsDataURL(blob);
                            reader.onloadend = function() {
                                var base64data = reader.result;
                            }
                            //image = reader.result;
                          //$scope.item.image = image;
                            $scope.img = reader.result;
                        },
                        function (response) {
                            DialogService.generalServerError();
                        }
                    );
    }

    $scope.saveRecord = function(item) {
        var file = $scope.item.image;
        item.image = $scope.item.image.name;
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
                                                var path = "/file_management/" + item.image;
                                                $http.get(path)
                                                        .then(
                                                            function (response) {
                                                                var blob = new Blob([response.data], {type: 'image/jpg'});
                                                                var reader = new FileReader();
                                                                reader.readAsDataURL(blob);
                                                                reader.onloadend = function() {
                                                                     $scope.img = reader.result;
                                                                 }
                                                                $scope.items.push({
                                                                    id: item.id,
                                                                    name: item.name,
                                                                    description: item.description,
                                                                    quantity: item.quantity,
                                                                    image: $sanitize($scope.img),
                                                                    unit: item.unit,
                                                                });

                                                            },
                                                            function (response) {
                                                                DialogService.generalServerError();
                                                            }
                                                        )
                                               /* $scope.items.push({
                                                    id: item.id,
                                                    name: item.name,
                                                    description: item.description,
                                                    quantity: item.quantity,
                                                    image: $scope.img,
                                                    unit: item.unit,
                                                });*/
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