
app.controller('HeaderCtrl', ['$scope', 'ModalService', '$log', '$window', '$http', '$location', function($scope, ModalService, $log, $window, $http, $location) {

    $scope.complexResult = null;

    ///for changing headre///
    $scope.IsVisibleBeforeLogin = true;
    $scope.IsVisibleAfterLogin = false;

    if(sessionStorage.UserId) {
        //alert(sessionStorage.UserId);
        console.log(sessionStorage.UserId)
        $scope.IsVisibleBeforeLogin = false;
        $scope.IsVisibleAfterLogin = true;
        $scope.UName = sessionStorage.UserName;
    }

    ////header chane end////
   
    $scope.showComplex = function() {

        ModalService.showModal({
            //templateUrl: "complex/complex.html",
            templateUrl: "template/partial/logInPopUp1.html",
            //templateUrl: "template/partial/regFirst.html",
            controller: "ComplexController",
            inputs: {
                title: "A More Complex Example"
            }
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                $scope.complexResult  = "User Name: " + result.name + ", Password: " + result.age;
                //console.log($scope.complexResult);
                //////////for login/////
                    var username = result.name;
                    var password= result.age;
                   // alert(username);
                    
                    $scope.vals = {
    						usernames: username,
    						passwords: password
                        };

                    //$http.post("js/loginResponse.json")
                    $http.post("http://192.168.0.51:8081/Propozal/login/",$scope.vals)
                   //$http.post("http://1.186.96.69:82/Propozal/login/",$scope.vals)
                   // $http.post("http://54.169.61.19:8080/Propozal/login/",$scope.vals)
                        .success(function (data){
                            // $scope.myData = data;
                            //////
                            $scope.myDatas = data;
                            //console.log($scope.myDatas);
                            $scope.myData_response = data.entity.response;
                            console.log($scope.myData_response);
                            /////
                            if($scope.myData_response == "success"){
                                sessionStorage.UserName= data.entity.firstName;
                                sessionStorage.UserId= data.entity.userId;
                                //alert(sessionStorage.UserName);

                                // $location.path('/myProfile');
                                var url = "http://" + $window.location.host + "/Propozal/index.html";
                               $log.log(url);
                               $window.location.href = url;
                            }else{
                                alert("Wrong Information");
                            }
                        })

                //////for log in end///////////

            });
        });

    };


    var formModel = {};
    $scope.showReg = function() {
        //alert("reg");
        ModalService.showModal({
            templateUrl: "template/partial/complex1.html",
            //templateUrl: "template/partial/logInPopUp1.html",
            controller: "ComplexController1",
            inputs: {
                title: "A More Complex Example"
            }
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                 ////////for Insert/////
                formModel.firstName = result.firstName;
                formModel.lastName = result.lastName;
                formModel.email = result.email;
                formModel.password = result.password;
                formModel.country = result.country;
                formModel.state = result.state;
                formModel.city = result.city;
                formModel.gender = result.gender;
                formModel.height = result.height;
                formModel.dobYear = result.dobYear;
                formModel.dobMonth = result.dobMonth;
                formModel.dobDate = result.dobDate;
                formModel.relegion = result.relegion;
                formModel.motherTongue = result.motherTongue;
                formModel.higestEdu = result.higestEdu;
                formModel.careerField = result.careerField;
                formModel.workingAs = result.workingAs;
                formModel.monthlySal = result.monthlySal;
                formModel.personType = result.personType;
                formModel.familyBackground = result.familyBackground;
                formModel.eduAndCareer = result.eduAndCareer;
                formModel.lookingFor = result.lookingFor;
                formModel.facebookUrl = result.facebookUrl;
                formModel.linkdinUrl = result.linkdinUrl;
                formModel.nicNumber = result.nicNumber;
                formModel.mobileNumber = result.mobileNumber;
                console.log("from my ctrl start");
                console.log(formModel);
                console.log("from my ctrl end");
                //$http.post('http://localhost:8081/OrganizationMaster/member/',$scope.formModel)
                $http.post('http://192.168.0.51:8081/Propozal/registration/',formModel)
               //$http.post('http://1.186.96.69:82/Propozal/registration/',formModel)
                //$http.post('http://54.169.61.19:8080/Propozal/registration/',formModel)
               //$http.post('js/data.json', formModel)
                    .success(function(data) {
                        alert("Your Registration is Succesfull..!!");
                       // $scope.activePath = $location.path('/myProfile');
                        var url = "http://" + $window.location.host + "/Propozal/index.html";
                        console.log("Success")
                    }).error(function(data) {
                    console.log("fail")
                })
                ////for Insert end///////////
           
            });
        });

    };

    $scope.logout = function() {
        delete sessionStorage.UserName;
        delete sessionStorage.UserId;
        $scope.IsVisibleBeforeLogin = true;
        $scope.IsVisibleAfterLogin = false;
        var url = "http://" + $window.location.host + "/Propozal/index.html";
        $log.log(url);
        $window.location.href = url;
    }

    }]);



