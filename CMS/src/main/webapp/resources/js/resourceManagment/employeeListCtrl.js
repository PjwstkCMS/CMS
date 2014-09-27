function EmployeeListCtrl($scope, $http, saveEditDelete, pagination) {
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.employees = "";
    $scope.privileges = "";
    $scope.cards = "";
    $scope.editMode = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";
    
    $scope.objectsName = "employees";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'surname';
    $scope.attributes[2] = 'phone';
    $scope.attributes[3] = 'email';
    $scope.attributes[4] = 'departmentId';
    $scope.attributes[5] = 'cardId';
    //$scope.attributes[4] = 'companyName';
    $scope.columns = {
        'name' : "Imię",
        'surname': "Nazwisko",
        'phone': "Telefon",
        'email': "Email",
        'departmentId' : "Departemant",
        'cardId' : "Numer karty"
    };
    $scope.columnClasses = {
        'name' : "pracownik-name",
        'surname': "pracownik-surname",
        'phone': "pracownik-phone",
        'email': "pracownik-email",
        'departmentId' : "pracownik-departemant"
    };
        
    $scope.get = saveEditDelete.get($http, '/CMS/employee/employees.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        
        if(($scope.selected.name == null) || $scope.selected.surname == null || $scope.selected.phone == null || $scope.selected.email == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
            saveEditDelete.save($http, '/CMS/employee/save/:object.htm', $scope);
        }
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.employees = $scope.initData.employees;
            $scope.privileges = $scope.initData.privileges;
            $scope.cards = $scope.initData.cards;
            $scope.addresses = $scope.initData.addresses;
            $scope.employments = $scope.initData.employments;
            $scope.contracts = $scope.initData.contracts;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            $scope.selected = "";
        } else {
            $scope.selected = object;
            $scope.selectedAddress = new Array();
            $scope.selectedContracts = new Array();
            $scope.selectedEmployments = new Array();
            for (var i = 0; i<$scope.employments.length; i++) {
                if($scope.employments[i].employeeId == $scope.selected.id) {
                    $scope.selectedEmployments.push($scope.employments[i]);
                }
            }
            for (var i = 0; i<$scope.addresses.length; i++) {
                if($scope.addresses[i].persondataId == $scope.selected.persondataId) {
                    $scope.selectedAddress.push($scope.addresses[i]);
                }
            }
            for (var i = 0; i<$scope.contracts.length; i++) {
                if($scope.contracts[i].employeeId == $scope.selected.id) {
                    $scope.selectedContracts.push($scope.contracts[i]);
                }
            }
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
    };

    $scope.cancel = function() {
        $scope.editMode = false;
        $scope.selected = "";
    };

    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/employee/delete/:object.htm', $scope);
    };
    
    
}