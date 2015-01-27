var cmsModule = angular.module('cms', ['ui.bootstrap']);

cmsModule.factory('saveEditDelete', function () {
    return {
        save: function ($http, link, $scope) {
            if ($scope.selected.id == null || $scope.selected.id < 1) {
                $scope[$scope.objectsName].push($scope.selected);
            }

            return $http.post(
                    link,
                    {object: $scope.selected}).success(function (returnId) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania udana"

                if (returnId != null) {
                    $scope.selected.id = returnId.id;
                    if (returnId.contactPersonId != null) {
                        $scope.selected.contactPersonId = returnId.contactPersonId;
                    }
                    if (returnId.addressId != null) {
                        $scope.selected.address.id = returnId.addressId;
                        $scope.selected.addressId = returnId.addressId;
                    }
                }
                $scope.selected = null;
                $scope.editMode = false;
            }).error(function (error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania nie udana"
                alert(error);
            });
        },
        get: function ($http, link, $scope) {
            return $http.get(link).success(function (returnData) {
                $scope.status = null;
                $scope.initData = returnData;
                $scope[$scope.objectsName] = returnData[$scope.objectsName];
                $scope.objects = returnData[$scope.objectsName];
                //alert(returnData.privilegeKeys[0].id);
                return "Success";
            }).error(function (error) {
                $scope.status = "Błąd";
                return null;
            });
        },
        remove: function ($http, link, $scope) {
            return $http.post(
                    link,
                    {object: $scope.selected}).success(function () {
                var index;
                for (var i = 0; i < $scope[$scope.objectsName].length; i++) {
                    if ($scope[$scope.objectsName][i].id == $scope.selected.id) {
                        index = i;
                    }
                }
                $scope[$scope.objectsName].splice(index, 1);
                $scope.selected = "";
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja usuwania udana"
            }).error(function (error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja zapisywania nie udana"
            });
        },
        archive: function ($http, link, $scope) {
            return $http.post(
                    link,
                    {object: $scope.selected}).success(function () {
                var index;
                for (var i = 0; i < $scope[$scope.objectsName].length; i++) {
                    if ($scope[$scope.objectsName][i].id == $scope.selected.id) {
                        index = i;
                    }
                }
                $scope[$scope.objectsName].splice(index, 1);
                $scope.selected = "";
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja archiwizacji udana"
            }).error(function (error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Operacja archiwizacji nie udana"
            });
        },
        saveElement: function ($http, link, $scope, type) {
            return $http.post(
                    link,
                    {object: $scope.selector[type]}).success(function (returnId) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Dane zapisane";

                if (returnId != null) {
                    $scope.selector[type].id = returnId.id;
                }
                $scope.selector[type] = null;
                $scope.editMode = false;
            }).error(function (error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Błąd dodawania";
                alert(error);
            });
        },
        removeElement: function ($http, link, $scope, type) {
            alert($scope.selector[type]);
            return $http.post(
                    link,
                    {object: $scope.selector[type]}).success(function (returnId) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Dane usunięte";

                if (returnId != null) {
                    $scope.selector[type].id = returnId.id;
                }
                $scope.selector[type] = null;
                $scope.editMode = false;
            }).error(function (error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Błąd operacji usuwania";
                alert(error);
            });
        },
        saveKey: function ($http, link, $scope, $selector, $container) {
            if ($selector.id == null || $selector.id < 1) {
                $container.push($selector);
            }

            return $http.post(
                    link,
                    {object: $selector}).success(function (returnId) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Adres zapisany"

                if (returnId != null) {
                    $selector.id = returnId.id;
                }
                $selector = null;
                $scope.editMode = false;
            }).error(function (error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Błąd przy dodawaniu adresu"
                alert(error);
            });
        },
        deleteKey: function ($http, link, $scope, $selector, $container) {
            return $http.post(
                    link,
                    {object: $selector}).success(function () {
                var index = -1;
                for (var i = 0; i < $container.length; i++) {
                    if ($container[i].id == $selector.id) {
                        index = i;
                    }
                }
                if (index !== -1) {
                    $container.splice(index, 1);
                }
                $selector = "";
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Adres usunięty"
            }).error(function (error) {
                $scope.showOperationMessage = true;
                $scope.operationMessage = "Błąd przy usuwaniu adresu"
            });
        },
        saveOldData: function ($scope, object) {
            var copy = object.constructor();
            for (var attr in object) {
                if (object.hasOwnProperty(attr))
                    copy[attr] = object[attr];
            }
            $scope.restoreData = copy;
            /*
             for (i = 0; i < $scope.attributes.length; i++) {                
             $scope.restoreData[$scope.attributes[i]] = "";
             $scope.restoreData[$scope.attributes[i]] = object[$scope.attributes[i]];
             //alert($scope.selected[$scope.attributes[i]]);
             }
             */
            //alert($scope.restoreData);
        },
        restoreOldData: function ($scope) {
            for (var attr in $scope.restoreData) {
                $scope.selected[attr] = $scope.restoreData[attr];
            }
        }
    };
});

cmsModule.factory('pagination', function () {
    return {
        indexOnPage: function ($scope) {
            return function (index)
            {
                if (index <= $scope.pageMax && index >= $scope.pageMin) {
                    return true;
                } else {
                    return false;
                }
            };
        },
        pageMaxSmallerThenSize: function ($scope) {
            return function () {
                //alert($scope[$scope.objectsName].length);
                if ($scope[$scope.objectsName].length <= 10) {
                    return false;
                }
                if ($scope.pageMax > $scope[$scope.objectsName].length) {
                    return false;
                } else {
                    return true;
                }
            };
        }
    };
});

cmsModule.factory('columnDesc', function () {
    return {
        get: function (object) {
            descriptions = {
                'id': "ID",
                'employeeId': "Pracownik",
                'customerId': "Klient",
                'managerId': "Manager",
                'companyId': "Firma",
                'departmentId': "Departement",
                'cardNumber': "Numer karty",
                'dictTypeId': "Rodzaj słownika",
                'dictId': "Dodatkowy Opis",
                'positionId': "Stanowisko",
                'groupId': "Grupa",
                
                'employee': "Pracownik",
                'customer': "Klient",
                'manager': "Manager",
                'department': "Departement",
                'position': "Stanowisko",
                'dict': "Dodatkowy Opis",
                'group': "Grupa",
                
                'forename': "Imie",
                'surname': "Nazwisko",
                'phone': "Telefon",
                'email': "Email",
                'pesel': "Pesel",
                'login': "Login",
                'employeIdNum': "Id pracownika",
                'password': "Hasło",
                'salary': "Pensja",
                'name': "Nazwa",
                'description': "Opis",
                'price': "Cena",
                'mimeType': "Rodzaj pliku",
                'value': "Wartość",
                'code': "Kod",
                'startDate': "Data rozpoczęcia",
                'closeDate': "Planowana data zakończenia",
                'finalisationDate': "Data zakończenia",
                'dateFrom': "Od",
                'dateTo': "Do",
                'country': "Kraj",
                'city': "Miasto",
                'streetName': "Ulica",
                'streetNumber': "Numer",
                'apartmentNumber': "Lokal",
                'postalCode': "Kod",
                'mac': "MAC Adres"

            };

            for (desc in descriptions) {
                if (desc == object) {
                    return descriptions[desc];
                }
            }
            return "N/A";
        }
    };
});
