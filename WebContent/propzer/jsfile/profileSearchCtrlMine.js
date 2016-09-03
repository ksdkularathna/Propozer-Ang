
app.controller('profileSearchCtrl', ['$scope', 'ModalService', '$log', '$window', '$http', '$location', '$route',
                              function($scope, ModalService, $log, $window, $http, $location,$route) {
	console.log('inside search ctrl');	
	
	$scope.showConnect=true;
	 $scope.showSent=false;
	 
	 
	  $scope.showDiv=function(){
	   
	   console.log("hh");
	  $scope.showConnect=false;
	  $scope.showSent=true; 
	  }
	
	

	$scope.endPointURL;
	
	$scope.first=true;
	
	$http.get("json/endPoint.json")
    .success(function(data) {
  	  $scope.endPointURL= data.endPoint;
         console.log($scope.endPointURL);   
         
         var serviceURL=$scope.endPointURL+'/initialSearch/';
     	
         $http.get(serviceURL)    
         .success(function(data) {        	 
             
         	//$scope.initialSearchData=data.entity.records;
         	$scope.genderList=data.entity.genders;
         	$scope.ageList=data.entity.ages;
         	$scope.cityList=data.entity.cities;
         	$scope.qualificationList=data.entity.qualifications;        	
         	
         });         
    });		
	
	$scope.searchUser=function(form){
   	 console.log(form);
   	 
   	 var user = sessionStorage.UserId;
   	 
   	 console.log(user);
   	 
   	var json={
         	userId:user,
         	data:form         	
         }
   	 
   	 
   	 var serviceURL=$scope.endPointURL+"/insideSearch/";
   	 
    	$http.post(serviceURL,json)
        //$http.post("http://192.168.0.51:8081/Propozal/search/",json)
        //$http.get('js/data.json')
            .success(function(data) {
            	
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
            	
            	$scope.result=true;
            	$scope.first=false;
               
            	$scope.searchResults = data.entity.records;
            	$scope.matchingCount=data.entity.matchingCount;
            	
            	
            	
            	console.log($scope.searchResults);
               
            });    	
    	
    	$scope.selection=[];
		// toggle selection for a given employee by name
		$scope.toggleSelection = function toggleSelection(sub,subName) {
			
			var value=sub+' '+subName;
			
			
			console.log(value);
			
	    var idx = $scope.selection.indexOf(value);

	    // is currently selected
	    if (idx > -1) {
	      $scope.selection.splice(idx, 1);
	    }
	    // is newly selected
	    else {
	      $scope.selection.push(value);
	    }	    
	    console.log($scope.selection);	    
	  };   	   	    	 
    }  
	
	$scope.newSearchUser=function(){
		
		 console.log($scope.selection);	
		 
	     var serviceURL=$scope.endPointURL+'/newSearch/';
	     	
         $http.post(serviceURL,$scope.selection)    
         .success(function(data) {        	 
             
        	$scope.searchResults = data.entity.records;
         	$scope.matchingCount=data.entity.matchingCount;     	
         	
         	
         	console.log($scope.searchResults);  	
        	 
         	
         });
		 
		
	}
	
	

}]);



