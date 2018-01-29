angular
        .module('myApp')
        .controller('EditAccountController', EditAccountController);

    EditAccountController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function EditAccountController(UserService, $location, $rootScope, FlashService) {
        var vm = this;

        vm.edit = edit;

        function edit() {
            vm.dataLoading = true;
            UserService.Update(vm.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('User data update successful', true);
                        $location.path('/login');
                    } else {
                        DialogService.handle(response.response, "User", "edit", "email or username");
                        vm.dataLoading = false;
                    }
                });
        }

        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    vm.user = user.data;
                });
        }

        loadCurrentUser();
    }