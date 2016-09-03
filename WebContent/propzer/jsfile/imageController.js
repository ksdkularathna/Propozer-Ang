app.directive('ngConfirmClick', [
        function(){
            return {
                priority: 1,
                terminal: true,
                link: function (scope, element, attr) {
                    var msg = attr.ngConfirmClick || "Are you sure?";
                    var clickAction = attr.ngClick;
                    element.bind('click',function (event) {
                        if ( window.confirm(msg) ) {
                            scope.$eval(clickAction)
                        }
                    });
                }
            };
    }])

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
	
	
	$scope.setProfile=function(image){
		
		console.log(image);
				
        var serviceURL=$scope.endPointURL+'/setProfilePic/';
     	
        $http.post(serviceURL,image)    
        .success(function(data) {        	 
            
        	var active=data.entity.response;
       	 
       	 console.log(active);
       	 
       	 if(active=='success'){
       		 console.log('Successfully uploaded profile picture.');
       		 alert('Successfully uploaded profile picture!!');
       		 
       		$scope.activePath = $location.path('/myProfile');
       		 
       		 
       	 }else{
       		 console.log('Fail');
       	 }
        	
        });
		
			
		}
	
	
}]);