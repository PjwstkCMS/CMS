<%-- 
    Document   : fileManager
    Created on : 2014-08-30, 15:00:46
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/manageFileCtrl.js"></script>
        <h1>manageFile!</h1>        
        <div ng-controller="ManageFileCtrl">
            <t:dataTable/>
        </div>
    </jsp:body>
</t:general>