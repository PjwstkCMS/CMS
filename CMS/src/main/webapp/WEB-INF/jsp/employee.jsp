<%-- 
    Document   : employee
    Created on : 2014-08-30, 14:59:47
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/employeeListCtrl.js"></script>
        <h1>Employee!</h1>        
        <div ng-controller="EmployeeListCtrl">            
            <t:dataTable/>            
            <div ng-show="selected && !newRecord">
                <table>
                    <tr>
                        <th>
                            Adresy
                        </th>
                        <th>
                            Zatrudnienia
                        </th>
                        <th>
                            Kontrakty
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <select ng-model="addressSelector" ng-options="add.streetName+' '+add.streetNumber+' '+add.city
                                for add in selected.addresses"></select><br>
                        </td>
                        <td>
                            <select ng-model="employmentSelector" ng-options="'ID: '+empl.id
                                for empl in selectedEmployments"></select><br>
                        </td>
                        <td>
                            <select ng-model="contractSelector" ng-options="'ID: '+con.id
                                for con in selectedContracts"></select><br>
                        </td>
                    </tr>
                </table>
                <input type="button" ng-click="addAddress()" value="ADD ADDRESS"/>
                <input type="button" ng-show="addressSelector" ng-click="editAddress()" value="EDIT ADDRESS"/>
                <input type="button" ng-click="removeKey()" value="DELETE ADDRESS"/>
                <div ng-show="addressSelector" >
                    <table>
                    <tr>
                        <th ng-repeat="adatr in addressAttributes">
                            {{$parent.columnDescription(adatr)}}
                        </th>   
                    </tr>
                    <tbody>
                        <tr>
                            <td ng-repeat="attr in addressAttributes">
                                <div ng-if="attr == 'dictId'"> 
                                    <div ng-repeat="dict in dictionaries" ng-show="dict.id == addressSelector[attr]">
                                        {{dict.description}}
                                    </div>
                                </div>
                                <div ng-if="attr != 'dictId'"> {{addressSelector[attr]}} </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div ng-show="addressEdit">
                    <t:editTable map="addressValues" object="addressSelector"/>
                    <input type="button" ng-click="addKey()" value="Add"/>
                    <input type="button" ng-click="cancelAddress()" value="Cancel"/>
                </div>    
                </div>
            </div>
            <div ng-show="editMode">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>