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
    
    $scope.editValues = [];
    $scope.editValues[0] = {0: 'employeIdNum', 1: false};
    $scope.editValues[1] = {0: 'login', 1: true};
    $scope.editValues[2] = {0: 'password', 1: true};
    $scope.editValues[3] = {0: 'groupId', 1: true};
    
    $scope.additionalAttributes = [];
    $scope.additionalAttributes[0] = 'email';
    $scope.additionalAttributes[1] = 'phone';
    $scope.additionalAttributes[2] = 'pesel';
    $scope.additionalAttributes[3] = 'cardId';
    
    $scope.selected = "";
    $scope.editMode = false;
    $scope.newRecord = false;
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

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność danych: "+columnDesc.get($scope.editValues[i][0]));
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/userList/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.newRecord = false;
        } else {
            saveEditDelete.saveOldData($scope,object);
            $scope.selected = object;
            $scope.newRecord = false;
        }
    }

    $scope.cancel = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.editMode = false;
        $scope.selected = "";
        $scope.newRecord = false;
    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/userList/delete/:object.htm', $scope);
    };
    
    $scope.resetPass = function() {
        saveEditDelete.save($http, '/CMS/userList/resetPass/:object.htm', $scope);
    };
    
    $scope.create = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.selected = {'id':"",'employeeId':"","persondataId":"","groupId":"",
            "login":"","password":"","employee":"","group":"","photoHash":""
            ,"privilegeKeyCodes":[]};
        $scope.editMode = true;
        $scope.newRecord = true;
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
        if(obj == 'employeeIdNum'){
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