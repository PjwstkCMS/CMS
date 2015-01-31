function EmployeeListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "Employee";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);

    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.employees = "";
    $scope.privileges = "";
    $scope.cards = "";
    $scope.editMode = false;
    $scope.newRecord = false;
    $scope.selectedSubpage = 'empData';
    $scope.selector = '';
    $scope.selector.address = '';
    $scope.selector.contract = '';
    $scope.selector.employment = '';


    $scope.objectsName = "employees";
    $scope.attributes = [];
    $scope.attributes[0] = 'forename';
    $scope.attributes[1] = 'surname';
    $scope.attributes[2] = 'phone';
    $scope.attributes[3] = 'email';
    $scope.attributes[4] = 'pesel';
    $scope.attributes[5] = 'department';
    $scope.attributes[6] = 'cardNumber';
    $scope.attributes[7] = 'position';
    $scope.attributes[8] = 'salary';

    $scope.editValues = [];
    $scope.editValues[0] = {0: 'id', 1: false};
    $scope.editValues[1] = {0: 'forename', 1: true};
    $scope.editValues[2] = {0: 'surname', 1: true};
    $scope.editValues[3] = {0: 'phone', 1: true};
    $scope.editValues[4] = {0: 'email', 1: true};
    $scope.editValues[5] = {0: 'pesel', 1: true};
    $scope.editValues[6] = {0: 'departmentId', 1: true};
    $scope.editValues[7] = {0: 'cardNumber', 1: false};
    $scope.editValues[8] = {0: 'positionId', 1: true};
    $scope.editValues[9] = {0: 'salary', 1: true};

    $scope.addressAttributes = [];
    $scope.addressAttributes[0] = 'country';
    $scope.addressAttributes[1] = 'city';
    $scope.addressAttributes[2] = 'streetName';
    $scope.addressAttributes[3] = 'streetNumber';
    $scope.addressAttributes[4] = 'apartmentNumber';
    $scope.addressAttributes[5] = 'postalCode';
    $scope.addressAttributes[6] = 'dictId';

    $scope.addressValues = [];
    $scope.addressValues[0] = {0: 'id', 1: false};
    $scope.addressValues[1] = {0: 'country', 1: true};
    $scope.addressValues[2] = {0: 'city', 1: true};
    $scope.addressValues[3] = {0: 'streetName', 1: true};
    $scope.addressValues[4] = {0: 'streetNumber', 1: true};
    $scope.addressValues[5] = {0: 'apartmentNumber', 1: true};
    $scope.addressValues[6] = {0: 'postalCode', 1: true};
    $scope.addressValues[7] = {0: 'dictId', 1: true};

    $scope.employmentAttributes = [];
    $scope.employmentAttributes[0] = 'id';
    $scope.employmentAttributes[1] = 'dateFrom';
    $scope.employmentAttributes[2] = 'dateTo';
    $scope.employmentAttributes[3] = 'dict';
    $scope.employmentAttributes[4] = 'employee';

    $scope.employmentValues = [];
    $scope.employmentValues[0] = {0: 'id', 1: false};
    $scope.employmentValues[1] = {0: 'dateFrom', 1: true};
    $scope.employmentValues[2] = {0: 'dateTo', 1: true};
    $scope.employmentValues[3] = {0: 'dictId', 1: true};
    $scope.employmentValues[4] = {0: 'employeeId', 1: true};

    $scope.contractAttributes = [];
    $scope.contractAttributes[0] = 'id';
    $scope.contractAttributes[1] = 'customer';
    $scope.contractAttributes[2] = 'employee';
    $scope.contractAttributes[3] = 'startDate';
    $scope.contractAttributes[4] = 'closeDate';
    $scope.contractAttributes[5] = 'finalisationDate';
    $scope.contractAttributes[6] = 'description';
    $scope.contractAttributes[7] = 'price';

    $scope.contractValues = [];
    $scope.contractValues[0] = {0: 'id', 1: false};
    $scope.contractValues[1] = {0: 'customerId', 1: true};
    $scope.contractValues[2] = {0: 'employeeId', 1: true};
    $scope.contractValues[3] = {0: 'startDate', 1: true};
    $scope.contractValues[4] = {0: 'closeDate', 1: true};
    $scope.contractValues[5] = {0: 'finalisationDate', 1: false};
    $scope.contractValues[6] = {0: 'description', 1: true};
    $scope.contractValues[7] = {0: 'price', 1: true};

    $scope.attributesArray = $scope.addressAttributes;

    $scope.get = saveEditDelete.get($http, '/CMS/employee/employees.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function () {
        for (var i = 0; i < $scope.editValues.length; i++) {
            if ($scope.editValues[i][1] &&
                    ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")) {
                alert("Sprawdź poprowność danych: "+columnDesc.get($scope.editValues[i][0]));
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/employee/save/:object.htm', $scope);

    };

    loadDataPromise.then(function (returnData) {
        if (returnData != null) {
            $scope.employees = $scope.initData.employees;
            $scope.positions = $scope.initData.positions;
            $scope.departments = $scope.initData.departments;
            $scope.dictionaries = $scope.initData.dictionaries;

            $scope.privileges = $scope.initData.privileges;
            $scope.cards = $scope.initData.cards;

            //$scope.addresses = $scope.initData.addresses;
           // $scope.employments = $scope.initData.employments;
            //$scope.contracts = $scope.initData.contracts;

        } else {
            alert('err');
        }
    });

    $scope.select = function (object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.selectedContracts = "";
            $scope.selectedEmployments = "";
            $scope.editSubElement = false;
            $scope.addressSelector = "";
            $scope.editMode = false;
            $scope.newRecord = false;
        } else {
            saveEditDelete.saveOldData($scope, object);
            $scope.editSubElement = false;
            $scope.newRecord = false;
            $scope.addressSelector = "";
            $scope.selected = object;
            //$scope.selectedContracts = new Array();
            //$scope.selectedEmployments = new Array();
            $scope.loadAdditional($scope.selected.id);
            /*
            for (var i = 0; i < $scope.employments.length; i++) {
                if ($scope.employments[i].employeeId == $scope.selected.id) {
                    $scope.selectedEmployments.push($scope.employments[i]);
                }
            }
            for (var i = 0; i < $scope.contracts.length; i++) {
                if ($scope.contracts[i].employeeId == $scope.selected.id) {
                    $scope.selectedContracts.push($scope.contracts[i]);
                }
            }*/
        }
    }

    $scope.edit = function () {
        $scope.editMode = true;
        $scope.newRecord = false;
    };

    $scope.cancel = function () {
        saveEditDelete.restoreOldData($scope);
        $scope.editMode = false;
        $scope.selected = "";
        $scope.newRecord = false;
    };

    $scope.create = function () {
        saveEditDelete.restoreOldData($scope);
        $scope.selected = {'id': "", 'persondataId': "", "cardId": "", "departmentId": "", "positionId": "",
            "forename": "", "surname": "", "email": "", "phone": "", "salary": "", "pesel": "", "department": "", "position": "",
            "privilegeKeyCodes": []};
        $scope.editMode = true;
        $scope.newRecord = true;
    };

    $scope.delete = function () {
        saveEditDelete.remove($http, '/CMS/employee/delete/:object.htm', $scope);
    };
    
    $scope.archive = function () {
        saveEditDelete.remove($http, '/CMS/employee/archive/:object.htm', $scope);
    };

    $scope.columnDescription = function (obj) {
        return columnDesc.get(obj);
    };
    
    $scope.checkTable = function(table, divName){
        for (var i = 0; i < table.length; i++) {
            var temp = table[i];
            if (temp[0] === divName) {
                return true;
            }
        }
        return false;
    };

    $scope.addNewElement = function (type) {
        if (type == 'address') {
            $scope.selector.address = {
                'id': "", 'country': "", 'city': "", 'streetName': "", 'streetNumber': "",
                'apartmentNumber': "", 'postalCode': "", 'persondataId': $scope.selected.persondataId, 'companyId': "-1", 'dictId': ""
            };
            $scope.editSubElement = true;
        } else if (type == 'employment') {
            $scope.selector.employment = {'id': "", 'dateFrom': "", "dateTo": "", "dictId": "", "employeeId": "", 'dict': "", 'employee': ""};
            $scope.editSubElement = true;
        } else if (type == 'contract') {
            $scope.selector.contract = {
                'id': "", 'customerId': "", 'employeeId': "", 'startDate': "", 'closeDate': "",
                'finalisationDate': "", 'description': "", 'price': "", employee: "", customer: ""
            };
            $scope.editSubElement = true;
        }
    };

    $scope.showEdit = function () {

        if ($scope.selector.address.id != '') {
            //alert('test');
            $scope.editSubElement = true;
        }
    };

    $scope.cancelElement = function () {
        $scope.editSubElement = false;
        $scope.selector.address = null;
        $scope.selector.contract = null;
        $scope.selector.employment = null;
    };

    $scope.addElement = function (type) {
        if (type == 'address') {
            $scope.selector.address.persondataId = $scope.selected.persondataId;
            for (var i = 0; i < $scope.addressValues.length; i++) {
                if ($scope.addressValues[i][1] && $scope.selector.address[$scope.addressValues[i][0]] == null) {
                    alert("Sprawdź poprowność danych adresu: "+columnDesc.get($scope.addressValues[i][0]));
                    return;
                }
            }
            saveEditDelete.saveElement($http, '/CMS/address/save/:object.htm', $scope, type);
            if (!$scope.selectedGroupHasKey($scope.selector.address)) {
                $scope.selected.addresses.push($scope.selector.address);
            }
            $scope.editSubElement = false;
        } else if (type == 'employment') {
            $scope.selector.employment.employeeId = $scope.selected.id;
            for (var i = 0; i < $scope.employmentValues.length; i++) {
                if ($scope.employmentValues[i][1] && $scope.selector.employment[$scope.employmentValues[i][0]] == null) {
                    alert("Sprawdź poprowność danych zatrudnienia: "+columnDesc.get($scope.employmentValues[i][0]));
                    return;
                }
            }
            //alert($scope.selector.employment.dateFrom);
            saveEditDelete.saveElement($http, '/CMS/employment/save/:object.htm', $scope, type);
            $scope.selectedEmployments.push($scope.selector.employment);
            $scope.editSubElement = false;
        } else if (type == 'contract') {
            $scope.selector.contract.employeeId = $scope.selected.id;
            for (var i = 0; i < $scope.contractValues.length; i++) {
                if ($scope.contractValues[i][1] && $scope.selector.contract[$scope.contractValues[i][0]] == null) {
                    alert("Sprawdź poprowność danych kontraktu: "+columnDesc.get($scope.contractValues[i][0]));
                    return;
                }
            }
            if($scope.selector.contract.finalisationDate != null){        
                var check = new Date($scope.selector.contract.startDate) < new Date($scope.selector.contract.finalisationDate);
                if(!check){
                    alert("Faktyczna data zakończenia musi być późniejsza od daty rozpoczęcia!");
                    return;
                }
            }
            saveEditDelete.saveElement($http, '/CMS/contract/save/:object.htm', $scope, type);
            $scope.selectedContracts.push($scope.selector.contract);
            $scope.editSubElement = false;
        }
    };
    
    $scope.loadAdditional = function (empId){
        $http.post('/CMS/employee/innerData/:object.htm', empId).success(function (returnData) {
                $scope.status = null;
                $scope.additionalData = returnData;
                $scope.selectedContracts = returnData.contracts;
                $scope.selectedEmployments = returnData.employments;
                //alert(returnData.privilegeKeys[0].id);
                return "Success";
            }).error(function (error) {
                $scope.status = "Błąd";
                return null;
            });
    };

    $scope.removeElement = function (type) {
        if (type == 'address') {
            if ($scope.selector.address !== undefined) {
                saveEditDelete.removeElement($http, '/CMS/address/delete/:object.htm', $scope, type);                
            }
        } else if (type == 'employment') {
            if ($scope.selector.employment !== undefined) {
                saveEditDelete.removeElement($http, '/CMS/employment/delete/:object.htm', $scope, type);
            }
        } else if (type == 'contract') {
            if ($scope.selector.contract !== undefined) {
                saveEditDelete.removeElement($http, '/CMS/contract/delete/:object.htm', $scope, type);
            }
        }
        $scope.selector[type] = "";
        $scope.editSubElement = false;
    };

    $scope.selectedGroupHasKey = function (privKeyId) {
        if ($scope.selected.addresses !== undefined) {
            for (var i = 0; i < $scope.selected.addresses.length; i++) {
                if ($scope.selected.addresses[i] == privKeyId) {
                    return true;
                }
            }
        }
        return false;
    }

    $scope.selectAction = function (obj) {
        if (obj == 'departmentId') {
            var index;
            for (var i = 0; i < $scope.departments.length; i++) {
                if ($scope.departments[i].id == $scope.selected.departmentId) {
                    index = i;
                }
            }
            $scope.selected.department = $scope.departments[index].name;
        }
        if (obj == 'positionId') {
            var index;
            for (var i = 0; i < $scope.positions.length; i++) {
                if ($scope.positions[i].id == $scope.selected.positionId) {
                    index = i;
                }
            }
            $scope.selected.position = $scope.positions[index].name;
        }
    };
}
