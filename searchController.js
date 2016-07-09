/**
 * Created by Tarun on 7/9/2016.
 */
app.controller('searchController', ['$scope', '$window', '$routeParams', '$http', '$location', function($scope, $window, $routeParams, $http, $location) {

    console.log("from the controller search.");
    function init() {
        var gender = $routeParams.gender;
        var city = $routeParams.city;
        var edu = $routeParams.edu;
        var minAge = $routeParams.minAge;
        var maxAge = $routeParams.maxAge;
        console.log(gender);
        console.log(city);
        console.log(edu);
        console.log(minAge);
        console.log(maxAge);
    }


    init();
}]);
