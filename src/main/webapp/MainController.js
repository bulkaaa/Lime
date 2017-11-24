/*var app = angular.module('app', ['ui.router']);

app.controller('MainController', function($scope) {});

app.config(function($stateProvider, $urlRouterProvider) {

    // default route
    $urlRouterProvider.otherwise("/index");
  var header = {
       template: '<h1>Im Header</h1>',
       controller: function($scope) {}

  }
     var footer = {
       template: '<h1>Im Footer </h1>',
       controller: function($scope) {}

  }
    // ui router states
    $stateProvider
        .state('login', {
            url: "/login",
            views: {
                header: header,
                content: {
                    template: '<p>login content</>',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
        .state('second', {
            url: "/second",
            views: {
                header: header,
                content: {
                    template: '<p>Second content</>',
                    controller: function($scope) {}
                },
                footer: footer
            }
        });

});*/