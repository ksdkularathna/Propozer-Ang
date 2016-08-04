app.controller('imageController', ['$scope', 'ModalService', '$log', '$window', '$http', '$location', 
                              function($scope, ModalService, $log, $window, $http, $location) {
	
	console.log('inside image ctrl');	

	$scope.endPointURL;	
	
	$http.get("json/endPoint.json")
    .success(function(data) {
  	  $scope.endPointURL= data.endPoint;
         console.log($scope.endPointURL);   
         
         var json={
              	userId:sessionStorage.UserId
              }
         
         var serviceURL=$scope.endPointURL+'/showAllImage/';
     	
         $http.post(serviceURL,json)    
         .success(function(data) {        	 
             
        	 $scope.imageList=data.entity.otherImages;
        	 
        	 console.log($scope.imageList);
         	
         });         
    });	
	
	
	
}]);