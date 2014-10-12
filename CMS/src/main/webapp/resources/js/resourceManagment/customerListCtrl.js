function CustomerListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "Customer";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    $scope.selectedCompany = 'test';
    $scope.selectedCompanyAddress = "";
    $scope.selectedContracts = [];

    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.customers = "";
    $scope.privileges = "";
    $scope.editMode = false;
    $scope.restoreData = "";
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";

    $scope.objectsName = "customers";
    $scope.attributes = [];
    $scope.attributes[0] = 'forename';
    $scope.attributes[1] = 'surname';
    $scope.attributes[2] = 'phone';
    $scope.attributes[3] = 'email';
    
    $scope.contractAttributes = [];
    $scope.contractAttributes[0] = 'id';
    $scope.contractAttributes[1] = 'employeeId';
    $scope.contractAttributes[2] = 'startDate';
    $scope.contractAttributes[3] = 'closeDate';
    $scope.contractAttributes[4] = 'finalisationDate';
    $scope.contractAttributes[5] = 'description';
    $scope.contractAttributes[6] = 'price';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'forename', 1:true};
    $scope.editValues[2] = {0:'surname', 1:true};
    $scope.editValues[3] = {0:'phone', 1:true};
    $scope.editValues[4] = {0:'email', 1:true};
    $scope.editValues[5] = {0:'companyId', 1:true};
    
    $scope.contractSelector = "";
    $scope.contractValues = [];
    $scope.contractValues[0] = {0:'id', 1:false};
    $scope.contractValues[1] = {0:'customerId',1:true};
    $scope.contractValues[2] = {0:'employeeId',1:true};
    $scope.contractValues[3] = {0:'startDate',1:true};
    $scope.contractValues[4] = {0:'closeDate',1:true};
    $scope.contractValues[5] = {0:'finalisationDate',1:false};
    $scope.contractValues[6] = {0:'description',1:true};
    $scope.contractValues[7] = {0:'price',1:true};
    

    $scope.get = saveEditDelete.get($http, '/CMS/customer/customers.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/customer/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.customers = $scope.initData.customers;
            $scope.companies = $scope.initData.companies;
            $scope.contracts = $scope.initData.contracts;
        } else {
            alert('err');
        }
    });

    $scope.contractSelect = function(object) {
        if ($scope.contractSelector == object) {
            $scope.contractSelector = "";
        } else {
            $scope.contractSelector = object;
        }
    }
    
    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.selectedCompany = "";
            $scope.contractSelector = "";
        } else {            
            $scope.selected = object; 
            $scope.contractSelector = "";
            $scope.selectedContracts = [];
            $scope.selectedCompany = "";
            for (var i = 0; i<$scope.companies.length; i++) {
                if($scope.companies[i].id == $scope.selected.companyId) {
                    $scope.selectedCompany = $scope.companies[i];
                    $scope.selectedCompanyAddress = $scope.selectedCompany.addresses[0];
                }
            }
            for (var i = 0; i<$scope.contracts.length; i++) {
                if($scope.contracts[i].customerId == $scope.selected.id) {
                    $scope.selectedContracts.push($scope.contracts[i]);
                }
            }
            saveEditDelete.saveOldData($scope,object);
        }
    }
    
    $scope.checkCustomerId = function(con){
        if(con.customerId == $scope.selected.id) {
            return true;
        }
        return false;
    };

    $scope.edit = function() {
        $scope.editMode = true;
    };

    $scope.cancel = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.editMode = false;
        $scope.selected = "";
    };

    $scope.create = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.selected = {'id':"",'companyId':"","persondataId":""
            ,"forename":"","surname":"","email":"","phone":"","privilegeKeyCodes":[]};
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/customer/delete/:object.htm', $scope);
    };

    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}