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







  //var app = angular.module('sampleapp');

app.factory("RegService", function ($http, $q) {
    return {
        IsUserNameAvailablle: function (email) {
            // Get the deferred object
            var deferred = $q.defer();
            var financialYear="2015-2016";
            var noOfSharePurchased=3;
            // Initiates the AJAX call
            // $http({ method: 'GET', url: window.location.origin + '192.168.1.1?noOfSharePurchased=' + noOfSharePurchased })
            //$http({ method: 'GET', url: 'http://192.168.0.51:8081/OrganizationMaster/shareNumberCheck/" + emailx +"?financialYear=" + year "' })
            $http({ method: 'GET', url: "http://192.168.0.51:8081/OrganizationMaster/shareNumberCheck/" + noOfSharePurchased +"?financialYear=" + financialYear})
                .success(deferred.resolve).error(deferred.reject);
            // Returns the promise - Contains result once request completes
            return deferred.promise;
        }
    }
});


  app.controller('ComplexController1', ['$scope', '$element', 'title', 'RegService', 'close',function($scope, $element, title, close, RegService) {
      $scope.firstName = null;
      $scope.lastName = null;
      $scope.email = null;
      $scope.password = null;
      $scope.password_c = null;
      $scope.country = null;
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
      $scope.workingAs = null;
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
          alert("hi");
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
          /////////////
          close({
              firstName: $scope.firstName,
              lastName: $scope.lastName,
              email: $scope.email,
              password: $scope.password
          }, 500000); // close, but give 500ms for bootstrap to animate

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
          formModel.firstName = $scope.firstName;
          formModel.lastName = $scope.lastName;
          formModel.email = $scope.email;
          formModel.password = $scope.password;
          formModel.country = $scope.country;
          formModel.city = $scope.city;
          console.log(formModel);
          console.log("from complex controller 2nd");
          /////////////
          close({
              firstName: $scope.firstName,
              lastName: $scope.lastName,
              email: $scope.email,
              password: $scope.password,
              country: $scope.country,
              city: $scope.city,
          }, 5000000); // close, but give 500ms for bootstrap to animate
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
          formModel.firstName = $scope.firstName;
          formModel.lastName = $scope.lastName;
          formModel.email = $scope.email;
          formModel.password = $scope.password;
          formModel.country = $scope.country;
          formModel.city = $scope.city;
          //console.log(formModel);
          console.log("from complex controller 3rd");
          /////////////
          close({
              firstName: $scope.firstName,
              lastName: $scope.lastName,
              email: $scope.email,
              password: $scope.password,
              country: $scope.country,
              city: $scope.city,
          }, 5000000); // close, but give 500ms for bootstrap to animate
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
          formModel.firstName = $scope.firstName;
          formModel.lastName = $scope.lastName;
          formModel.email = $scope.email;
          formModel.password = $scope.password;
          formModel.country = $scope.country;
          formModel.city = $scope.city;
          //console.log(formModel);
          console.log("from complex controller 4th");
          /////////////
          close({
              firstName: $scope.firstName,
              lastName: $scope.lastName,
              email: $scope.email,
              password: $scope.password,
              country: $scope.country,
              city: $scope.city,
          }, 5000000); // close, but give 500ms for bootstrap to animate
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
          formModel.firstName = $scope.firstName;
          formModel.lastName = $scope.lastName;
          formModel.email = $scope.email;
          formModel.password = $scope.password;
          formModel.country = $scope.country;
          formModel.city = $scope.city;
          //console.log(formModel);
          console.log("from complex controller 5th");
          /////////////
          close({
              firstName: $scope.firstName,
              lastName: $scope.lastName,
              email: $scope.email,
              password: $scope.password,
              country: $scope.country,
              city: $scope.city,
          }, 5000000); // close, but give 500ms for bootstrap to animate
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
          formModel.firstName = $scope.firstName;
          formModel.lastName = $scope.lastName;
          formModel.email = $scope.email;
          formModel.password = $scope.password;
          formModel.country = $scope.country;
          formModel.city = $scope.city;
         // console.log(formModel);
          console.log("from complex controller 6th");
          /////////////
          close({
              firstName: $scope.firstName,
              lastName: $scope.lastName,
              email: $scope.email,
              password: $scope.password,
              country: $scope.country,
              city: $scope.city,
          }, 5000000); // close, but give 500ms for bootstrap to animate
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
          formModel.firstName = $scope.firstName;
          formModel.lastName = $scope.lastName;
          formModel.email = $scope.email;
          formModel.password = $scope.password;
          formModel.country = $scope.country;
          formModel.city = $scope.city;
          //console.log(formModel);
          console.log("from complex controller 7th");
          /////////////
          close({
              firstName: $scope.firstName,
              lastName: $scope.lastName,
              email: $scope.email,
              password: $scope.password,
              country: $scope.country,
              city: $scope.city,
          }, 5000000); // close, but give 500ms for bootstrap to animate
      };

      $scope.next8th = function() {
          $scope.myvalue = false;
          $scope.myvalue2nd = false;
          $scope.myvalue3rd = false;
          $scope.myvalue4th = false;
          $scope.myvalue5th = false;
          $scope.myvalue6th = false;
          $scope.myvalue7th = false;
          $scope.myvalue8th = false;
          //////////for Insert/////
          formModel.firstName = $scope.firstName;
          formModel.lastName = $scope.lastName;
          formModel.email = $scope.email;
          formModel.password = $scope.password;
          formModel.country = $scope.country;
          formModel.city = $scope.city;

          formModel.gender = $scope.gender;
          formModel.height = $scope.height;
          formModel.dobYear = $scope.dobYear;
          formModel.dobMonth = $scope.dobMonth;
          formModel.dobDate = $scope.dobDate;
          formModel.relegion = $scope.relegion;
          formModel.motherTongue = $scope.motherTongue;

          formModel.higestEdu = $scope.higestEdu;
          formModel.careerField = $scope.careerField;
          formModel.workingAs = $scope.workingAs;
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
      };

  //  This cancel function must use the bootstrap, 'modal' function because
  //  the doesn't have the 'data-dismiss' attribute.
  $scope.cancel = function() {
    //  Manually hide the modal.
    $element.modal('hide');
  };


      //// for user mail id checking start////
      $scope.checkUserAvailable = function () {
          RegService.IsUserNameAvailablle($scope.email).then(function (userstatus) {
              $scope.registrationForm.email.$setValidity('unique', userstatus);
              console.log(userstatus)
          }, function () {
              alert('error while checking user from server');
          });
      };
      //// for user mail id checking end////
      
      

}]);