angular
        .module('myApp')
        .controller('ResetPasswordController', ResetPasswordController);

   ResetPasswordController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function ResetPasswordController(UserService, $location, $rootScope, FlashService) {



    }