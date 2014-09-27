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
                Pracownicy na ten stanowisku:<br/>
                <select>
                    <option ng-repeat="emp in selectedEmployees">{{emp.name}} {{emp.surname}}</option>
                </select>
            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>