app.controller('activationController', ['$scope', 'ModalService', '$log', '$window', '$http', '$location', '$routeParams',
                              function($scope, ModalService, $log, $window, $http, $location,$routeParams) {
	
	console.log('inside activation ctrl');	

	$scope.endPointURL;	
	
	$http.get("json/endPoint.json")
    .success(function(data) {
  	  $scope.endPointURL= data.endPoint;
         console.log($scope.endPointURL);        
         
         var empNo = $routeParams.keyId;
            
         console.log(empNo); 
         
         var json={
               	userId:empNo
               }
         
         var serviceURL=$scope.endPointURL+'/activateUser/';
      	
         $http.post(serviceURL,json)    
         .success(function(data) {        	 
             
        	 var active=data.entity.response;
        	 
        	 console.log(active);
        	 
        	 if(active=='success'){
        		 console.log('You are an activated user!!');
        		 alert('You are an activated user!!');
        	 }else{
        		 alert('You activation time has Expired')
        		 console.log('Fail');
        	 }
         	
         });  
         
    });	
	
	
	
}]);