//////for dependency dropdown start//////
app.factory("CustomerService", ['$filter','$http',
    function($filter,$http){
	
$scope.endPointURL;
	
	$http.get("json/endPoint.json")
    .success(function(data) {
  	  $scope.endPointURL= data.endPoint;
         console.log($scope.endPointURL);
    });

        var service = {};

        var countrylist=[];
        
//        $http.get("http://1.186.96.69:82/Propozal/country/")
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
    
    $http.get("json/endPoint.json")
    .success(function(data) {
  	  $scope.endPointURL= data.endPoint;
         console.log($scope.endPointURL);
         
     	
     	
     	$scope.first=true;
     	$scope.second=false;
     	$scope.third=false;
     	
     	var serviceURL=$scope.endPointURL+"/country/";
     	
     	console.log('**********serviceURL***********');
     	console.log(serviceURL);
     	
     	//$http.get("http://54.169.61.19:8080/Propozal/country/")
     	$http.get(serviceURL)
         .success(function(data) {
       	  $scope.countries= data.entity.records;
              console.log($scope.countries);
         });
     	
     	var serviceURL=$scope.endPointURL+"/state/";
     	$http.get(serviceURL)
         .success(function(data) {
       	  $scope.states= data.entity.records;
              console.log($scope.states);
         });
     	
     	var serviceURL=$scope.endPointURL+"/city/";
     	$http.get(serviceURL)
         .success(function(data) {
       	  $scope.cities= data.entity.records;
              console.log($scope.cities);
         });
   	  
         $scope.getCountryStates = function(){
         	
         	$scope.first=false;
         	$scope.second=true;
         	
         	
         	//$scope.statesNew={};
       	  
       	 // var countryId=$scope.country;
       	  
       	  var json={
       			  countryId:$scope.user.country
       	  }
       	  
       	var serviceURL=$scope.endPointURL+"/states/";
       	  
       //	$http.post("http://54.169.61.19:8080/Propozal/states/",json)
       	 $http.post(serviceURL,json)
             .success(function(data) {
           	  $scope.statesNew = data.entity.records;
                  console.log($scope.statesNew);
             });    	  
       	  
             //$scope.states = CustomerService.getCountryState($scope.country);
           //  $scope.cities =[];
         };

         $scope.getStateCities = function(statess){
         	
         	$scope.first=false;
         	//$scope.third=true;
         	$scope.second=true;
       	  
       	  var json={
       			  stateId:statess
       	  }
       	  
       	var serviceURL=$scope.endPointURL+"/cityy/";
       	  
       	//$http.post("http://54.169.61.19:8080/Propozal/cityy/",json)
       	 $http.post(serviceURL,json)
             .success(function(data) {
           	  $scope.citiesNew = data.entity.records;
                  console.log($scope.citiesNew);
             });  
       	  
       	  
             //$scope.cities = CustomerService.getStateCity($scope.state);
         }
     	
     	
     	var id=$routeParams.userId;
         
         var json={
         		userId:id
             }
         
         var serviceURL=$scope.endPointURL+"/profileForEdit/";
         
        // $http.post("http://54.169.61.19:8080/Propozal/profileForEdit/",json)
             $http.post(serviceURL,json) 
             
                 .success(function(data) {                	
                 	$scope.user = data.entity.records[0];
                 	
                 	console.log($scope.user);
                    
                 });   
         
         $scope.editDone = function(user) {
             // $http.put("js/category.json?CategoryNo=" + CategoryNo,category)
            // console.log(member);
         	// $http.post("http://54.169.61.19:8080/Propozal/editProfile/",json)
         	
         	 var serviceURL=$scope.endPointURL+"/editProfile/";
         	
             $http.post(serviceURL,user)
                 .success(function(data) {
                     console.log("succ");
                     
                     if(data.response=='success'){
                    	 
                    	 alert("Updated Successfully!!");
                    	 
                    	$scope.activePath = $location.path('/myProfile');
                 		
                 	//$window.location.href = "http://" + $window.location.host + "/Propozal/index.html#/myProfile";
                 		//$route.reload();
                 		
                 	}else if(data.response=='fail'){
                 		alert("Not updated!!");
                 	}
                     
                     //$scope.activePath = $location.path('/memberList');
                 });
         };         
    });  
}]);
