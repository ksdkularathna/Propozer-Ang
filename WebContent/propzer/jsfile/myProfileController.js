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
		  var userEmail= sessionStorage.UserId;
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
		   
		   $scope.value1 = {
				    email:email,
				    userEmail:userEmail
				        };
		   
		   $http.post("http://192.168.0.51:8085/Propozal/recentView/",$scope.value1)
		   
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


app.controller('myProfileController1',['$scope','$http','$location','$window','$routeParams','$rootScope', function ($scope, $http, $location, $window, $routeParams,$rootScope) {
	 
	 var email = $routeParams.email;
 //alert(email);
	// console.log(email);
	 
	 
	 
	 $scope.value = {
			 email:email
      };
 $scope.activePath = null;
 $http.post("http://192.168.0.51:8085/Propozal/viewprofiledemo/",$scope.value)
 //$http.get('js/vv.json')
     .success(function (data)
     {
     	console.log("success");
         $scope.MemberDetail = data.entity.records[0];
      
       // MemberDetail.email
     });
 
 
		
	  }
]);	