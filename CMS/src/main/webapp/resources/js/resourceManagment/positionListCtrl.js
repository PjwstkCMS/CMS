function PositionListCtrl($scope, $http, saveEditDelete, pagination) {
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.positions = "";
    $scope.privileges = "";
    $scope.editMode = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";
    
    $scope.objectsName = "positions";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'description';
    //$scope.attributes[4] = 'companyName';
    $scope.columns = {
        'name' : "Nazwa",
        'description': "Opis",
    };
    $scope.columnClasses = {
        'name' : "stanowisko-nazwa",
        'description': "stanowisko-opis",
    };
        
    $scope.get = saveEditDelete.get($http, '/CMS/position/positions.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        
        if(($scope.selected.name == null) || $scope.selected.surname == null || $scope.selected.phone == null || $scope.selected.email == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
            saveEditDelete.save($http, '/CMS/position/save/:object.htm', $scope);
        }
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.positions = $scope.initData.positions;
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
        saveEditDelete.remove($http, '/CMS/position/delete/:object.htm', $scope);
    };
    
    
}