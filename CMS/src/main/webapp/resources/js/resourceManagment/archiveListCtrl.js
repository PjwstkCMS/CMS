function ArchiveListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {

    $scope.employees = '';
    $scope.companies = '';
    $scope.customers = '';
    $scope.contracts = '';
    $scope.show = "";
    $scope.selected = "";
    $scope.objectsName = "employees";

    $scope.loadData = function (link) {
        $scope.show = link;
        $http.get(link).success(function (returnData) {
            $scope.status = null;
            $scope.initData = returnData;
            $scope.employees = returnData.employees;
            $scope.customers = returnData.customers;
            $scope.contracts = returnData.contracts;
            $scope.companies = returnData.companies;
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

    $scope.dearchiveEmp = function () {
        $http.post(
                '/CMS/archive/dearchiveEmployee/:id.htm',
                {id: $scope.selected.id}).success(function () {
            var index;
            for (var i = 0; i < $scope.employees.length; i++) {
                if ($scope.employees[i].id == $scope.selected.id) {
                    index = i;
                }
            }
            $scope.employees.splice(index, 1);
            $scope.selected = "";
            $scope.showOperationMessage = true;
            $scope.operationMessage = "Operacja usuwania udana"
        }).error(function (error) {
            $scope.showOperationMessage = true;
            $scope.operationMessage = "Operacja usuwania nie udana"
        });

    }
    $scope.dearchiveCon = function () {
        $http.post(
                '/CMS/archive/dearchiveContract/:id.htm',
                {id: $scope.selected.id}).success(function () {
            var index;
            for (var i = 0; i < $scope.contracts.length; i++) {
                if ($scope.contracts[i].id == $scope.selected.id) {
                    index = i;
                }
            }
            $scope.contracts.splice(index, 1);
            $scope.selected = "";
            $scope.showOperationMessage = true;
            $scope.operationMessage = "Operacja usuwania udana"
        }).error(function (error) {
            $scope.showOperationMessage = true;
            $scope.operationMessage = "Operacja usuwania nie udana"
        });

    }
    $scope.dearchiveCus = function () {
         $http.post(
                '/CMS/archive/dearchiveCustomer/:id.htm',
                {id: $scope.selected.id}).success(function () {
            var index;
            for (var i = 0; i < $scope.customers.length; i++) {
                if ($scope.customers[i].id == $scope.selected.id) {
                    index = i;
                }
            }
            $scope.customers.splice(index, 1);
            $scope.selected = "";
            $scope.showOperationMessage = true;
            $scope.operationMessage = "Operacja usuwania udana"
        }).error(function (error) {
            $scope.showOperationMessage = true;
            $scope.operationMessage = "Operacja usuwania nie udana"
        });

    }
    $scope.dearchiveCom = function () {
        $http.post(
                '/CMS/archive/dearchiveCompany/:id.htm',
                {id: $scope.selected.id}).success(function () {
            var index;
            for (var i = 0; i < $scope.companies.length; i++) {
                if ($scope.companies[i].id == $scope.selected.id) {
                    index = i;
                }
            }
            $scope.customers.splice(index, 1);
            $scope.selected = "";
            $scope.showOperationMessage = true;
            $scope.operationMessage = "Operacja usuwania udana"
        }).error(function (error) {
            $scope.showOperationMessage = true;
            $scope.operationMessage = "Operacja usuwania nie udana"
        });

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
