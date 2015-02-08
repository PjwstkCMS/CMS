<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>

        <script src="/CMS/resources/js/resourceManagment/archiveListCtrl.js"></script>

        <div ng-controller="ArchiveListCtrl">                        
            <table class="archive-table">
                <tr class="table-header">
                    <th ng-click="loadData('/CMS/archive/employees.htm')">
                        Pracownicy
                    </th>
                    <th ng-click="loadData('/CMS/archive/contracts.htm')">
                        Umowy
                    </th>
                    <th ng-click="loadData('/CMS/archive/companies.htm')">
                        Firmy
                    </th>
                    <th ng-click="loadData('/CMS/archive/customers.htm')">
                        Klienci
                    </th>
                </tr>
                <tr ng-class="{selectedObject: emp == selected}" ng-click="select(emp)" 
                    ng-if="show == '/CMS/archive/employees.htm'" ng-repeat="emp in employees">
                    <td colspan="4">
                        {{emp.forename}} {{emp.surname}} - Stanowisko: {{emp.position}} Departament: {{emp.department}}        
                    </td>
                </tr>
                <tr ng-class="{selectedObject: con == selected}" ng-click="select(con)" 
                    ng-show="show == '/CMS/archive/contracts.htm'" ng-repeat="con in contracts">
                    <td colspan="4">        
                        {{con.description}}                            
                    </td>
                </tr>
                <tr ng-class="{selectedObject: com == selected}" ng-click="select(com)" 
                    ng-show="show == '/CMS/archive/companies.htm'" ng-repeat="com in companies">
                    <td colspan="4">
                        {{com.name}}    
                    </td>
                </tr>
                <tr ng-class="{selectedObject: cus == selected}" ng-click="select(cus)" 
                    ng-show="show == '/CMS/archive/customers.htm'" ng-repeat="cus in customers">
                    <td colspan="4">
                        {{cus.forename}} {{cus.surname}}
                    </td>
                </tr>
            </table>
            <div ng-if="selected">
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
        </div>
    </jsp:body>
</t:general>