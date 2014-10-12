function UserListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "simple";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "users";
    $scope.attributes = [];
    $scope.attributes[0] = 'login';
    $scope.attributes[1] = 'groupName';
    $scope.attributes[2] = 'employeeId';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'login',1:true};
    $scope.editValues[2] = {0:'groupName',1:true};
    $scope.editValues[3] = {0:'employeeId',1:true};
    
    $scope.selected = "";
    $scope.editMode = false;
    $scope.get = saveEditDelete.get($http, '/CMS/userList/users.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/userList/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.users = $scope.initData.users;
            $scope.employees = $scope.initData.employees;
            $scope.groups = $scope.initData.groups;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
        } else {
            saveEditDelete.saveOldData($scope,object);
            $scope.selected = object;
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
        $scope.selected = new Object();
        $scope.selected.groupId = -1;
        $scope.selected.employeeId = -1;
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/userList/delete/:object.htm', $scope);
    };
    
    $scope.getGroupName = function(id) {
      for (var i = 0; i<$scope.groups.length; i++) {
          if($scope.groups[i].id == id){
              return $scope.groups[i].name;
          }
      }  
    };
    
    $scope.generateLogin = function(emp) {
        var login = emp.name.substring(0,1) + emp.surname.substring(0,emp.surname.length);
        login = login.toLowerCase();
        return login;
    };
    
    $scope.checkEditPrivileges = function() {
        return true;
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}