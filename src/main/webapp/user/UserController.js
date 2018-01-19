app.controller('UserController', ['$scope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $http, $modal, $dialogs, DialogService) {
    var modalInstance = null;

    $scope.showAll = function() {
        $http.get("/user/all")
            .then(
                function (response) {
                    if (response.data) {
                        console.log("updated users successfully!");
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
        $http.get("/user/" + item.id)
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
        $http.put("/user/update", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("updated user successfully!");
                        $scope.item.name = response.data.name;
                        $scope.item.surname = response.data.surname;
                        $scope.item.roles = response.data.roles;
                        $scope.item.emailAddress = response.data.emailAddress;
                    }
                },
                function(response){
                    DialogService.handle(response, 'user', 'update');
                }
            );
    };

    $scope.deleteRecord = function(id) {
        let dlg = $dialogs.confirm('Are you sure you want to delete this record?');
        dlg.result.then(function(btn) {
            $http.delete("/user/delete/" + id)
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

        $http.post("/user/create", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("created user successfully!");
                        item = response.data;
                        $scope.items.push({
                            id: item.id,
                            name: item.name,
                            surname: item.surname,
                            roles: item.roles,
                            emailAddress: item.emailAddress,
                        });
                    }
                },
                function(response){
                    DialogService.handle(response, 'user', 'create');
                }
            );
    }

}]);