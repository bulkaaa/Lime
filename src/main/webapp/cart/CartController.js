
app.controller('CartController', ['$scope', '$http', '$uibModal', function($scope, $http, $modal) {
     var modalInstance = null;

     $scope.items = [
                {id: '1', name: 'resource1', value: '6', description:'resource1 description Lorem ipsum dolor sit amet, consectetur adipiscing elit.', image: "libs/img/BlueTestTube.jpg"},
                {id: '2', name: 'resource2', value: '56', description:'resource2 description', image: "libs/img/OrangeTestTube.png"},
                {id: '3', name: 'resource3', value: '98', description:'resource3 description', image: "libs/img/PinkTestTube.jpg"},
                {id: '4', name: 'resource4', value: '54', description:'resource4 description', image: "libs/img/BlueTestTube.jpg"},
                {id: '5', name: 'resource5', value: '7', description:'resource5 description', image: "libs/img/OrangeTestTube.png"},
                {id: '6', name: 'resource6', value: '30', description:'resource6 description', image: "libs/img/PinkTestTube.jpg"}
              ];


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
}

$scope.editRecord = function(item){

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
   }

$scope.updateRecord = function(item) {
      $http.put("/resource/update", JSON.stringify(item))
             .then(
                 function(response){
                   if (response.data){
                       console.log("updated record successfully!");
                   }
                 },
                 function(response){
                   alert( "failure message: " + JSON.stringify(response));
                 }
             );
 }

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
 }

 $scope.deleteRow = function(id) {
    for (let i = $scope.items.length - 1; i >= 0; i--) {
       if ($scope.items[i].id === id) {
           $scope.items.splice(i, 1);
       }
    }
 }

$scope.addRecord = function(){
	modalInstance = $modal.open({
	  templateUrl: 'modals/add-record.html',
	  controller: 'AddRecordController',
	  scope: $scope,
	  size: '',
	  resolve: {
	  }
   });
}

$scope.saveRecord = function(item) {

     $http.post("/resource/create", JSON.stringify(item))
        .then(
            function(response){
              if (response.data){
                  console.log("created resource successfully!");
                  $scope.items.push({
                            name: item.name,
                            description: item.description,
                            value: item.value,
                            image:''
                  });
              }
            },
            function(response){
              alert( "failure message: " + JSON.stringify(response));
            }
        );
  }

}]);