function ArchiveListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {

    $scope.employees = '';
    $scope.show = "";
    $scope.selected = "";

    $scope.loadData = function (link) {
        $scope.show = link;
        $http.get(link).success(function (returnData) {
            $scope.status = null;
            $scope.initData = returnData;
            $scope.employees = returnData.employees;
            //alert($scope.employees);
            //$scope[$scope.objectsName] = returnData[$scope.objectsName];
            //$scope.objects = returnData[$scope.objectsName];
            //alert(returnData.privilegeKeys[0].id);
            return "Success";
        }).error(function (err) {
            alert(err);
        });
    }

    $scope.select = function (object) {
        $scope.selected = object;
    }

    $scope.deleteEmp = function () {
        saveEditDelete.remove($http, '/CMS/employee/delete/:object.htm', $scope);
        var index;
        for (var i = 0; i < $scope.employees.length; i++) {
            if ($scope.employees[i].id == $scope.selected.id) {
                index = i;
            }
        }
        $scope[$scope.objectsName].splice(index, 1);
    };

    $scope.deleteCus = function () {
        saveEditDelete.remove($http, '/CMS/customer/delete/:object.htm', $scope);
        var index;
        for (var i = 0; i < $scope.employees.length; i++) {
            if ($scope.employees[i].id == $scope.selected.id) {
                index = i;
            }
        }
        $scope[$scope.objectsName].splice(index, 1);
    };

    $scope.deleteCom = function () {
        saveEditDelete.remove($http, '/CMS/company/delete/:object.htm', $scope);
        var index;
        for (var i = 0; i < $scope.employees.length; i++) {
            if ($scope.employees[i].id == $scope.selected.id) {
                index = i;
            }
        }
        $scope[$scope.objectsName].splice(index, 1);
    };

    $scope.deleteCon = function () {
        saveEditDelete.remove($http, '/CMS/contract/delete/:object.htm', $scope);
        var index;
        for (var i = 0; i < $scope.employees.length; i++) {
            if ($scope.employees[i].id == $scope.selected.id) {
                index = i;
            }
        }
        $scope[$scope.objectsName].splice(index, 1);
    };

}
