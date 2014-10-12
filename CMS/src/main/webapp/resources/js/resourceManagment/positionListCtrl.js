function PositionListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "Position";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.positions = "";
    $scope.privileges = "";
    $scope.selectedEmployees = "";
    $scope.editMode = false;
    //$scope.displayPage = true;
    //$scope.displayPageName = "customerPage";
    
    $scope.objectsName = "positions";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'description';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'name',1:true};
    $scope.editValues[2] = {0:'description',1:true};
    
    $scope.employeeSelector = "";
    
    $scope.employeeAttributes = [];
    $scope.employeeAttributes[0] = 'id';
    $scope.employeeAttributes[1] = 'forename';
    $scope.employeeAttributes[2] = 'surname';
    $scope.employeeAttributes[3] = 'phone';
    $scope.employeeAttributes[4] = 'email';
    $scope.employeeAttributes[5] = 'departmentId';
        
    $scope.get = saveEditDelete.get($http, '/CMS/position/positions.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/position/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.positions = $scope.initData.positions;
            $scope.privileges = $scope.initData.privileges;
            $scope.employees = $scope.initData.employees;
            $scope.departments = $scope.initData.departments;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.selectedEmployees = "";
            $scope.editMode = false;
        } else {
            saveEditDelete.saveOldData($scope,object);
            $scope.selected = object;
            $scope.selectedEmployees = new Array();
            for (var i = 0; i<$scope.employees.length; i++) {  
                if($scope.employees[i].positionId == $scope.selected.id) {
                    $scope.selectedEmployees.push($scope.employees[i]);
                }                
            }
        }
    }
    
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
        $scope.selected = {
            'id':"",'name':"",'description':""
        };
        $scope.selectedEmployees = "";
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/position/delete/:object.htm', $scope);
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}