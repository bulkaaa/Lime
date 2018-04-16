app.controller('NotificationController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {
        var modalInstance = null;
        $scope.notifications = true;

        $scope.showAll = function() {
        let counter = 0;
            $http.get("/resource/all")
                .then(
                    function (response) {
                        if (response.data) {
                            $scope.items = response.data;
                            angular.forEach($scope.items ,function(value, key){
                            if (value.notifications_on)
                                    counter++;
                            });
                            if(counter == $scope.items.length){
                                 $('.OnButton').addClass('active');
                                 $('.OffButton').removeClass('active');
                             }
                            if(counter !== $scope.items.length){
                                  $('.OffButton').addClass('active');
                                  $('.OnButton').removeClass('active');
                             }
                         }
                    },
                    function (response) {
                        DialogService.generalServerError();
                    }
                );
        };

        $scope.showAll();


        $scope.editRecord = function(item){

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
                            $scope.item.critical_value = response.data.critical_value;
                            $scope.item.notifications_on = response.data.notifications_on;
                        }
                    },
                    function(response){
                        DialogService.handle(response,'resource', 'update');
                    }
                );
        };

        $scope.NotificationsOn = function(){
            $http.get("/resource/toggle-notification/true")
                .then(
                    function (response) {
                    angular.forEach($scope.items ,function(value, key){
                         value.notifications_on = 'true';
                         })
                    },
                    function (response) {
                        DialogService.generalServerError();
                    }
                );
        };

        $scope.NotificationsOff = function(){
           $http.get("/resource/toggle-notification/false")
               .then(
                   function (response) {
                   angular.forEach($scope.items ,function(value, key){
                        value.notifications_on = 'false';
                        })
                   },
                   function (response) {
                       DialogService.generalServerError();
                   }
               );
        };


    }]);
