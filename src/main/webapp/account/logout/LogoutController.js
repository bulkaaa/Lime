  angular
        .module('myApp')
        .controller('LogoutController', LogoutController);

  LogoutController.$inject = ['$location', '$rootScope', 'dialogs', 'AuthenticationService'];
    function LogoutController($location, $rootScope, $dialogs, AuthenticationService) {

        (function initController() {
            // reset login status
            if($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.username) {
                AuthenticationService.ClearCredentials();
                $dialogs.notify("Logout", "You have been successfully logged out");
            }
            $location.path('/login');
        })();

    }