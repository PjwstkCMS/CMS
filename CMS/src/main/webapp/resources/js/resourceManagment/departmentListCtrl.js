function DepartmentListCtrl($scope, $http, saveEditDelete, pagination, columnDesc) {
    $scope.page = "Department";
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.departments = "";
    $scope.privileges = "";
    $scope.editMode = false;
    $scope.newRecord = false;
    
    $scope.objectsName = "departments";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'managerId';
    
    $scope.editValues = [];
    $scope.editValues[0] = {0:'id', 1:false};
    $scope.editValues[1] = {0:'name', 1:true};
    $scope.editValues[2] = {0:'managerId', 1:true};
    
    $scope.addressValues = [];
    $scope.addressValues[0] = {0:'id', 1:false};
    $scope.addressValues[1] = {0:'country',1:true};
    $scope.addressValues[2] = {0:'city',1:true};
    $scope.addressValues[3] = {0:'streetName',1:true};
    $scope.addressValues[4] = {0:'streetNumber',1:true};
    $scope.addressValues[5] = {0:'apartmentNumber',1:true};
    $scope.addressValues[6] = {0:'postalCode',1:true};
    $scope.addressValues[7] = {0:'dictId',1:true};
        
    $scope.get = saveEditDelete.get($http, '/CMS/department/departments.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        for (var i = 0; i<$scope.editValues.length; i++) {  
            if($scope.editValues[i][1] && 
               ($scope.selected[$scope.editValues[i][0]] == null || $scope.selected[$scope.editValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        for (var i = 0; i<$scope.addressValues.length; i++) {  
            if($scope.addressValues[i][1] && 
               ($scope.selected.address[$scope.addressValues[i][0]] == null || $scope.selected.address[$scope.addressValues[i][0]] == "")){
                alert("Sprawdź poprowność wprowadzonych danych");
                return;
            }
        }
        saveEditDelete.save($http, '/CMS/department/save/:object.htm', $scope);
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.departments = $scope.initData.departments;
            $scope.employees = $scope.initData.employees;
            $scope.dictionaries = $scope.initData.dictionaries;
            $scope.privileges = $scope.initData.privileges;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            saveEditDelete.restoreOldData($scope);
            $scope.selected = "";
            $scope.newRecord = false;
        } else {
            $scope.selected = object;
            saveEditDelete.saveOldData($scope,object);
            $scope.newRecord = false;
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
        $scope.newRecord = false;
    };

    $scope.cancel = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.editMode = false;
        $scope.selected = "";
        $scope.newRecord = false;
    };

    $scope.create = function() {
        saveEditDelete.restoreOldData($scope);
        $scope.selected = {
            'id': "", 'name':"", 'managerId':"",
            'address':{ 'id':"",'country':"",'city':"",'streetName':"",'streetNumber':"",
                        'apartmentNumber':"",'postalCode':"",'dictId':"",'companyId':"-1", 'persondataId':"-1"
            },
            'privilegeKeyCodes':[]
        };
        $scope.editMode = true;
        $scope.newRecord = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/department/delete/:object.htm', $scope);
    };
    
    $scope.columnDescription = function(obj){
        return columnDesc.get(obj);
    };
}