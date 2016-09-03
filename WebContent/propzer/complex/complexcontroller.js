var app = angular.module('sampleapp');

app.controller('ComplexController', [
  '$scope', 'ModalService','$element', 'title', 'close','$http','$window', '$location', '$route',
  function($scope, ModalService,$element, title, close,$http,$window, $location, $route) {

  $scope.name = null;
  $scope.age = null;
  $scope.title = title;
  
  //  This close function doesn't need to use jQuery or bootstrap, because
  //  the button has the 'data-dismiss' attribute.
  $scope.close = function() {
 	  close({
      name: $scope.name,
      age: $scope.age
    }, 500); // close, but give 500ms for bootstrap to animate
  };

    $scope.login = function() {
      close({
        name: $scope.name,
        age: $scope.age
      }, 500); // close, but give 500ms for bootstrap to animate
    };

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

//#################################ForgetPassword##############################################//
  $scope.forgotPas = function() {
     // alert("reg");
      $scope.visible=true;
      ModalService.showModal({
          templateUrl: "template/forget.html",           
          controller: "startController1",
          inputs: {
              title: "A More Complex Example"
          }
      }).then(function(modal) {
          modal.element.modal();
          modal.close.then(function(result) {
        	 
        	  $scope.complexResult1  = "User Name: " + result.email;
        	  console.log($scope.complexResult1);
        	  console.log('inside forgot..complexResult1');
        	  var emailId= result.email;
        	  $scope.vals = {
        			  email: emailId
						
                  };
        	  $http.post("http://54.251.163.21:8080/Propozal/forget/",$scope.vals)
        	  
        	  // console.log(username);
        	  .success(function(data) {
                        alert("Please Check Your Email..!!");
                       // $scope.activePath = $location.path('/myProfile');
                        var url = "http://" + $window.location.host + "/Propozal/index.html";
                        
                        $window.location.reload();
                        console.log("Success")
                    }).error(function(data) {
                    console.log("fail")
                    alert("You are not registered user..!!");
                })
          });
      });

  };
  }]);
//#################################End ForgetPassword##############################################//

app.controller('startController1', ['$scope','$element', 'title', 'close',function ($scope,$element,title,close) {
	  $scope.email =null;
	  $scope.forgetMail = function () {
		  alert("Please wait we are sending you a reset password email ..!!");
		 	  close({
		 		 email: $scope.email
		   
		    }, 500); // close, but give 500ms for bootstrap to animate
		  
		}
}]);

