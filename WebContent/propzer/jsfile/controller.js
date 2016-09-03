
app.controller('HeaderCtrl', ['$scope', 'ModalService', '$log', '$window', '$http', '$location', '$route',
                              function($scope, ModalService, $log, $window, $http, $location,$route) {
	
	console.log('inside controller.js');

	$scope.endPointURL;
	
	$http.get("json/endPoint.json")
    .success(function(data) {
  	  $scope.endPointURL= data.endPoint;
         console.log($scope.endPointURL);
    });	
	
	
    $scope.complexResult = null;

    ///for changing headre///
    $scope.IsVisibleBeforeLogin = true;
    $scope.IsVisibleAfterLogin = false;

    if(sessionStorage.UserId) {
        //alert(sessionStorage.UserId);
        console.log(sessionStorage.UserId)
        $scope.IsVisibleBeforeLogin = false;
        $scope.IsVisibleAfterLogin = true;
        $scope.UName = sessionStorage.UserName;
    }

    ////header chane end////
    
  //:::::::::::::::::::::::::::::::::::::::::::StartFriendName::::::::::::
    $scope.number;
    $scope.friendNameList=[];
    userEmail=sessionStorage.UserId;
     
      var json={
              userId:userEmail,
                 
             }
      
      var serviceURL="http://192.168.0.51:8085/Propozal/friendName/";
    
    $http.post(serviceURL,json)
    .success(function(data) {
     console.log("-------------");

    console.log(data);
    
    
    if (data.entity) {
      console.log(data.entity);
      
    
      $scope.number=data.entity.length;
     for(var i=0;i< data.entity.length;i++)
      {
      var obj={};
      var name=data.entity[i].friend[0]+"  "+data.entity[i].friend[1];
      obj.name=name;
      obj.email=data.entity[i].friend[2];
      $scope.friendNameList.push(obj); 
      }
      console.log($scope.friendNameList);
      
     } else  {
       console.log("data not received//null");

      
            }

    });
    
   //:::::::::::::::::::::::::::::::::::::::::::endFriendName:::::::::::::::::
    
  //******************************************************************************************//
	
	  $scope.notificationPopUp = function() {

	        
	        $scope.visible=true;
	        ModalService.showModal({
	            templateUrl: "template/notification.html",           
	            controller: "NotificationControler",
	            inputs: {
	                title: "A More Complex Example"
	            }
	        }).then(function(modal) {
	            modal.element.modal();
	            modal.close.then(function(result) {

	             console.log('inside forgot..j;ldf;ljhldflk'+result);
	           
	            });
	        });

	    

	    };
	    
	  //******************************************************************************************//
   
    $scope.showComplex = function() {

        ModalService.showModal({
            //templateUrl: "complex/complex.html",
            templateUrl: "template/partial/logInPopUp1.html",
            //templateUrl: "template/partial/regFirst.html",
            controller: "ComplexController",
            inputs: {
                title: "A More Complex Example"
            }
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                $scope.complexResult  = "User Name: " + result.name + ", Password: " + result.age;
                //console.log($scope.complexResult);
                //////////for login/////
                    var username = result.name;
                    var password= result.age;
                   // alert(username);
                    
                    $scope.vals = {
    						usernames: username,
    						passwords: password
                        };

                    var serviceURL=$scope.endPointURL+"/login/";
                    
                    //$http.post("js/loginResponse.json")
                    //$http.post("http://192.168.0.51:8081/Propozal/login/",$scope.vals)
                   //$http.post("http://1.186.96.69:82/Propozal/login/",$scope.vals)
                   // $http.post("http://54.169.61.19:8080/Propozal/login/",$scope.vals)
                    $http.post(serviceURL,$scope.vals)
                        .success(function (data){
                            // $scope.myData = data;
                            //////
                            $scope.myDatas = data;
                            //console.log($scope.myDatas);
                            $scope.myData_response = data.entity.response;
                            console.log($scope.myData_response);
                            /////
                            if($scope.myData_response == "success"){
                                sessionStorage.UserName= data.entity.firstName;
                                sessionStorage.UserId= data.entity.userId;
                                //sessionStorage.Uimage=data.entity.userImage;
                                //alert(sessionStorage.UserName);

                                //$location.path('/myProfile');
                                //var url = "http://" + $window.location.host + "/Propozal/index.html";
                                var url = "http://" + $window.location.host + "/Propozal/index.html#/myProfile";
                               $log.log(url);
                               $window.location.href = url;
                                
                               $window.location.reload();
                               // $route.reload();
                                
                            }else{
                                alert("Wrong Information");
                            }
                        })

                //////for log in end///////////

            });
        });

    };


    var formModel = {};
    $scope.showReg = function() {
        //alert("reg");
        ModalService.showModal({
            templateUrl: "template/partial/complex1.html",
            //templateUrl: "template/partial/logInPopUp1.html",
            controller: "ComplexController1",
            inputs: {
                title: "A More Complex Example"
            }
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                 ////////for Insert/////
                formModel.firstName = result.firstName;
                formModel.lastName = result.lastName;
                formModel.email = result.email;
                formModel.password = result.password;
                formModel.country = result.country;
                formModel.state = result.state;
                formModel.city = result.city;
                formModel.gender = result.gender;
                formModel.height = result.height;
                formModel.dobYear = result.dobYear;
                formModel.dobMonth = result.dobMonth;
                formModel.dobDate = result.dobDate;
                formModel.relegion = result.relegion;
                formModel.motherTongue = result.motherTongue;
                formModel.higestEdu = result.higestEdu;
                formModel.careerField = result.careerField;
                formModel.workingAs = result.workingAs;
                formModel.monthlySal = result.monthlySal;
                formModel.personType = result.personType;
                formModel.familyBackground = result.familyBackground;
                formModel.eduAndCareer = result.eduAndCareer;
                formModel.lookingFor = result.lookingFor;
                formModel.facebookUrl = result.facebookUrl;
                formModel.linkdinUrl = result.linkdinUrl;
                formModel.nicNumber = result.nicNumber;
                formModel.mobileNumber = result.mobileNumber;
                console.log("from my ctrl start");
                console.log(formModel);
                console.log("from my ctrl end");
                
                var serviceURL=$scope.endPointURL+"/registration/";
                
                //$http.post('http://localhost:8081/OrganizationMaster/member/',$scope.formModel)
               // $http.post('http://192.168.0.51:8081/Propozal/registration/',formModel)
               //$http.post('http://1.186.96.69:82/Propozal/registration/',formModel)
               //$http.post('http://54.169.61.19:8080/Propozal/registration/',formModel)
               //$http.post('js/data.json', formModel)
               
                $http.post(serviceURL,formModel)
               
                    .success(function(data) {
                        alert("Your Registration is Succesfull..!!");
                       // $scope.activePath = $location.path('/myProfile');
                        var url = "http://" + $window.location.host + "/Propozal/index.html";
                        console.log("Success")
                    }).error(function(data) {
                    console.log("fail")
                })
                ////for Insert end///////////
           
            });
        });

    };

    $scope.logout = function() {
        delete sessionStorage.UserName;
        delete sessionStorage.UserId;
        $scope.IsVisibleBeforeLogin = true;
        $scope.IsVisibleAfterLogin = false;
        var url = "http://" + $window.location.host + "/Propozal/index.html";
        $log.log(url);
        $window.location.href = url;
    }

    $scope.hoverIn = function(){
    	
   	 $scope.notiList =[];
   	
   	$scope.hoverEdit = true;
   	var userId = sessionStorage.UserId;
   	
   	var value1 = {
   			 userId:userId
	        };
   	  var serviceURL=$scope.endPointURL+"/getNotification/";
   	// console.log("hiiiigfhyhjgsg"+value1);
   	// $http.post("http://192.168.0.126:8080/Propozal/getNotification/",$scope.value)
   	  
   	  $http.post(serviceURL,value1)
         
         .success(function(data) {
           
            // $scope.activePath = $location.path('/myProfile');
           //  var url = "http://" + $window.location.host + "/Propozal/index.html";
             $scope.notiList1=data.entity.records;
             console.log($scope.notiList1);
            
             for(var i=0;i<$scope.notiList1.length;i++)
          {
           var obj={};
           obj.count=$scope.notiList1[i].count;
           obj.firstName=$scope.notiList1[i].friend[0];
           obj.lastName=$scope.notiList1[i].friend[1];
           obj.email=$scope.notiList1[i].friend[2];
           console.log("hiiii"+ obj.count);
           console.log("hiiii"+ obj.firstName);
           $scope.notiList.push(obj);
           $scope.count=obj.count;
           
          }
             
             console.log("Success")
         }).error(function(data) {
         console.log("fail")
     })
   	
   };

   $scope.hoverOut = function(){
   	$scope.hoverEdit = true;
   };
   
   

   }]);


