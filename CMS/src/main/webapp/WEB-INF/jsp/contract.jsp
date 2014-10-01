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
        <script src="/CMS/resources/js/resourceManagment/contractListCtrl.js"></script>
        <h1>Contract!</h1>
        <h3>${server}</h3>
        <div ng-controller="ContractListCtrl">
            <t:dataTable/>
            <t:editTable/>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>