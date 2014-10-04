<%-- 
    Document   : position
    Created on : 2014-08-30, 15:01:33
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body><script src="/CMS/resources/js/resourceManagment/positionListCtrl.js"></script>
        <h1>Stanowisko</h1>
        <div ng-controller="PositionListCtrl">
            <t:dataTable/>
            <div ng-show="selectedEmployees.length > 0">
                <h3>
                    Pracownicy na tym stanowisku:
                </h3>
                <select ng-model="employeeSelector" ng-options="emp.forename+' '+emp.surname for emp in selectedEmployees"></select><br>
                <table>
                    <tr>
                        <th ng-repeat="adatr in employeeAttributes">
                            {{$parent.columnDescription(adatr)}}
                        </th>   
                    </tr>
                    <tbody>
                        <tr>
                            <td ng-repeat="attr in employeeAttributes">
                                <div ng-if="attr == 'departmentId'"> 
                                    <div ng-repeat="dep in departments" ng-show="dep.id == employeeSelector[attr]">
                                        {{dep.name}}
                                    </div>
                                </div>
                                <div ng-if="attr != 'departmentId'"> {{employeeSelector[attr]}} </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div ng-show="editMode">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <t:jsonOperations/>
            </div>
    </jsp:body>
</t:general>