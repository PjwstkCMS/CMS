function CompanyListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.companies = "";
    $scope.privileges = "";
    $scope.editMode = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";
    
    $scope.objectsName = "companies";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'name',1:true};
    $scope.editValues[2] = {0:'addresses',1:true};
    
    
    $scope.addressSelector = "";
    
    $scope.addressAttributes = [];
    $scope.addressAttributes[0] = 'country';
    $scope.addressAttributes[1] = 'city';
    $scope.addressAttributes[2] = 'streetName';
    $scope.addressAttributes[3] = 'streetNumber';
    $scope.addressAttributes[4] = 'apartmentNumber';
    $scope.addressAttributes[5] = 'postalCode';
    $scope.addressAttributes[6] = 'dictId';
    
    $scope.addressValues = [];
    $scope.addressValues[0] = {0:'id', 1:false};
    $scope.addressValues[1] = {0:'country',1:true};
    $scope.addressValues[2] = {0:'city',1:true};
    $scope.addressValues[3] = {0:'streetName',1:true};
    $scope.addressValues[4] = {0:'streetNumber',1:true};
    $scope.addressValues[5] = {0:'apartmentNumber',1:true};
    $scope.addressValues[6] = {0:'postalCode',1:true};
    $scope.addressValues[7] = {0:'dictId',1:true};
        
    $scope.get = saveEditDelete.get($http, '/CMS/company/companies.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/company/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.companies = $scope.initData.companies;
            $scope.dictionaries = $scope.initData.dictionaries;
            $scope.privileges = $scope.initData.privileges;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            $scope.selected = "";
            $scope.addressSelector = "";
            $scope.addressEdit = false;
            $scope.editMode = false;
        } else {
            $scope.selected = object;
            $scope.addressSelector = "";
            $scope.addressEdit = false;
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
        $scope.selected = {
            'id': "", 'name':"", 'contactPersonId':"",'addresses':[],'privilegeKeyCodes':[]
        };
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/company/delete/:object.htm', $scope);
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
    
    $scope.addAddress = function(){
        $scope.addressSelector = {
            'id': "", 'country':"", 'city':"",'streetName':"",'streetNumber':"",
            'apartmentNumber':"",'postalCode':"",'persondataId':"-1",'companyId':"",'dictId':""
        };
        $scope.addressEdit = true;
    };
    $scope.editAddress = function(){
        if($scope.addressSelector != ""){
            $scope.addressEdit = true;
        }
    };
    $scope.cancelAddress = function(){
        $scope.addressEdit = false;
    };
    $scope.addKey = function() {
        $scope.addressSelector.companyId = $scope.selected.id;
        for (var i = 0; i<$scope.addressValues.length; i++) {  
            if($scope.addressValues[i][1] && $scope.addressSelector[$scope.addressValues[i][0]] == null){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.saveAddress($http, '/CMS/address/save/:object.htm', $scope);
        if (!$scope.selectedGroupHasKey($scope.addressSelector)) {
            $scope.selected.addresses.push($scope.addressSelector);
        }
        $scope.addressEdit = false;
    };

    $scope.removeKey = function() {
        if($scope.addressSelector !== undefined){
            saveEditDelete.removeAddress($http, '/CMS/address/delete/:object.htm', $scope);
            
            $scope.addressSelector = "";
            $scope.addressEdit = false;
        }
    };
    
    $scope.selectedGroupHasKey = function(privKeyId) {
        if ($scope.selected.addresses !== undefined) {
            for (var i = 0; i < $scope.selected.addresses.length; i++) {
                if ($scope.selected.addresses[i] == privKeyId) {
                    return true;
                }
            }
        }
        return false;
    }
}