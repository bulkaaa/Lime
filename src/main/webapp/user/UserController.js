app.controller('UserController', ['$scope', '$rootScope', '$http', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $modal, $dialogs, DialogService) {
    var modalInstance = null;
    $scope.users = true;

    $scope.showAll = function() {
        $http.get("/user/all")
            .then(
                function (response) {
                    if (response.data) {
                        var items = response.data;
                        for(var i = 0; i<items.length; i++){
                            var roles = "";
                                for(var j = 0; j<items[i].roles.length; j++ )
                                    roles += items[i].roles[j].name + " ";
                            items[i].roles = roles;
                         }
                        $scope.items = items;
                    }
                },
                function (response) {
                     DialogService.handle(response, 'users', 'all');
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
        $http.get("/user/one/" + item.id)
            .then(function(response){
                $scope.item = item;
                item = response.data;
                $http.get("/user/get-roles")
                    .then(
                        function (response) {
                            if (response.data) {
                                $scope.list.roles = [];
                                $scope.roles = response.data.slice();
                                for(i =0; i< $scope.roles.length; i++)
                                   for(j =0; j< item.roles.length; j++)
                                        if($scope.roles[i].id == item.roles[j].id)
                                             $scope.list.roles.push($scope.roles[i]);
                            }
                        },
                        function (response) {
                            DialogService.generalServerError();
                        }
                    );

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
                        item = response.data;
                        var roles = "";
                        for(var j = 0; j<item.roles.length; j++ )
                            roles += item.roles[j].name + " ";

                        $scope.item.name = response.data.name;
                        $scope.item.surname = response.data.surname;
                        $scope.item.username = response.data.username;
                        $scope.item.roles = roles;
                        $scope.item.password = response.data.password;
                        $scope.item.emailAddress = response.data.emailAddress;
                    }
                },
                function(response){
                    DialogService.handle(response, 'user', 'update', 'email or username');
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
        $scope.item={};
        $scope.list.roles = [];
        $http.get("/user/get-roles")
            .then(
                function (response) {
                    if (response.data) {
                        $scope.roles = response.data.slice();
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

        $http.post("/user/create", JSON.stringify(item))
            .then(
                function(response){
                    if (response.data){
                        console.log("created user successfully!");
                        item = response.data;
                        var roles = "";
                        for(var j = 0; j<item.roles.length; j++ )
                            roles += item.roles[j].name + " ";
                        $scope.items.push({
                            id: item.id,
                            name: item.name,
                            surname: item.surname,
                            password: item.password,
                            roles: roles,
                            emailAddress: item.emailAddress,
                            username: item.username,
                        });
                    }
                },
                function(response){
                    DialogService.handle(response, 'user', 'create', 'email or username');
                }
            );
    };

    $scope.list = {
            roles: []
        };


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