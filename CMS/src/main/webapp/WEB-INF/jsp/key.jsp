<%-- 
    Document   : key
    Created on : 2014-08-30, 15:01:10
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/privilegeKeyListCtrl.js"></script>
        <div ng-controller="PrivilegeKeyListCtrl">
            <h1>{{bla}}!</h1>
        </div>
        <t:dataTable/>
    </jsp:body>
</t:general>