//////for dependency dropdown start//////
app.factory("CustomerService", ['$filter','$http',
    function($filter,$http){

        var service = {};

        var countrylist=[];
        
//        $http.get("http://192.168.0.51:8081/Propozal/country/")
//        .success(function(data) {
//        	countrylist= data.entity.records;
//             console.log(countrylist);
//        });
        
//        var countrylist = [
//            { "id": 1, "country": "Srilanka" },
//            { "id": 2, "country": "Canada" },
//            { "id": 3, "country": "India" },
//        ];

        
        var statelist = [
            {"Id":1, "state":"Alaska", "countryId": 1},
            {"Id":2, "state":"California", "countryId": 1},
            {"Id":3, "state":"West Bengal", "countryId": 3},
            {"Id":4, "state":"Uttar Prades", "countryId": 3},
        ];

        var citylist = [
            {"Id":1, "city":"Anchorage", "stateId": 1},
            {"Id":2, "city":"Fairbanks", "stateId": 1},
            {"Id":3, "city":"Kolkata", "stateId": 3},
            {"Id":4, "city":"Durgapur", "stateId": 3},
        ];

        service.getCountry = function(){
        	console.log(countrylist);
            return countrylist;
        };

        service.getCountryState = function(countryId){

            var states = ($filter('filter')(statelist, {countryId: countryId}, true));
            //alert(states);
            return states;
        };


        service.getStateCity = function(stateId){
            var items = ($filter('filter')(citylist, {stateId: stateId}, true));
            return items;
        };

        return service;
    }]);


app.controller('profileEditCtrl', ['$scope', '$window', '$routeParams', '$http', '$location','CustomerService', function($scope, $window, $routeParams, $http, $location,CustomerService) {

    console.log("from the profileEditCtrl");
    function init() {
    	
    	$http.get("http://192.168.0.51:8081/Propozal/country/")
        .success(function(data) {
      	  $scope.countries= data.entity.records;
             console.log($scope.countries);
        });
  	  
        $scope.getCountryStates = function(){
      	  
      	 // var countryId=$scope.country;
      	  
      	  var json={
      			  countryId:$scope.user.country
      	  }
      	  
      	  $http.post("http://192.168.0.51:8081/Propozal/states/",json)
            .success(function(data) {
          	  $scope.states = data.entity.records;
                 console.log($scope.states);
            });    	  
      	  
            //$scope.states = CustomerService.getCountryState($scope.country);
            $scope.cities =[];
        };

        $scope.getStateCities = function(){
      	  
      	  var json={
      			  stateId:$scope.state
      	  }
      	  
      	  $http.post("http://192.168.0.51:8081/Propozal/cityy/",json)
            .success(function(data) {
          	  $scope.cities = data.entity.records;
                 console.log($scope.cities);
            });  
      	  
      	  
            //$scope.cities = CustomerService.getStateCity($scope.state);
        }
    	
    	
    	var id=$routeParams.userId;
        
        var json={
        		userId:id
            }
            
            $http.post("http://192.168.0.51:8081/Propozal/profileForEdit/",json) 
            
                .success(function(data) {                	
                	$scope.user = data.entity.records[0];
                	
                	console.log($scope.user);
                   
                });   
        
        $scope.editDone = function(user) {
            // $http.put("js/category.json?CategoryNo=" + CategoryNo,category)
           // console.log(member);
            $http.post("http://192.168.0.51:8081/Propozal/editProfile/",user)
                .success(function(data) {
                    console.log("succ");
                    
                    //$scope.activePath = $location.path('/memberList');
                });
        };
    }

    init();
}]);
