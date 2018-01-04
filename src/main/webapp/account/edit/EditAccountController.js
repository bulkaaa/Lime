angular
        .module('myApp')
        .controller('EditAccountController', EditAccountController);

    EditAccountController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function EditAccountController(UserService, $location, $rootScope, FlashService) {



    }