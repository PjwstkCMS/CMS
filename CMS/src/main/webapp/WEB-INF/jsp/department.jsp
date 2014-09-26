<%-- 
    Document   : department
    Created on : 2014-08-30, 14:59:14
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/departmentListCtrl.js"></script>
        <h1>Department!</h1>
        <h3>${server}</h3>
        <div ng-controller="DepartmentListCtrl">
            <t:dataTable/>
            <div id="companyTable" ng-show="selected">
                <b>Adres:</b> {{selected.address.city}} {{selected.address.streetName}}
                              {{selected.address.streetNumber}}/m.{{selected.address.apartmentNumber}}
                              {{selected.address.postalCode}} {{selected.address.country}}

            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>