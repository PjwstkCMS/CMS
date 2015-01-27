<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <style>
            .selectedObject {
                background-color: lightblue;
            }
            table {
                width: 100%;
            }
        </style>
        <script src="/CMS/resources/js/resourceManagment/archiveListCtrl.js"></script>
      
        <div ng-controller="ArchiveListCtrl">                        
            <table width="100%" border="1">
                <tr>
                    <th ng-click="loadData('/CMS/archive/employees.htm')">
                        Employees
                    </th>
                    <th ng-click="loadData('/CMS/archive/contracts.htm')">
                        Contracts
                    </th>
                    <th ng-click="loadData('/CMS/archive/companies.htm')">
                        Companies
                    </th>
                    <th ng-click="loadData('/CMS/archive/customers.htm')">
                        Customers
                    </th>
                </tr>
                <tr>
                    <td colspan="4">
                        <p ng-class="{selectedObject: emp == selected}" ng-click="select(emp)" 
                           ng-show="show == '/CMS/archive/employees.htm'" ng-repeat="emp in employees">
                            {{emp.forename}}                            
                        </p>
                        <p ng-class="{selectedObject: com == selected}" ng-click="select(com)" 
                           ng-show="show == '/CMS/archive/contracts.htm'" ng-repeat="com in employees">
                            {{com.name}}                            
                        </p>
                        <p ng-class="{selectedObject: con == selected}" ng-click="select(con)" 
                           ng-show="show == '/CMS/archive/companies.htm'" ng-repeat="con in employees">
                            {{con.name}}                            
                        </p>
                        <p ng-class="{selectedObject: cus == selected}" ng-click="select(cus)" 
                           ng-show="show == '/CMS/archive/customers.htm'" ng-repeat="cus in employees">
                            {{cus.description}}                            
                        </p>
                    </td>
                </tr>
            </table>
            <input type="button" ng-click="deleteCon()"
                   ng-show="show == '/CMS/archive/contracts.htm'" value="USUŃ"/>
            <input type="button" ng-click="deleteEmp()"
                   ng-show="show == '/CMS/archive/employees.htm'" value="USUŃ"/>
            <input type="button" ng-click="deleteCom()"
                   ng-show="show == '/CMS/archive/companies.htm'" value="USUŃ"/>
            <input type="button" ng-click="deleteCus()"
                   ng-show="show == '/CMS/archive/customers.htm'" value="USUŃ"/>
            <input type="button" ng-click="dearchiveCon()"
                   ng-show="show == '/CMS/archive/contracts.htm'" value="DEARCHIWIZUJ"/>
            <input type="button" ng-click="dearchiveEmp()"
                   ng-show="show == '/CMS/archive/employees.htm'" value="DEARCHIWIZUJ"/>
            <input type="button" ng-click="dearchiveCom()"
                   ng-show="show == '/CMS/archive/companies.htm'" value="DEARCHIWIZUJ"/>
            <input type="button" ng-click="dearchiveCus()"
                   ng-show="show == '/CMS/archive/customers.htm'" value="DEARCHIWIZUJ"/>
        </div>
    </jsp:body>
</t:general>