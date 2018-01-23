    angular
        .module('myApp')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.Login = Login;
        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function Login(user) {
            return $http({
                method: 'POST',
                url: '/login',
                data:  $.param(user),
                headers:  {'Content-Type': 'application/x-www-form-urlencoded' }
            }).then(handleSuccess, handleError('User login error'));
        }

        function GetAll() {
            return $http.get('/user/all').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/user/one/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.get('/user/get-by-username/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            return $http.post('/user/create', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('/user/update', user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/user/delete/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            return { success: true, data: res.data };
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }
