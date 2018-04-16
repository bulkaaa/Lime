app.controller('OrderController', ['$scope', '$rootScope', '$http', '$location', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $location, $modal, $dialogs, DialogService) {

    var modalInstance = null;

    $scope.getResources = function() {
        let counter = 0;
        $http.get("/resource/all")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.resources = response.data;
                        angular.forEach($scope.resources ,function(value, key){
                        if (value.ordering_on)
                                counter++;
                        });
                        if(counter == $scope.resources.length){
                             $('.OnButton').addClass('active');
                             $('.OffButton').removeClass('active');
                         }
                        if(counter !== $scope.resources.length){
                              $('.OffButton').addClass('active');
                              $('.OnButton').removeClass('active');
                         }
                        if (!$scope.resources.length)
                            $dialogs.notify('Currently there are no resources added in LIME', "You can add products here by clicking on 'Add New Product' button");
                    }
                },
                function (response) {
                    DialogService.generalServerError();
                }
            );
    };

    $scope.sendOrder = function () {
        let dlg = $dialogs.confirm('Order will be send to resource suppliers. Are you sure?');
        dlg.result.then(function(btn){
        var items = [];
        items = $scope.list.resources;

        var map = {};

        angular.forEach(items, function (value, key) {
            var inp = angular.element('#' + value.id).val();
            if(inp > 0)
                map[value.id] = angular.element('#' + value.id).val()
        });

        if(Object.keys(map).length === 0)
            $dialogs.error('Ups... Validation error', 'Please check your input');
        else
            $http.post("/order/send", JSON.stringify(map))
                .then(
                    function(response){
                        if (response.data){
                            $dialogs.notify('Order', 'Order was sent to resource suppliers');
                        }
                    },
                    function(response){
                        DialogService.handle(response, 'order', 'send');
                    }
                );

    });
    };

    $scope.NotificationsOn = function(){
                $http.get("/resource/toggle-order/true")
                    .then(
                        function (response) {
                        console.log("orders on");
                        },
                        function (response) {
                            DialogService.generalServerError();
                        }
                    );
            };

            $scope.NotificationsOff = function(){
               $http.get("/resource/toggle-order/false")
                   .then(
                       function (response) {
                         console.log("orders off");
                       },
                       function (response) {
                           DialogService.generalServerError();
                       }
                   );
            };



    $scope.list = {
        resources: []
    };
    $scope.getResources();

}]);