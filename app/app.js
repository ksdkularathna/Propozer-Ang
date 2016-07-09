/**
 * Created by Tarun on 6/17/2016. , 'UserValidation'
 */
var app = angular.module('sampleapp', ['angularModalService', 'ngAnimate', 'ngRoute', 'UserValidation']);

 app.config(['$routeProvider', function($routeProvider) {
     $routeProvider.when('/', {
         templateUrl: '../index.html'

     }).when('/myProfile', {
         templateUrl: 'template/myProfile.html',
         controller: 'myProfileCtrl'
     })
     .when("/search/:gender/:city/:edu/:minAge/:maxAge", {
         controller: "searchController",
         templateUrl: "template/searchAll.html"
     });
     
     // otherwise({
     //     redirectTo: '/'
     // });

 }]);