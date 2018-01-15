
app.controller('CartController', ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
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
                    alert("failure message: " + JSON.stringify(response));
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
            $http.get("resource/" + item.id)
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
                       }
                     },
                     function(response){
                       alert( "failure message: " + JSON.stringify(response));
                     }
                 );
     };

     $scope.deleteRecord = function(id) {
            if (confirm('Are you sure you want to delete this record?')) {
                 $http.delete("/resource/delete/" +id)
                    .then(
                        function(response){
                            if (response.data){
                                console.log(response);
                               $scope.deleteRow(id);
                            }
                        },
                        function(response){
                            alert( "failure message: " + JSON.stringify(response));
                        }
                    );
            }
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

         $http.post("/resource/create", JSON.stringify(item))
            .then(
                function(response){
                  if (response.data){
                      console.log("created resource successfully!");
                      item = response.data;
                      $scope.items.push({
                                id: item.id,
                                name: item.name,
                                description: item.description,
                                quantity: item.quantity,
                                image: item.image,
                                unit: item.unit
                      });
                  }
                },
                function(response){
                  alert( "failure message: " + JSON.stringify(response));
                }
            );
      }

}]);