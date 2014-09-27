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
            <div ng-show="selected">
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
                            <select>
                                <option ng-repeat="add in selectedAddress">ID: {{add.id}}</option>
                            </select>
                        </td>
                        <td>
                            <select>
                                <option ng-repeat="empl in selectedEmployments">ID: {{empl.id}}</option>
                            </select> 
                        </td>
                        <td>
                            <select>
                                <option ng-repeat="con in selectedContracts">ID: {{con.id}}</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>