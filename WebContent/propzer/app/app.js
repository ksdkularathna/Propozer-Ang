var app = angular.module('sampleapp', ['angularModalService', 'ngAnimate', 'ngRoute', 'UserValidation','ui.bootstrap']);

 app.config(['$routeProvider', function($routeProvider) {
     $routeProvider.when('/', {
         templateUrl: '../index.html',
         controller: 'startController'
     })
     .when('/myProfile', {
         templateUrl: 'template/myProfile.html',
         controller: 'myProfileCtrl'
     })
     .when("/search/:gender/:city/:qualification/:minAge/:maxAge", {
         //.when("/search", {
         controller: "searchController",
         templateUrl: "template/searchAll.html"
     })
     .when('/editProfile/:userId', {
         templateUrl: 'template/profileEdit.html',
         controller: 'profileEditCtrl'
     })
     
     .when('/searchProfile/:userId', {
         templateUrl: 'template/profileSearch.html',
         controller: 'profileSearchCtrl'
     })
     .when("/searchInside/:gender/:city/:qualification/:minAge/:maxAge", {
         //.when("/search", {
         controller: "searchController",
         templateUrl: "template/insideSearch.html"
     })     
     .when("/demo", {
         controller: "demoController",
         templateUrl: "template/demoPage.html"
     })
     
     .when("/seeImage/:userId", {
         controller: "imageController",
         templateUrl: "template/imagePage.html"
     })
     
     .when("/activation/:keyId", {
         controller: "activationController",
         templateUrl: "template/activation.html"
     })
     
     ;
     
     // otherwise({
     //     redirectTo: '/'
     // });

 }]);