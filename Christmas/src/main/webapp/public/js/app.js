var lclsApp = angular.module('lclsApp',['ngRoute','lclsControllers','lclsServices']);

lclsApp.config(['$routeProvider','$locationProvider',
 function($routeProvider,$locationProvider) {

 $locationProvider.html5Mode(true);

 $routeProvider.
      when('/', {
        templateUrl: '/public/partials/home.html'
      }).
      when('/Add', {
        templateUrl: '/public/partials/Add.html',
        controller: 'presentCtrl'
      }).
      when('/Edit/:id', {
        templateUrl: '/public/partials/Edit.html',
        controller: 'presentCtrl'
      }).
      when('/MyList', {
        templateUrl: '/public/partials/MyList.html',
        controller: 'presentCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);