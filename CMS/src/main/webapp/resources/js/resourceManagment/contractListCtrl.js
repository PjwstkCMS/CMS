function ContractListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "simple";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);

    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.contracts = "";
    $scope.privileges = "";
    $scope.editMode = false;
    $scope.newRecord = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";
    
    $scope.objectsName = "contracts";
    $scope.attributes = [];
    $scope.attributes[0] = 'id';
    $scope.attributes[1] = 'customer';
    $scope.attributes[2] = 'employee';
    $scope.attributes[3] = 'startDate';
    $scope.attributes[4] = 'closeDate';
    $scope.attributes[5] = 'finalisationDate';
    $scope.attributes[6] = 'description';
    $scope.attributes[7] = 'price';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'customerId',1:true};
    $scope.editValues[2] = {0:'employeeId',1:true};
    $scope.editValues[3] = {0:'startDate',1:true};
    $scope.editValues[4] = {0:'closeDate',1:true};
    $scope.editValues[5] = {0:'finalisationDate',1:false};
    $scope.editValues[6] = {0:'description',1:true};
    $scope.editValues[7] = {0:'price',1:true};

        
    $scope.get = saveEditDelete.get($http, '/CMS/contract/contracts.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność danych: "+columnDesc.get($scope.editValues[i][0]));
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/contract/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.contracts = $scope.initData.contracts;
            $scope.customers = $scope.initData.customers;
            $scope.employees = $scope.initData.employees;
            $scope.privileges = $scope.initData.privileges;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.newRecord = false;
        } else {
            $scope.selected = object;
            saveEditDelete.saveOldData($scope,object);
            $scope.newRecord = false;
        }
    }
    
    $scope.edit = function() {
        $scope.editMode = true;
        $scope.newRecord = false;
    };

    $scope.cancel = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.editMode = false;
        $scope.selected = "";
        $scope.newRecord = false;
    };

    $scope.create = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.selected = {
            'id': "", 'customerId':"", 'employeeId':"",'startDate':"",'closeDate':"",
            'finalisationDate':"",'description':"",'price':"", employee:"", customer:""
        };
        $scope.newRecord = true;
        $scope.editMode = true;
    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/contract/delete/:object.htm', $scope);
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
    
    $scope.selectAction = function(obj) {
        if(obj == 'customerId'){
            var index;
            for (var i = 0; i < $scope.customers.length; i++) {
                if ($scope.customers[i].id == $scope.selected.customerId) {
                    index = i;
                }
            }
            $scope.selected.customer = $scope.customers[index].forename + ' ' + $scope.customers[index].surname;
        }
        if(obj == 'employeeId'){
            var index;
            for (var i = 0; i < $scope.employees.length; i++) {
                if ($scope.employees[i].id == $scope.selected.employeeId) {
                    index = i;
                }
            }
            $scope.selected.employee = $scope.employees[index].forename + ' ' + $scope.employees[index].surname;
        }
    };
    
}