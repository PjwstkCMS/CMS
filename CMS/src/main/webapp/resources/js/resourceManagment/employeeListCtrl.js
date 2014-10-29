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
    $scope.attributes[6] = 'cardId';
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
    $scope.editValues[7] = {0: 'cardId', 1: false};
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
    $scope.employmentAttributes[0] = '';

    $scope.attributesArray = $scope.addressAttributes;

    $scope.get = saveEditDelete.get($http, '/CMS/employee/employees.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function () {
        for (var i = 0; i < $scope.editValues.length; i++) {
            if ($scope.editValues[i][1] &&
                    ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")) {
                alert("Sprawdź poprowność wprowadzonych danych");
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

            $scope.addresses = $scope.initData.addresses;
            $scope.employments = $scope.initData.employments;
            $scope.contracts = $scope.initData.contracts;

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
            $scope.selectedContracts = new Array();
            $scope.selectedEmployments = new Array();
            for (var i = 0; i < $scope.employments.length; i++) {
                if ($scope.employments[i].employeeId == $scope.selected.id) {
                    $scope.selectedEmployments.push($scope.employments[i]);
                }
            }
            for (var i = 0; i < $scope.contracts.length; i++) {
                if ($scope.contracts[i].employeeId == $scope.selected.id) {
                    $scope.selectedContracts.push($scope.contracts[i]);
                }
            }
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

    $scope.columnDescription = function (obj) {
        return columnDesc.get(obj);
    };

    $scope.addNewElement = function (type) {
        if (type == 'address') {
            $scope.selector.address = {
                'id': "", 'country': "", 'city': "", 'streetName': "", 'streetNumber': "",
                'apartmentNumber': "", 'postalCode': "", 'persondataId': $scope.selected.persondataId, 'companyId': "-1", 'dictId': ""
            };
            $scope.editSubElement = true;
        }
    };

    $scope.cancelAddress = function () {
        $scope.addressEdit = false;
        if ($scope.addressSelector.id == "") {
            $scope.addressSelector = null;
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
                if ($scope.addressValues[i][1] && $scope.addressSelector[$scope.addressValues[i][0]] == null) {
                    alert("Sprawdź poprowność wprowadzonych danych");
                    return;
                }
            }
            saveEditDelete.saveAddress($http, '/CMS/address/save/:object.htm', $scope);
            if (!$scope.selectedGroupHasKey($scope.addressSelector)) {
                $scope.selected.addresses.push($scope.addressSelector);
            }
            $scope.editSubElement = false;
        }
    };

    $scope.removeElement = function (type) {
        if (type == 'address') {
            if ($scope.addressSelector !== undefined) {
                saveEditDelete.removeAddress($http, '/CMS/address/delete/:object.htm', $scope);

                $scope.addressSelector = "";
                $scope.editSubElement = false;
            }
        }
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
    
    $scope.selectAction = function(obj) {
        if(obj == 'departmentId'){
            var index;
            for (var i = 0; i < $scope.departments.length; i++) {
                if ($scope.departments[i].id == $scope.selected.departmentId) {
                    index = i;
                }
            }
            $scope.selected.department = $scope.departments[index].name;
        }
        if(obj == 'positionId'){
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
