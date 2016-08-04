angular.module('UserValidation', []).directive('validPasswordC', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue, $scope) {
                var noMatch = viewValue != scope.registrationForm.password.$viewValue
                ctrl.$setValidity('noMatch', !noMatch)
            })
        }
    }
})

  var app = angular.module('sampleapp');

//app.factory("RegService", function ($http, $q) {
//    return {
//        IsUserNameAvailablle: function (email) {
//            // Get the deferred object
//            var deferred = $q.defer();
//            var financialYear="2015-2016";
//            var noOfSharePurchased=300;
//            // Initiates the AJAX call
//            // $http({ method: 'GET', url: window.location.origin + '192.168.1.1?noOfSharePurchased=' + noOfSharePurchased })
//            //$http({ method: 'GET', url: 'http://1.186.96.69:82/OrganizationMaster/shareNumberCheck/" + emailx +"?financialYear=" + year "' })
//            $http({ method: 'GET', url: "http://1.186.96.69:82/OrganizationMaster/shareNumberCheck/" + noOfSharePurchased +"?financialYear=" + financialYear})
//                .success(deferred.resolve).error(deferred.reject);
//            // Returns the promise - Contains result once request completes
//            return deferred.promise;
//        }
//    }
//});

//////for dependency dropdown start//////
app.factory("CustomerService", ['$filter','$http',
    function($filter,$http){

//	$scope.endPointURL;
//	
//	$http.get("json/endPoint.json")
//    .success(function(data) {
//  	  $scope.endPointURL= data.endPoint;
//         console.log($scope.endPointURL);
//    });
	
        var service = {};

        var countrylist=[];
        
//        $http.get("http://1.186.96.69:82/Propozal/country/")
//        .success(function(data) {
//        	countrylist= data.entity.records;
//             console.log(countrylist);
//        });
        
//        var countrylist = [
//            { "id": 1, "country": "Srilanka" },
//            { "id": 2, "country": "Canada" },
//            { "id": 3, "country": "India" },
//        ];

        
        
        
        var statelist = [
            {"Id":1, "state":"Alaska", "countryId": 1},
            {"Id":2, "state":"California", "countryId": 1},
            {"Id":3, "state":"West Bengal", "countryId": 3},
            {"Id":4, "state":"Uttar Prades", "countryId": 3},
        ];

        var citylist = [
            {"Id":1, "city":"Anchorage", "stateId": 1},
            {"Id":2, "city":"Fairbanks", "stateId": 1},
            {"Id":3, "city":"Kolkata", "stateId": 3},
            {"Id":4, "city":"Durgapur", "stateId": 3},
        ];

        service.getCountry = function(){
        	console.log(countrylist);
            return countrylist;
        };

        service.getCountryState = function(countryId){

            var states = ($filter('filter')(statelist, {countryId: countryId}, true));
            //alert(states);
            return states;
        };


        service.getStateCity = function(stateId){
            var items = ($filter('filter')(citylist, {stateId: stateId}, true));
            return items;
        };

        return service;
    }]);


