<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/terminalListCtrl.js"></script>
        <div ng-controller="TerminalListCtrl">
            <t:dataTable/>
            <div ng-show="editMode && newRecord">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <t:jsonOperations/>
            <table ng-show="selected">
                <tr>
                    <th>
                        #
                    </th>
                    <th>
                        Imię
                    </th>
                    <th>
                        Nazwisko
                    </th>
                    <th>
                        Timestamp
                    </th>
                </tr>
                <tr style="text-align: center" ng-repeat="log in logs | filter:selected.id">
                    <td>
                        {{$index+1}}
                    </td>
                    <td>
                        {{log.empName}}
                    </td>
                    <td>
                        {{log.empSurname}}
                    </td>
                    <td>
                        {{log.date}}
                    </td>
                </tr>
            </table>
        </div>
    </jsp:body>
</t:general>