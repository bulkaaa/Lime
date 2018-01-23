 angular
        .module('myApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['UserService', '$rootScope', '$cookies'];
    function HomeController(UserService, $rootScope, $cookies) {
        var vm = this;

        vm.user = null;
        vm.roles = "";
        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        initController();


        function initController() {
            loadCurrentUser();
            loadAllUsers();
        }

        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    vm.user = user.data;
                    var roles = [];
                    var role = "";
                    angular.forEach(vm.user.roles, function (value, key) {
                        role = value.name.replace("ROLE_", "");
                        roles.push(role);
                        vm.roles += role + " ";
                    });
                    $rootScope.globals.currentUser.roles = roles;
                    $rootScope.globals.currentUser.isAdmin = roles.includes("ADMIN");
                    $rootScope.globals.currentUser.isManager = roles.includes("MANAGER");
                    $rootScope.globals.currentUser.isStaff = roles.includes("STAFF");

                    // store user details in globals cookie that keeps user logged in for 1 week (or until they logout)
                    var cookieExp = new Date();
                    cookieExp.setDate(cookieExp.getDate() + 7);
                    $cookies.putObject('globals', $rootScope.globals, { expires: cookieExp });
                })
        }

        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users.data;
                });
        }

        function deleteUser(id) {
            UserService.Delete(id)
            .then(function () {
                loadAllUsers();
            });
        }
    }