app.controller('SearchCtrl', ['$scope', 'ModalService', '$log', '$window', '$http', '$location', 
                              function($scope, ModalService, $log, $window, $http, $location) {
	console.log('inside search ctrl');	

	$scope.endPointURL;
	
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
	
	$scope.searchBox=function(form){
   	 console.log(form);
   	 
   	 $scope.result=true;
   	 
   	 var serviceURL=$scope.endPointURL+"/search/";
   	 
 	$http.post(serviceURL,form)
     //$http.post("http://192.168.0.51:8081/Propozal/search/",json)
     //$http.get('js/data.json')
         .success(function(data1) {
         	
         	//----------------------------------------------------------------------------------------//
             var serviceURL=$scope.endPointURL+'/searchMenu/';
          	
             $http.get(serviceURL)    
             .success(function(data) {
            	 
            	 $scope.result=true;
                 
               	$scope.searchResults = data1.entity.records;
               	$scope.matchingCount=data1.entity.matchingCount;
               	
               	console.log($scope.searchResults);
            	 
            	 
            	 $scope.oneAtATime = true;
                 
             	$scope.menuBar=data.entity;
             	
            	console.log($scope.menuBar);

             });
            
             //---------------------------------------------------------------------------------------//
         });
   	 
    }
	/*app.controller('myProfileController',['$scope','$http','$location','$window','$routeParams','$rootScope', function ($scope, $http, $location, $window, $routeParams,$rootScope) {
		 
		//:::::::::::::::::::::::::::::To collect Propozal sent request:::::::::::::// 
		 var val=[];
		 var initialVal =$routeParams.email;
		   val=initialVal.split("+");
		   email=val[0];
		   $scope.state=val[1];
		   //alert(empNo);
		  console.log("Root Paaram");
		  console.log(email);
		  console.log("Root Paaram");
		  $scope.propozalSent=[]
		  $scope.propozalSent[email]=val[1];
		 //:::::::::::::::::::::::::::::To collect Propozal sent request::::::::::::://
		
		 
		 
		 $scope.value = {
				 email:email
	        };
	   $scope.activePath = null;
	   $http.post("http://192.168.0.51:8085/Propozal/myprofiledemo/",$scope.value)
	   //$http.get('js/vv.json')
	       .success(function (data)
	       {
	       	console.log("success");
	           $scope.MemberDetail = data.entity.records[0];
	           console.log( $scope.MemberDetail);
	        
	         // MemberDetail.email
	       });
	   
	 
			
		  }
	  ]);	*/
	
	    
	    
	app.controller('myProfileController',['$scope','$http','$location','$window','$routeParams','$rootScope', function ($scope, $http, $location, $window, $routeParams,$rootScope) {
		 
		//:::::::::::::::::::::::::::::To collect Propozal sent request:::::::::::::// 
		 var val=[];
		 var initialVal =$routeParams.email;
		   val=initialVal.split("+");
		   email=val[0];
		   $scope.state=val[1];
		   
		   $scope.isExist=false;
		   $scope.image=val[2];
		   //alert(empNo);
		  console.log("Root Paaram");
		  console.log(email);
		  console.log("Root Paaram");
		  $scope.propozalSent=[]
		  $scope.propozalSent[email]=val[1];
		 //:::::::::::::::::::::::::::::To collect Propozal sent request::::::::::::://   
		  $scope.value = {
		    email:email
		        };
		   $scope.activePath = null;
		   $http.post("http://192.168.0.51:8085/Propozal/myprofiledemo/",$scope.value)
		   //$http.get('js/vv.json')
		       .success(function (data)
		       {
		        console.log("success");
		           $scope.MemberDetail = data.entity.records[0];
		        
		         // MemberDetail.email
		       });
		   
		   
		   
		   $scope.sent=function(friendEmail){
		  var userEmail=sessionStorage.UserId;
		 
		   $scope.propozalSent[friendEmail]=true;
		  $scope.postValue = {
		   useremail : userEmail,
		   friendemail : friendEmail,
		   
		  };
		  
		  $http.get("json/endPoint.json")
		     .success(function(data) {
		      $scope.endPointURL= data.endPoint;
		      
		     console.log("end point");   
		          console.log($scope.endPointURL);   
		      console.log("end point");   
		          
		      
		       
		                
		     });
		  
		  // var serviceURL="http://192.168.0.124:8081/Propozal/friendSearch/";
		  
		  $http.post("http://192.168.0.51:8085/Propozal/friendSearch/", $scope.postValue)
		  .success(function(data) {
		   //console.log(data);

		   if (data.entity.response == 'success') {

		    $route.reload();

		   } else if (data.entity.response == 'fail') {
		    alert("Not updated!!");
		    $route.reload();
		   }

		  });
		  
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

		  
		 }
		   
		   
		   
		   

}]);
	
}]);



