app.controller('startController', ['$scope', '$log','fileUpload', '$window', '$http', '$location','$route',
           function($scope, $log, fileUpload, $window, $http, $location,$route) {

	$scope.endPointURL;	
		
    if(sessionStorage.UserId) {
            
        $http.get("json/endPoint.json")
        .success(function(data) {
        	
      	  $scope.endPointURL= data.endPoint;
             console.log($scope.endPointURL);
             
             var serviceURL=$scope.endPointURL+"/profile/";
             
             console.log('**************serviceURL****************');
             console.log(serviceURL);
             
             var json={
                 	userId:sessionStorage.UserId
                 }
             
             $http.post(serviceURL,json)
             //$http.post("http://1.186.96.69:82/Propozal/profile/",json)
             //$http.get('js/data.json')
                 .success(function(data) {
                	 
                	 console.log("*******************************************Start");
                      
                 });             
        });        
    }

}]);