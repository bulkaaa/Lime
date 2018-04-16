angular
        .module('myApp')
        .controller('ResetPasswordController', ResetPasswordController);

   ResetPasswordController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope', '$http', 'dialogs', 'DialogService'];
    function ResetPasswordController(UserService, $location, $rootScope, FlashService, $scope, $http, $dialogs, DialogService) {

    $scope.resetPassword = function(email){
       $http.delete("/user/change_password/"  + email + "/")
       .then(
               function(response){
                    $dialogs.notify('Email send!', 'Email with your new password has been send successfully');
               },
               function(response){
                   DialogService.generalServerError();
               }
       );
    }

    }