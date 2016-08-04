app.controller('searchController', ['$scope', '$window', '$routeParams', '$http', '$location', function($scope, $window, $routeParams, $http, $location) {

	$scope.oneAtATime = true;
	
	  $scope.groups = [
	    {
	      title: 'Dynamic Group Header - 1',
	      content: 'Dynamic Group Body - 1'
	    },
	    {
	      title: 'Dynamic Group Header - 2',
	      content: 'Dynamic Group Body - 2'
	    }
	  ];

	  $scope.items = ['Item 1', 'Item 2', 'Item 3'];

	  $scope.addItem = function() {
	    var newItemNo = $scope.items.length + 1;
	    $scope.items.push('Item ' + newItemNo);
	  };

	  $scope.status = {
	    isCustomHeaderOpen: false,
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };	
	
    console.log("from the controller search.");
    function init() {
    	
    	$scope.endPointURL;
    	
    	$http.get("json/endPoint.json")
        .success(function(data) {
      	  $scope.endPointURL= data.endPoint;
             console.log($scope.endPointURL);
             
             //----------------------------------------------------------------------------------------//
             var serviceURL=$scope.endPointURL+'/searchMenu/';
          	
             $http.get(serviceURL)    
             .success(function(data) {
            	 
            	 $scope.oneAtATime = true;
                 
             	$scope.menuBar=data.entity;
             	
             	$scope.genderList=data.entity[0].value;
             	$scope.heightList=data.entity[1].value;             	
             	$scope.ageList=data.entity[2].value;
             	$scope.countryList=data.entity[3].value;
             	$scope.cityList=data.entity[4].value;
             	$scope.maritalStatusList=data.entity[5].value;
             	$scope.relegionList=data.entity[6].value;
             	$scope.languageList=data.entity[7].value;             	
             	$scope.qualificationList=data.entity[8].value;
             	
             	console.log($scope.genderList);
             	
             });
            
             //---------------------------------------------------------------------------------------//
         	
             var genders = $routeParams.gender;
             var citys = $routeParams.city;
             var edus = $routeParams.qualification;
             var minAges = $routeParams.minAge;
             var maxAges = $routeParams.maxAge;
             console.log(genders);
             console.log(citys);
             console.log(edus);
             console.log(minAges);
             console.log(maxAges);
             
             var json={
             		gender:genders,
             		city:citys,
             		edu:edus,
             		minAge:minAges,
             		maxAge:maxAges
                 }
                 
             var serviceURL=$scope.endPointURL+"/search/";
             
             	$http.post(serviceURL,json)
                 //$http.post("http://192.168.0.51:8081/Propozal/search/",json)
                 //$http.get('js/data.json')
                     .success(function(data) {
                        
                     	$scope.searchResults = data.entity.records;
                     	$scope.matchingCount=data.entity.matchingCount;
                     	
                     	console.log($scope.searchResults);
                        
                     });
             
        });
    }

    init();
}]);



/*app.controller('searchController', ['$scope', '$window', '$routeParams', '$http', '$location', function($scope, $window, $routeParams, $http, $location) {

    console.log("from the controller search.");
    function init() {
    	
    	$scope.endPointURL;
    	
    	$http.get("json/endPoint.json")
        .success(function(data) {
      	  $scope.endPointURL= data.endPoint;
             console.log($scope.endPointURL);
             
             //----------------------------------------------------------------------------------------//
             var serviceURL=$scope.endPointURL+'/searchMenu/';
          	
             $http.get(serviceURL)    
             .success(function(data) {
                 
             	$scope.menuBar=data.entity;
             	
             	$scope.genderList=data.entity[0].value;
             	$scope.heightList=data.entity[1].value;             	
             	$scope.ageList=data.entity[2].value;
             	$scope.countryList=data.entity[3].value;
             	$scope.cityList=data.entity[4].value;
             	$scope.maritalStatusList=data.entity[5].value;
             	$scope.relegionList=data.entity[6].value;
             	$scope.languageList=data.entity[7].value;             	
             	$scope.qualificationList=data.entity[8].value;
             	
             	console.log($scope.menuBar);
             	
             });
            
             //---------------------------------------------------------------------------------------//
         	
             var genders = $routeParams.gender;
             var citys = $routeParams.city;
             var edus = $routeParams.edu;
             var minAges = $routeParams.minAge;
             var maxAges = $routeParams.maxAge;
             console.log(genders);
             console.log(citys);
             console.log(edus);
             console.log(minAges);
             console.log(maxAges);
             
             var json={
             		gender:genders,
             		city:citys,
             		edu:edus,
             		minAge:minAges,
             		maxAge:maxAges
                 }
                 
             var serviceURL=$scope.endPointURL+"/search/";
             
             	$http.post(serviceURL,json)
                 //$http.post("http://192.168.0.51:8081/Propozal/search/",json)
                 //$http.get('js/data.json')
                     .success(function(data) {
                        
                     	$scope.searchResults = data.entity.records;
                     	
                     	console.log($scope.searchResults);
                        
                     });
             
        });
    }

    init();
}]);
*/