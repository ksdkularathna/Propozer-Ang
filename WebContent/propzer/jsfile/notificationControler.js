var app = angular.module('sampleapp');

app.controller('NotificationControler', [
'ModalService', '$scope', '$element','$http', 'title','$route', 'close',
  function(ModalService,$scope, $element, $http,$route, title, close) {
	
	
//:::::::::::::::::::::::::::::::::::::::::::StartFriendName::::::::::::
	
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
	
	
	
	
	
//:::::::::::::::::::::::::::::::::::::::::::Start AcceptFriend:::::::::::::::::
	
	//:::::::::::::::::::::::::intialisaton::::::::::::::::::::::::::::::::::::::::::::::::://		  
   
	
	
	
 
	
	
	$scope.accept=function(friendEmail){
		var userEmail=sessionStorage.UserId;
		var friendEmail=friendEmail;
		
		
		var  accepted="Y";
		var  decline="N";
		
		 
		   $scope.postValue = {
					useremail : friendEmail,
					friendemail : userEmail,
					accepted:accepted,
					decline:decline
					
				};
		console.log($scope.postValue);
		 var serviceURL = "http://192.168.0.51:8085/Propozal/friendAccept/";
		$http.post(serviceURL, $scope.postValue).success(function(data){
			if (data.entity.response == 'success') {
				console.log("hii");
				$route.reload();

			} else if (data.entity.response == 'fail') {
				alert("Not updated!!");
				$route.reload();
			}
			
		});
	}
	
//:::::::::::::::::::::::::::::::::::::::::::End AcceptFriend:::::::::::::::::
	
//::::::::::::::::::::::::::::::::::::::::::Start DeclineFriend:::::::::::::::::	
	$scope.decline=function(friendEmail){
		var userEmail=sessionStorage.UserId;
		var friendEmail=friendEmail;
		
		
		var  accepted="N";
		var  decline="Y";
		
		 
		   $scope.postValue = {
					useremail : friendEmail,
					friendemail : userEmail,
					accepted:accepted,
					decline:decline
					
				};
		console.log($scope.postValue);
		 var serviceURL = "http://192.168.0.51:8085/Propozal/friendAccept/";
		$http.post(serviceURL, $scope.postValue).success(function(data){
			if (data.entity.response == 'success') {
				console.log("hii");
				var index=
				$route.reload();

			} else if (data.entity.response == 'fail') {
				alert("Not updated!!");
				$route.reload();
			}
			
		});
	}
//::::::::::::::::::::::::::::::::::::::::::End DeclineFriend:::::::::::::::::		
	
	
  $scope.name = null;
  $scope.age = null;
  $scope.title = title;
  $scope.tt="TEST"
  
  //  This close function doesn't need to use jQuery or bootstrap, because
  //  the button has the 'data-dismiss' attribute.
  $scope.close = function() {
 	  close({
      name: $scope.name,
      age: $scope.age
    }, 500); // close, but give 500ms for bootstrap to animate
  };

//    $scope.login = function() {
//      close({
//        name: $scope.name,
//        age: $scope.age
//      }, 500); // close, but give 500ms for bootstrap to animate
//    };

  //  This cancel function must use the bootstrap, 'modal' function because
  //  the doesn't have the 'data-dismiss' attribute.
  $scope.cancel = function() {

    //  Manually hide the modal.
    $element.modal('hide');
    
    //  Now call close, returning control to the caller.
  //  close({
   //   name: $scope.name,
    //  age: $scope.age
   // }, 500); // close, but give 500ms for bootstrap to animate
  };
  $scope.visible=false;
  
  
 

}]);