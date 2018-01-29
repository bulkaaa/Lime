angular
    .module('myApp')
    .factory('DialogService', DialogService);

DialogService.$inject = ['$rootScope', '$timeout', '$q','dialogs'];
function DialogService($rootScope, $timeout, $q, $dialogs) {
    service = {};
    service.dialogWait = dialogWait;
    service.generalServerError = generalServerError;
    service.validationError = validationError;
    service.handle = handle;
    service.loginFirstError = loginFirstError;
    return service;


    function generalServerError(){
        $dialogs.error('Ups... Server error', 'Seems like this functionality is not working correctly yet. Please try again later or contact us: lime.lab.application@gmail.com');
    }
    function validationError(response, name, action){
        let errorMessage = "Please check your input: ";
        if (response.data && response.data.validationErrors){
            errors = response.data.validationErrors;
            for (let i in errors)
                errorMessage += "Field: " + errors[i].field +  " " + errors[i].hint + ". ";
        }
        $dialogs.error("Failed to "+action+" " + name + " .", errorMessage);
    }

    function alreadyExistsError(response, name, uniqueFields){
        let errorMessage = "Please check your input";
        $dialogs.error(name + " with such " + uniqueFields +" already exists", errorMessage);
    }

    function loginFirstError(){
        $dialogs.notify("Unauthorized", "Please log in first");
    }

    function handle(response, name, action, uniqueFields){
        if (response.status === 422 )
            validationError(response, name, action);
        else if(response.status === 409)
            alreadyExistsError(response, name, uniqueFields);
        else
            generalServerError();
    }

    function dialogWait() {
        return $q(function(resolve, reject) {
            var i = 0;
            var msgs = [
                'Initializing process...',
                'About half way done...',
                'Almost there?',
                'Woo Hoo! I made it!'
            ];
            var progress = 0;

            dlg = $dialogs.wait('Give me a minute...', msgs[i], progress);
            function fakeProgress(){
                $timeout(function () {
                    if (progress < 100) {
                        progress += 25;
                        $rootScope.$broadcast('dialogs.wait.progress', {msg: msgs[i++], 'progress': progress});
                        fakeProgress();
                    } else {
                        $rootScope.$broadcast('dialogs.wait.complete');
                        resolve('Done');
                    }
                }, 500);
            }
            fakeProgress();
        });
    }



}