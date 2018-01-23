app.controller('NotificationController', ['$scope', '$rootScope', '$http', '$location', '$uibModal', 'dialogs', 'DialogService', function($scope, $rootScope, $http, $location, $modal, $dialogs, DialogService) {

    DialogService.generalServerError();
    $location.path('/welcome');

}]);