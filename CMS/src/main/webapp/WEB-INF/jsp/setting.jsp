<%-- 
    Document   : setting
    Created on : 2014-08-30, 15:02:08
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/systemConfigCtrl.js"></script>
        <h1>setting!</h1>
        <div ng-controller="SystemConfigCtrl">
            <t:dataTable/>
        </div>
    </jsp:body>
</t:general>