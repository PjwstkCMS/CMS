function CompanyListCtrl($scope, $http, saveEditDelete, pagination) {
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.companys = "";
    $scope.privileges = "";
    $scope.editMode = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";
    
    $scope.objectsName = "companys";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    //$scope.attributes[4] = 'companyName';
    $scope.columns = {
        'name' : "Nazwa",
    };
    $scope.columnClasses = {
        'name' : "firma-nazwa",
    };
    
    $scope.addressAttributes = [];
    $scope.addressAttributes[0] = 'country';
    $scope.addressAttributes[1] = 'city';
    $scope.addressAttributes[2] = 'streetName';
    $scope.addressAttributes[3] = 'streetNumber';
    $scope.addressAttributes[4] = 'apartmentNumber';
    $scope.addressAttributes[5] = 'postalCode';
    
    $scope.addressColumns= {
        'country': "Kraj",
        'city': "Miasto",
        'streetName': "Ulica",
        'streetNumber': "Numer",
        'apartmentNumber': "Lokal",
        'postalCode': "Kod"
    };
    $scope.addressColumnClasses = {
        'country': "address-country",
        'city': "address-city",
        'streetName': "address-streetName",
        'streetNumber': "address-streetNumber",
        'apartmentNumber': "address-apartmentNumber",
        'postalCode': "address-postalCode"
    };
    
        
    $scope.get = saveEditDelete.get($http, '/CMS/company/companys.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        
        if($scope.selected.name == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
            saveEditDelete.save($http, '/CMS/company/save/:object.htm', $scope);
        }
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.companys = $scope.initData.companys;
            $scope.privileges = $scope.initData.privileges;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            $scope.selected = "";
        } else {
            $scope.selected = object;
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
        saveEditDelete.remove($http, '/CMS/company/delete/:object.htm', $scope);
    };
    
    
}