//////for dependency dropdown end//////

  app.controller('ComplexController1', ['$scope', '$element', 'title', 'close', 'CustomerService','$http',
               function($scope, $element, title, close, CustomerService,$http) {

     // $scope.countries = CustomerService.getCountry();
	  
	  $scope.endPointURL;
		
		$http.get("json/endPoint.json")
	    .success(function(data) {
	    	
	  	  $scope.endPointURL= data.endPoint;
	         console.log($scope.endPointURL);
	         
	         //--------------------------------------------------------------------------//
	         
	        /* var serviceURL=$scope.endPointURL+"/salary/";
			  
			  //$http.get("http://54.169.61.19:8080/Propozal/country/")
			  $http.get(serviceURL)
		      .success(function(data) {
		    	  $scope.salaries= data.entity.records;
		           console.log($scope.salaries);
		      });*/
	         
	         //--------------------------------------------------------------------------//
	         
	   	  var serviceURL=$scope.endPointURL+"/country/";
		  
		  //$http.get("http://54.169.61.19:8080/Propozal/country/")
		  $http.get(serviceURL)
	      .success(function(data) {
	    	  $scope.countries= data.entity.records;
	           console.log($scope.countries);
	      });
		  
	      $scope.getCountryStates = function(){
	    	  
	    	 // var countryId=$scope.country;
	    	  
	    	  var json={
	    			  countryId:$scope.country
	    	  }
	    	  
	    	  var serviceURL=$scope.endPointURL+"/states/";
	    	  
	    	  //$http.post("http://54.169.61.19:8080/Propozal/states/",json)
	    	  $http.post(serviceURL,json)
	          .success(function(data) {
	        	  $scope.states = data.entity.records;
	               console.log($scope.states);
	          });    	  
	    	  
	          //$scope.states = CustomerService.getCountryState($scope.country);
	          $scope.cities =[];
	      };

	      $scope.getStateCities = function(){
	    	  
	    	  var json={
	    			  stateId:$scope.state
	    	  }
	    	  
	    	  var serviceURL=$scope.endPointURL+"/cityy/";
	    	  
	    	  //$http.post("http://54.169.61.19:8080/Propozal/cityy/",json)
	    	  $http.post(serviceURL,json)
	          .success(function(data) {
	        	  $scope.cities = data.entity.records;
	               console.log($scope.cities);
	          });  
	    	  
	    	  
	          //$scope.cities = CustomerService.getStateCity($scope.state);
	      }


	      $scope.firstName = null;
	      $scope.lastName = null;
	      $scope.email = null;
	      $scope.password = null;
	      $scope.password_c = null;
	      $scope.country = null;
	      $scope.state = null;
	      $scope.city = null;
	      $scope.gender = null;
	      $scope.height = null;
	      $scope.dobYear = null;
	      $scope.dobMonth = null;
	      $scope.dobDate = null;
	      $scope.relegion = null;
	      $scope.motherTongue = null;
	      $scope.higestEdu = null;
	      $scope.careerField = null;
	      $scope.monthlySal = null;
	      $scope.personType = null;
	      $scope.familyBackground = null;
	      $scope.eduAndCareer = null;
	      $scope.lookingFor = null;
	      $scope.facebookUrl = null;
	      $scope.linkdinUrl = null;
	      $scope.nicNumber = null;
	      $scope.mobileNumber = null;
	  
	  //  This close function doesn't need to use jQuery or bootstrap, because
	  //  the button has the 'data-dismiss' attribute.
	      $scope.myvalue = true;
	      $scope.myvalue2nd = false;
	      $scope.myvalue3rd = false;
	      $scope.myvalue4th = false;
	      $scope.myvalue5th = false;
	      $scope.myvalue6th = false;
	      $scope.myvalue7th = false;
	      $scope.myvalue8th = false;
	  // $scope.close = function() {
	  //     //alert("hii");
	  //     console.log("hello");
	  //     $scope.myvalue = false;
	  //     $scope.myvalue2nd = true;
	 	//   close({
	  //       firstName: $scope.firstName,
	  //       lastName: $scope.lastName,
	  //       email: $scope.email,
	  //       password: $scope.password
	  //   }, 500); // close, but give 500ms for bootstrap to animate
	  // };

	      var formModel = {};
	      $scope.next = function() {
	          //alert("hi");
	          $scope.myvalue = false;
	          $scope.myvalue2nd = true;
	          $scope.myvalue3rd = false;
	          $scope.myvalue4th = false;
	          $scope.myvalue5th = false;
	          $scope.myvalue6th = false;
	          $scope.myvalue7th = false;
	          $scope.myvalue8th = false;
	          //$scope.showModal = true;
	          //////////for Insert/////
	          formModel.firstName = $scope.firstName;
	          formModel.lastName = $scope.lastName;
	          formModel.email = $scope.email;
	          formModel.password = $scope.password;
	          console.log(formModel);
	          console.log("from complex controller");
	          // /////////////
	          // close({
	          //     firstName: $scope.firstName,
	          //     lastName: $scope.lastName,
	          //     email: $scope.email,
	          //     password: $scope.password
	          // }, 500000); // close, but give 500ms for bootstrap to animate

	      };


	      $scope.next2nd = function() {
	          $scope.myvalue = false;
	          $scope.myvalue2nd = false;
	          $scope.myvalue3rd = true;
	          $scope.myvalue4th = false;
	          $scope.myvalue5th = false;
	          $scope.myvalue6th = false;
	          $scope.myvalue7th = false;
	          $scope.myvalue8th = false;
	          //////////for Insert/////
	          // formModel.firstName = $scope.firstName;
	          // formModel.lastName = $scope.lastName;
	          // formModel.email = $scope.email;
	          // formModel.password = $scope.password;
	          // formModel.country = $scope.country;
	          // formModel.city = $scope.city;
	          // console.log(formModel);
	          // console.log("from complex controller 2nd");
	          // /////////////
	          // close({
	          //     firstName: $scope.firstName,
	          //     lastName: $scope.lastName,
	          //     email: $scope.email,
	          //     password: $scope.password,
	          //     country: $scope.country,
	          //     city: $scope.city,
	          // }, 5000000); // close, but give 500ms for bootstrap to animate
	      };

	      $scope.next3rd = function() {
	          $scope.myvalue = false;
	          $scope.myvalue2nd = false;
	          $scope.myvalue3rd = false;
	          $scope.myvalue4th = true;
	          $scope.myvalue5th = false;
	          $scope.myvalue6th = false;
	          $scope.myvalue7th = false;
	          $scope.myvalue8th = false;
	          //////////for Insert/////
	          // formModel.firstName = $scope.firstName;
	          // formModel.lastName = $scope.lastName;
	          // formModel.email = $scope.email;
	          // formModel.password = $scope.password;
	          // formModel.country = $scope.country;
	          // formModel.city = $scope.city;
	          // //console.log(formModel);
	          // console.log("from complex controller 3rd");
	          // /////////////
	          // close({
	          //     firstName: $scope.firstName,
	          //     lastName: $scope.lastName,
	          //     email: $scope.email,
	          //     password: $scope.password,
	          //     country: $scope.country,
	          //     city: $scope.city,
	          // }, 5000000); // close, but give 500ms for bootstrap to animate
	      };

	      $scope.next4th = function() {
	          $scope.myvalue = false;
	          $scope.myvalue2nd = false;
	          $scope.myvalue3rd = false;
	          $scope.myvalue4th = false;
	          $scope.myvalue5th = true;
	          $scope.myvalue6th = false;
	          $scope.myvalue7th = false;
	          $scope.myvalue8th = false;
	          //////////for Insert/////
	          // formModel.firstName = $scope.firstName;
	          // formModel.lastName = $scope.lastName;
	          // formModel.email = $scope.email;
	          // formModel.password = $scope.password;
	          // formModel.country = $scope.country;
	          // formModel.city = $scope.city;
	          // //console.log(formModel);
	          // console.log("from complex controller 4th");
	          // /////////////
	          // close({
	          //     firstName: $scope.firstName,
	          //     lastName: $scope.lastName,
	          //     email: $scope.email,
	          //     password: $scope.password,
	          //     country: $scope.country,
	          //     city: $scope.city,
	          // }, 5000000); // close, but give 500ms for bootstrap to animate
	      };

	      $scope.next5th = function() {
	          $scope.myvalue = false;
	          $scope.myvalue2nd = false;
	          $scope.myvalue3rd = false;
	          $scope.myvalue4th = false;
	          $scope.myvalue5th = false;
	          $scope.myvalue6th = true;
	          $scope.myvalue7th = false;
	          $scope.myvalue8th = false;
	          //////////for Insert/////
	          // formModel.firstName = $scope.firstName;
	          // formModel.lastName = $scope.lastName;
	          // formModel.email = $scope.email;
	          // formModel.password = $scope.password;
	          // formModel.country = $scope.country;
	          // formModel.city = $scope.city;
	          // //console.log(formModel);
	          // console.log("from complex controller 5th");
	          // /////////////
	          // close({
	          //     firstName: $scope.firstName,
	          //     lastName: $scope.lastName,
	          //     email: $scope.email,
	          //     password: $scope.password,
	          //     country: $scope.country,
	          //     city: $scope.city,
	          // }, 5000000); // close, but give 500ms for bootstrap to animate
	      };

	      $scope.next6th = function() {
	          $scope.myvalue = false;
	          $scope.myvalue2nd = false;
	          $scope.myvalue3rd = false;
	          $scope.myvalue4th = false;
	          $scope.myvalue5th = false;
	          $scope.myvalue6th = false;
	          $scope.myvalue7th = true;
	          $scope.myvalue8th = false;
	          //////////for Insert/////
	         //  formModel.firstName = $scope.firstName;
	         //  formModel.lastName = $scope.lastName;
	         //  formModel.email = $scope.email;
	         //  formModel.password = $scope.password;
	         //  formModel.country = $scope.country;
	         //  formModel.city = $scope.city;
	         // // console.log(formModel);
	         //  console.log("from complex controller 6th");
	         //  /////////////
	         //  close({
	         //      firstName: $scope.firstName,
	         //      lastName: $scope.lastName,
	         //      email: $scope.email,
	         //      password: $scope.password,
	         //      country: $scope.country,
	         //      city: $scope.city,
	         //  }, 5000000); // close, but give 500ms for bootstrap to animate
	      };

	      $scope.next7th = function() {
	          $scope.myvalue = false;
	          $scope.myvalue2nd = false;
	          $scope.myvalue3rd = false;
	          $scope.myvalue4th = false;
	          $scope.myvalue5th = false;
	          $scope.myvalue6th = false;
	          $scope.myvalue7th = false;
	          $scope.myvalue8th = true;
	          //////////for Insert/////
	          // formModel.firstName = $scope.firstName;
	          // formModel.lastName = $scope.lastName;
	          // formModel.email = $scope.email;
	          // formModel.password = $scope.password;
	          // formModel.country = $scope.country;
	          // formModel.city = $scope.city;
	          // //console.log(formModel);
	          // console.log("from complex controller 7th");
	          // /////////////
	          // close({
	          //     firstName: $scope.firstName,
	          //     lastName: $scope.lastName,
	          //     email: $scope.email,
	          //     password: $scope.password,
	          //     country: $scope.country,
	          //     city: $scope.city,
	          // }, 5000000); // close, but give 500ms for bootstrap to animate
	      };

	      $scope.next8th = function() {
	          // $scope.myvalue = false;
	          // $scope.myvalue2nd = false;
	          // $scope.myvalue3rd = false;
	          // $scope.myvalue4th = false;
	          // $scope.myvalue5th = false;
	          // $scope.myvalue6th = false;
	          // $scope.myvalue7th = false;
	          // $scope.myvalue8th = false;
	          //////////for Insert/////
	          formModel.firstName = $scope.firstName;
	          formModel.lastName = $scope.lastName;
	          formModel.email = $scope.email;
	          formModel.password = $scope.password;
	          formModel.country = $scope.country;
	          formModel.city = $scope.city;
	          formModel.state = $scope.state;

	          formModel.gender = $scope.gender;
	          formModel.height = $scope.height;
	          formModel.dobYear = $scope.dobYear;
	          formModel.dobMonth = $scope.dobMonth;
	          formModel.dobDate = $scope.dobDate;
	          formModel.relegion = $scope.relegion;
	          formModel.motherTongue = $scope.motherTongue;

	          formModel.higestEdu = $scope.higestEdu;
	          formModel.careerField = $scope.careerField;
	          formModel.monthlySal = $scope.monthlySal;
	          formModel.personType = $scope.personType;
	          formModel.familyBackground = $scope.familyBackground;
	          formModel.eduAndCareer = $scope.eduAndCareer;
	          formModel.lookingFor = $scope.lookingFor;

	          formModel.facebookUrl = $scope.facebookUrl;
	          formModel.linkdinUrl = $scope.linkdinUrl;
	          formModel.nicNumber = $scope.nicNumber;
	          formModel.mobileNumber = $scope.mobileNumber;
	          
	          console.log(formModel);
	          console.log("from complex controller 8th");
	          /////////////
	          close({
	              firstName: $scope.firstName,
	              lastName: $scope.lastName,
	              email: $scope.email,
	              password: $scope.password,
	              country: $scope.country,
	              city: $scope.city,
	              gender: $scope.gender,
	              height: $scope.height,
	              dobYear: $scope.dobYear,
	              dobMonth: $scope.dobMonth,
	              dobDate: $scope.dobDate,
	              relegion: $scope.relegion,
	              motherTongue: $scope.motherTongue,
	              higestEdu: $scope.higestEdu,
	              careerField: $scope.careerField,
	              workingAs: $scope.workingAs,
	              monthlySal: $scope.monthlySal,
	              personType: $scope.personType,
	              familyBackground: $scope.familyBackground,
	              eduAndCareer: $scope.eduAndCareer,
	              lookingFor: $scope.lookingFor,
	              facebookUrl: $scope.facebookUrl,
	              linkdinUrl: $scope.linkdinUrl,
	              nicNumber: $scope.nicNumber,
	              mobileNumber: $scope.mobileNumber
	          }, 500); // close, but give 500ms for bootstrap to animate

	          $element.modal('hide');
	      };

	  //  This cancel function must use the bootstrap, 'modal' function because
	  //  the doesn't have the 'data-dismiss' attribute.
	  $scope.cancel = function() {
	    //  Manually hide the modal.
	    $element.modal('hide');
	  };

	  
	  $scope.checkUserAvailable=function(){
		  
	      var json={
	    		  emailId:$scope.email
	          }
	          
	      	var serviceURL=$scope.endPointURL+"/emailCheck/";
	      
	          //$http.post("http://54.169.61.19:8080/Propozal/emailCheck/",json)
	          $http.post(serviceURL,json)
	          //$http.get('js/data.json')
	              .success(function(data) {  
	            	  
	            	  console.log(data);
	            	  
	                  var status = data.response;
	                  
	                  console.log(status);
	                  
	                  console.log(status);
	                  
	                  if(status=="valid"){
	                	  
	                  }else if(status=="notValid"){
	                	  alert('User name not available.');
	                  }
	              });
		  
	  };
	  

	      //// for user mail id checking start////
//	      $scope.checkUserAvailable = function () {
//	          RegService.IsUserNameAvailablle($scope.email).then(function (userstatus) {
//	              $scope.registrationForm.email.$setValidity('unique', userstatus);
//	              console.log(userstatus)
//	          }, function () {
//	              alert('error while checking user from server');
//	          });
//	      };
	      //// for user mail id checking end////
	      
	         
	    });
	  
}]);