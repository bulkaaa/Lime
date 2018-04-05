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



.controller('ProductController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {
    var modalInstance = null;
    $scope.showAll = function() {
        $http.get("/product/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.items = response.data;
                        if (!$scope.items.length)
                            $dialogs.notify('Currently there are no products added in LIME', "You can add products here by clicking on 'Add New Product' button");
                            angular.forEach($scope.items ,function(value, key){
                                     var path = "/file_management/" + value.image;
                                         $http.get(path)
                                             .then(
                                                 function (res) {
                                                     value.image = res.data;
                                                 },
                                                 function (response) {
                                                     DialogService.generalServerError();
                                                 }
                                             )
                            })
                    }
                },
                function (response) {
                    DialogService.generalServerError();
                }
            );
    };

    $scope.showAll();

    $scope.viewRecord = function(item){
        $scope.switchToProduct()
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
        $scope.switchToProduct()
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
        $scope.switchToProduct()
            var file = item.image;
          //  var ext = string.substring(item.image.name.lastIndexOf("."));
            item.image = item.image.name;
            item.quantity = 10;
            item.notifications_on = false;
            item.critical_value = 5;
            var fd = new FormData();
            fd.append('file', file);
                $http.post("/file_management/", fd, {
                    headers: {'Content-Type': undefined },
                    transformRequest: angular.identity})
                        .then(function(response){
                            $http.put("/product/update", JSON.stringify(item))
                                .then(
                                    function(response){
                                        if (response.data){
                                            var path = "/file_management/" + response.data.image;
                                            $scope.item.name = response.data.name;
                                            $scope.item.description = response.data.description;
                                            $scope.item.expectedValue = response.data.expectedValue;
                                            $scope.item.unit = response.data.unit;
                                             if($scope.item.image){
                                             $http.get(path)
                                                    .then(
                                                        function (res) {
                                                            $scope.item.image = res.data;
                                                        },
                                                        function (response) {
                                                            DialogService.generalServerError();
                                                        }
                                                    )
                                             }
                                        }
                                    },
                                    function(response){
                                        DialogService.handle(response, 'product', 'update');
                                    }
                                );
                            },
                            function(response){
                                DialogService.handle(response,'product', 'update');
                            }
                        );
    };

    $scope.deleteRecord = function(id) {
        $scope.switchToProduct()
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



    $scope.viewFormula = function(item){
        $scope.switchToFormula();
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

     $scope.list = {
            resources: []
        };

    $scope.editFormula = function(item){
        $scope.switchToFormula()
        $http.get("/product/one/" + item.id)
            .then(function(response){
                $scope.item = item;
                $http.get("/resource/all")
                    .then(
                        function (response) {
                            if (response.data) {
                                $scope.resourcesList = response.data;
                                var formulas = [];
                                $http.get("/formula/one/" + item.id)
                                            .then(
                                                function (response) {
                                                 angular.forEach(response.data ,function(value, key){
                                                     $scope.list.resources.push
                                                     (value.resource);
                                                     })
                                                },
                                                function(response)  {
                                                      DialogService.generalServerError();
                                                }
                                            )
                            }
                        },
                        function (response) {
                            DialogService.generalServerError();
                        }
                    );

                modalInstance = $modal.open({
                    templateUrl: 'modals/edit-formula.html',
                    controller: 'EditFormulaController',
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

    $scope.updateFormula = function(item){
            $scope.switchToProduct()
            var items = [];
            items = $scope.list.resources;
            var formulas = [];

            angular.forEach(items, function (value, key) {
                var inp = angular.element('#' + value.id).val();
                if(inp > 0){
                     formulas.push({
                        product: {id: item.id},
                        resource: value,
                        value: inp
                     });
                }

            });

            if(Object.keys(formulas).length === 0)
                $dialogs.error('Ups... Validation error', 'Please check your input');
            else
                $http.put("/formula/update", JSON.stringify(formulas))
                    .then(
                        function(response){
                            if (response.data){

                            }
                        },
                        function(response){
                            DialogService.handle(response, 'formula', 'edit');
                        }
                    );
    }



    $scope.addRecord = function(){
        $scope.switchToProduct()
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

    $scope.switchToProduct = function(){
        $scope.product = true;
        $scope.formula = false;
    }


    $scope.switchToFormula = function(){
        $scope.product = false;
        $scope.formula = true;
    }

    $scope.saveRecord = function(item) {
        var file = $scope.item.image;
        item.image = $scope.item.image.name;
        item.quantity = 10;
        item.notifications_on = false;
        item.critical_value = 5;
        var fd = new FormData();
         fd.append('file', file);
                    $http.post("/file_management/", fd, {
                        headers: {'Content-Type': undefined },
                        transformRequest: angular.identity})
                            .then(
                                function(response){
                                    $http.post("/product/create", JSON.stringify(item))
                                        .then(
                                            function(response){
                                                    item = response.data;
                                                    var path = "/file_management/" + item.image;
                                                    $http.get(path)
                                                            .then(
                                                                function (response) {
                                                                    item.image = response.data;
                                                                    $scope.items.push({
                                                                        id: item.id,
                                                                        name: item.name,
                                                                        description: item.description,
                                                                        expectedValue: item.expectedValue,
                                                                        image: item.image,
                                                                        unit: item.unit,
                                                                    });
                                                                },
                                                                function(response){
                                                                    DialogService.handle(response, 'product', 'create');
                                                                }
                                                            );
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