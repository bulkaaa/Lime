angular
        .module('myApp')
        .controller('ResetPasswordController', ResetPasswordController);

   ResetPasswordController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope', '$http', 'dialogs', 'DialogService'];
    function ResetPasswordController(UserService, $location, $rootScope, FlashService, $scope, $http, $dialogs, DialogService) {
    var vm = this;

    $scope.resetPassword = function(email){
       vm.dataLoading = true;
       $http.delete("/user/change_password/"  + email + "/")
       .then(
               function(response){
                    $dialogs.notify('Email send!', 'Email with your new password has been send successfully');
                    vm.dataLoading = false;
               },
               function(response){
                   $dialogs.notify('Wrong email', 'There is no such email address in our database');
                   vm.dataLoading = false;
               }
       );
    }

    }