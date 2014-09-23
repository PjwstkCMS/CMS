function ContractListCtrl($scope, $http, saveEditDelete, pagination) {
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.contracts = "";
    $scope.privileges = "";
    $scope.editMode = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";
    
    $scope.objectsName = "contracts";
    $scope.attributes = [];
    $scope.attributes[0] = 'customerId';
    $scope.attributes[1] = 'employeeId';
    //$scope.attributes[4] = 'companyName';
    $scope.columns = {
        'customerId' : "Klient",
        'employeeId' : "Pracownik"
    };
    $scope.columnClasses = {
        'customerId' : "umowa-klient",
        'employeeId' : "umowa-pracownik"
    };
        
    $scope.get = saveEditDelete.get($http, '/CMS/contract/contracts.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        
        if($scope.selected.name == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
            saveEditDelete.save($http, '/CMS/contract/save/:object.htm', $scope);
        }
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.contracts = $scope.initData.contracts;
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
        saveEditDelete.remove($http, '/CMS/contract/delete/:object.htm', $scope);
    };
    
    
}