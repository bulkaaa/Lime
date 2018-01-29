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
            return $http.get('/user/all').then(handleSuccess, handleError);
        }

        function GetById(id) {
            return $http.get('/user/one/' + id).then(handleSuccess, handleError);
        }

        function GetByUsername(username) {
            return $http.get('/user/get-by-username/' + username).then(handleSuccess, handleError);
        }

        function Create(user) {
            return $http.post('/user/create', user).then(handleSuccess, handleError);
        }

        function Update(user) {
            return $http.put('/user/update', user).then(handleSuccess, handleError);
        }

        function Delete(id) {
            return $http.delete('/user/delete/' + id).then(handleSuccess, handleError);
        }

        // private functions

        function handleSuccess(res) {
            return { success: true, data: res.data };
        }

        function handleError(res) {
                return { success: false, response: res };
        }
    }
