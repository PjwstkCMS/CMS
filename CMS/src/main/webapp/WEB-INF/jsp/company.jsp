<%-- 
    Document   : contract
    Created on : 2014-08-30, 14:58:34
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/companyListCtrl.js"></script>
        <h1>Company!</h1>        
        <div ng-controller="CompanyListCtrl">
            <t:dataTable/>
        </div>
    </jsp:body>
</t:general>