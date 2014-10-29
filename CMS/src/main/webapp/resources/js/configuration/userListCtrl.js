function UserListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "UserList";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "users";
    $scope.attributes = [];
    $scope.attributes[0] = 'login';
    $scope.attributes[1] = 'employee';
    $scope.attributes[2] = 'group';
    
    $scope.additionalAttributes = [];
    $scope.additionalAttributes[0] = 'email';
    $scope.additionalAttributes[1] = 'phone';
    $scope.additionalAttributes[2] = 'pesel';
    $scope.additionalAttributes[3] = 'cardId';
    
    $scope.selected = "";
    $scope.editMode = false;
    $scope.get = saveEditDelete.get($http, '/CMS/userList/users.htm', $scope);
    var loadDataPromise = $scope.get;
    
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
    
    $scope.selectAction = function(obj) {
        if(obj == 'groupId'){
            var index;
            for (var i = 0; i < $scope.groups.length; i++) {
                if ($scope.groups[i].id == $scope.selected.groupId) {
                    index = i;
                }
            }
            $scope.selected.group = $scope.groups[index].name;
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