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
    $scope.attributes[0] = 'id';
    $scope.attributes[1] = 'customerId';
    $scope.attributes[2] = 'employeeId';
    $scope.attributes[3] = 'startDate';
    $scope.attributes[4] = 'closeDate';
    $scope.attributes[5] = 'finalisationDate';
    $scope.attributes[6] = 'description';
    $scope.attributes[7] = 'price';
    
    //$scope.attributes[4] = 'companyName';
    $scope.columns = {
        'id' : "Id umowy",
        'customerId' : "Klient",
        'employeeId' : "Pracownik",
        'startDate' : "Data rozpoczęcia",
        'closeDate' : "Planowana data zakończenia",
        'finalisationDate' : "Data zakończenia",
        'description' : "Opis",
        'price' : "Cena"
    };
    $scope.columnClasses = {
        'id' : "kontrakt-id",
        'customerId' : "kontrakt-customerId",
        'employeeId' : "kontrakt-employeeId",
        'startDate': "kontrakt-startDate",
        'closeDate': "kontrakt-closeDate",
        'finalisationDate': "kontrakt-finalisationDate",
        'description': "kontrakt-description",
        'price': "kontrakt-price"
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