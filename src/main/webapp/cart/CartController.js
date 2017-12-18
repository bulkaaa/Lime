
app.controller('CartController', function($scope) {

     $scope.items = [
                {name: 'resource1', value: '6', description:'resource1 description', image: "libs/img/BlueTestTube.jpg"},
                {name: 'resource2', value: '56', description:'resource2 description', image: "libs/img/OrangeTestTube.png"},
                {name: 'resource3', value: '98', description:'resource3 description', image: "libs/img/PinkTestTube.jpg"},
                {name: 'resource4', value: '54', description:'resource4 description', image: "libs/img/BlueTestTube.jpg"},
                {name: 'resource5', value: '7', description:'resource5 description', image: "libs/img/OrangeTestTube.png"},
                {name: 'resource6', value: '30', description:'resource6 description', image: "libs/img/PinkTestTube.jpg"}
              ];

$(document).ready(function(){
    $('.minus-btn').on('click', function(e) {
            e.preventDefault();
            var $this = $(this);
            var $input = $this.closest('div').find('input');
            var value = parseInt($input.val());
            if (value > 1) {
                value = value - 1;
            } else {
                value = 0;
            }

            $input.val(value);

        });

        $('.plus-btn').on('click', function(e) {
            e.preventDefault();
            var $this = $(this);
            var $input = $this.closest('div').find('input');
            var value = parseInt($input.val());
            value = value + 1;
            $input.val(value);
        });


        $('.delete-btn').on('click', function() {
            $(this).closest('.item').remove();
        });
    })


        $scope.open = function() {
            $scope.showModal = true;
          };

          $scope.ok = function() {
            $scope.showModal = false;
          };

          $scope.cancel = function() {
            $scope.showModal = false;
          };

    });