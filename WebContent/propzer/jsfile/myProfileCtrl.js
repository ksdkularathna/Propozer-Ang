app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);

app.controller('myProfileCtrl', ['$scope', '$log','fileUpload', '$window', '$http', '$location','$route',
           function($scope, $log, fileUpload, $window, $http, $location,$route) {
		
	$scope.endPointURL;	
	
	 $scope.doneProfile=false;
	 $scope.notDone=true;
	
	//$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|file|ftp|blob):|data:image\//);
	
    if(sessionStorage.UserId) {
    	
    	
    	
    	console.log(sessionStorage.UserId + "pp");
//        $scope.IsVisibleBeforeLogin = false;
//        $scope.IsVisibleAfterLogin = true;
        var userId = sessionStorage.UserId;
        
        
        $http.get("json/endPoint.json")
        .success(function(data) {
        	
        	 $scope.IsVisibleBeforeLogin = false;
             $scope.IsVisibleAfterLogin = true;
             
             console.log($scope.IsVisibleBeforeLogin+'IsVisibleBeforeLogin');
             console.log($scope.IsVisibleAfterLogin+'IsVisibleAfterLogin');
        	
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
                 .success(function(data1) {                    
                    
                     
                     var serviceURL=$scope.endPointURL+"/pendingProfile/";
                     
                     $http.post(serviceURL,json)
                     //$http.get('js/data.json')
                         .success(function(data) {                      	 
                        	 
                        	 $scope.user = data1.entity.records[0];                            
                             
                             console.log($scope.image1);
                             console.log($scope.image2);
                             console.log($scope.image3);
                        	 
                        	 $scope.dropDown=false;
                        	 $scope.noDropDown=true;
                        	 
                             $scope.pendingData = data.entity.records[0];
                             
                             if($scope.pendingData==null){
                            	 
                            	 $scope.doneProfile=true;
                            	 $scope.notDone=false;
                            	 
                             }else if($scope.pendingData!=null){
                            	 
                            	 $scope.doneProfile=false;
                            	 $scope.notDone=true;
                            	 
                            	 if($scope.pendingData.varField=='higestEdu'){
                                	 $scope.dropDown=true;
                                	 $scope.noDropDown=false;
                                 }else if($scope.pendingData.varField=='monthlySal'){
                                	 $scope.dropDown=true;
                                	 $scope.noDropDown=false;
                                 }                                 
                                 console.log('***************************************');
                                 console.log($scope.pendingData);
                                 console.log('***************************************');
                             }  
                             
                             $scope.image=data1.entity.records[0].image;
                             $scope.image1=data1.entity.records[0].otherImages[0];
                             $scope.image2=data1.entity.records[0].otherImages[1];
                             $scope.image3=data1.entity.records[0].otherImages[2];
                             
                             
                         });                            
                 });  
             
            
             
        });   
        
        
        
    }//
    
    //var file=$scope.myFile;
	//var uploadUrl="http://1.186.96.69:82/Propozal/uploadedImages";
	
	//fileUpload.uploadFileToUrl(file, uploadUrl);
	
    $scope.uploadFileNew=function(myFile){
    	
	    var serviceURL=$scope.endPointURL+"/image/";	    
	    
        $http.post(serviceURL,myFile)
        //$http.get('js/data.json')
            .success(function(data) {  
            	
            	           
            });
    }
    
	$scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        
        var serviceURL=$scope.endPointURL+"/image/";
        
        var uploadUrl = serviceURL;
        fileUpload.uploadFileToUrl(file, uploadUrl);
    };
    
    $scope.updatePending = function(UserDetail,pendingData){
    	console.log('--------------------------------');
        console.log(UserDetail);
        console.log(pendingData);
        console.log('--------------------------------');        
        
        $scope.json1={
        	user:UserDetail
        }
        
        $scope.json2={
            	data:pendingData
            }
        
        $scope.newData={};
	    $scope.newData = angular.extend($scope.json1, $scope.json2);
        
	    //console.log( $scope.newData);
	    
	    var serviceURL=$scope.endPointURL+"/updatePending/";	    
	    
        $http.post(serviceURL, $scope.newData)
        //$http.get('js/data.json')
            .success(function(data) {  
            	
            	if(data.response=='success'){
            		//alert("Not updated!!");
            		
            	//$window.location.href = "http://" + $window.location.host + "/Propozal/index.html#/myProfile";
            		$route.reload();
            		
            	}else if(data.response=='fail'){
            		alert("Not updated!!");
            	}
                
            });           
    };
    
    //--------------------------------------------------------------------------------------------------//
    
    $scope.openPicture=function(image){
    	
//    	$scope.printToCart = function(printSectionId) {
//            var innerContents = document.getElementById(printSectionId).innerHTML;
//            var popupWinindow = window.open('', '_blank', 'width=1100,height=900,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
//            popupWinindow.document.open();
//            popupWinindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap1.min.css" /></head><body onload="window.print()">' + innerContents + '</html>');
//            popupWinindow.document.close();
//        }
    	
    	var myPopup = window.open("", "", "width=200, height=100");
    }
    
   
    
}]);
