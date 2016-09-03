
app.controller('profileSearchCtrl', ['$scope', 'ModalService', '$log', '$window', '$http', '$location', '$route',
                              function($scope, ModalService, $log, $window, $http, $location,$route) {
	console.log('inside search ctrl');	

	$scope.endPointURL;
	
	$scope.first=true;
	
	$http.get("json/endPoint.json")
    .success(function(data) {
  	  $scope.endPointURL= data.endPoint;
         console.log($scope.endPointURL);   
         
         var serviceURL=$scope.endPointURL+'/initialSearch/';
     	console.log("Whene I");
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
                
                	
                
                });
               
                //---------------------------------------------------------------------------------------//
                var json={
                        userId:user,
                           
                       }
               $scope.storeData=[]; 
                var serviceURL="http://192.168.0.51:8085/Propozal/friendSearch/";
               
               $http.post(serviceURL,json)
               .success(function(data) {
                console.log("-------------");

               console.log(data);
               
               
               if (data.entity) {
               for(var i=0;i< data.entity.length;i++)
               $scope.propozalSent[data.entity[i].friend]=true;

                console.log(data.entity);
                 
                 
                } else  {
                  console.log("data not received//null");

                 
                }

               });
               /..................................................................................../
            	
            	$scope.result=true;
            	$scope.first=false;
               
            	$scope.searchResults = data.entity.records;
            	$scope.matchingCount=data.entity.matchingCount;
   
            });    	
    	
    	$scope.selection=[];
    	$scope.age=[];
    	$scope.height=[];
    	$scope.gender=[];
    	$scope.country=[];
    	$scope.city=[];
    	$scope.religion;
    	$scope.speaks=[];
    	$scope.education=[];
   	 $scope.initalisation=1;
		// toggle selection for a given employee by name
		$scope.toggleSelection = function toggleSelection(sub,subName) {
			
			var value=sub+' '+subName;
			var submenu=subName;
			
			
			if( $scope.initalisation==1){
				for(var k in  $scope.postArray){
					console.log(k);
					if(k=='Education')
					var vStr='Qualification'+' '+$scope.postArray[k];	
					else
						var vStr=k+' '+$scope.postArray[k];	
						
					$scope.selection.push(vStr);
				}

					
				console.log($scope.selection);
				$scope.initalisation++;
			}
			
			
			
			
			if($scope.arar.hasOwnProperty(subName)){
				$scope.arar[subName]=!$scope.arar[subName];
				 
				
			}
			else
				{
				$scope.arar[subName]=true;
				
				}
			
	    var idx = $scope.selection.indexOf(value);

	    // is currently selected
	    if (idx > -1) {
	      $scope.selection.splice(idx, 1);
	    }
	    // is newly selected
	    else {
	    	
	      $scope.selection.push(value);
	    }	 
	    //console.log($scope.arar);
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
	
//::::::::::::for ChekBox Tracking:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::;//	
	$scope.arar=[];
	$scope.postArray=[];
	
	  $scope.logResultGender = function(value) {
		
	      
	         
	         var index=0;
	         if (typeof  $scope.arar!== 'undefined') {
				  for(var i=0;i< $scope.genderList.length;i++){
					  $scope.arar[$scope.genderList[i].gender]=false;
				  }
				  
			  }
	         var prefix="Gender"
	        	 
	         if(value!="Select"){
		         $scope.arar[value]=true;
		       
		         $scope.postArray[prefix]=value;
	         }
	         if(value=="Select"){
	        	 $scope.postArray[prefix]="select"
	         }
		        
		        console.log($scope.postArray);
	        
	    }
	  
	  
	  
	  $scope.logResultCity = function(value) {
			
	         console.log(value); 
	         console.log($scope.cityList); 
	         var prefix="City"
	         var index=0;
	         if (typeof  $scope.arar!== 'undefined') {
				  for(var i=0;i< $scope.cityList.length;i++){
					  $scope.arar[$scope.cityList[i].city]=false;
				  }
				  
			  }
	         if(value!="Select")
		         {
	        	 $scope.arar[value]=true;
	        	
		         $scope.postArray[prefix]=value;
		         }
	         
	         if(value=="Select"){
	        	 $scope.postArray[prefix]="select"
	         }
	         console.log($scope.postArray);
	    }
	  
	  
	  $scope.logResultQualification = function(value) {
		  var prefix="Qualification"
	         console.log(value); 
	         console.log($scope.qualificationList); 
	         
	         var index=0;
	         if (typeof  $scope.arar!== 'undefined') {
				  for(var i=0;i< $scope.qualificationList.length;i++){
					  if($scope.qualificationList[i])
					  $scope.arar[$scope.qualificationList[i].qualification]=false;
				  }
				  
			  }
	         if(value!="Select"){
		         $scope.arar[value]=true;
		       
		         $scope.postArray[prefix]=value;
	         }
	         if(value=="Select"){
	        	 $scope.postArray[prefix]="select"
	         }
	         console.log($scope.postArray);
	    }
	  
	  
	  
	 
	  
	  $scope.index=0;
	  
	  $scope.logResultAge = function(value) {
		  var prefix="Age"
	         console.log(value); 
	         console.log("--------------");
	         console.log($scope.ageList); 
	         console.log("--------------");
	         
	         $scope.index++;
	        if( $scope.index==3){
	         if (typeof  $scope.arar!== 'undefined') {
				  for(var i=0;i< $scope.ageList.length;i++){
					  $scope.arar[$scope.ageList[i].subMenu]=false;
				  }
				  
			  }
	         $scope.index=0;
	        }
	        
	         if(value!="Select"){
		         $scope.arar[value]=true;
		       
		         $scope.postArray[prefix]=value;
	         console.log($scope.postArray);
	        
	    }
	         if(value=="Select"){
	        	 $scope.postArray[prefix]="select"
	         }
	 
	  } 
	  
	  
	  $scope.logResultAge1 = function(value) {
		  var prefix="Age1"
	         
	        
	         if(value!="Select"){
		       
		       
		         $scope.postArray[prefix]=value;
	         console.log($scope.postArray);
	        
	    }
		  if(value=="Select"){
	        	 $scope.postArray[prefix]="select"
	         }
	 
	  } 
	  
	  
//	  $scope.addType = function(){
//		   angular.forEach($scope.typeCheckBoxes, function(value, index) {
//		      if(value.val)
//		       console.log('value.id');//here you can get selected value
//		   });
//		}
	 
	  $scope.propozalSent=[]
	
	  $scope.select = function(result) {
		      
		  //:::::::::::::::::::::::::intialisaton::::::::::::::::::::::::::::::::::::::::::::::::://    
		       $scope.selected = result;
		    var userEmail=sessionStorage.UserId;
		    var friendEmail=$scope.selected.email;
		    $scope.propozalSent[$scope.selected.email]=true;
		    
		      $scope.postValue = {
		      useremail : userEmail,
		      friendemail : friendEmail,
		      
		     };
		  //::::::::::::::start datbse updte for friend request ::::::::::::::::::::::::::::::::::::::://  
		      var serviceURL = "http://192.168.0.51:8085/Propozal/friend/";
		    $http.post(serviceURL, $scope.postValue).success(function(data) {
		     //console.log(data);

		     if (data.entity.response == 'success') {

		      $route.reload();

		     } else if (data.entity.response == 'fail') {
		      alert("Not updated!!");
		      $route.reload();
		     }

		    });

		   
		    
		       $scope.showSent=true;
		       $scope.propozalSent[$scope.selected.email]=true;
		       console.log($scope.propozalSent[$scope.selected.email]);
		    
		     }
	  
	  
	

}]);



