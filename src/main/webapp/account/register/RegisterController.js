angular
        .module('myApp')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService', 'DialogService'];
    function RegisterController(UserService, $location, $rootScope, FlashService, DialogService) {
        var vm = this;

        vm.register = register;

        function register() {
            vm.dataLoading = true;
            UserService.Create(vm.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/login');
                    } else {
                        DialogService.handle(response.response, "User", "register", "email or username");
                        vm.dataLoading = false;
                    }
                });
        }
    }