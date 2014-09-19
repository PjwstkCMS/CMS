<%-- 
    Document   : user
    Created on : 2014-08-30, 15:02:36
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <script src="/CMS/resources/js/configuration/userListCtrl.js"></script>
        <h1>user!</h1>
        <div ng-controller="UserListCtrl">
            <t:dataTable/>
        </div>
</t:general>