<%-- 
    Document   : employee
    Created on : 2014-08-30, 14:59:47
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/employeeListCtrl.js"></script>
        <h1>Employee!</h1>        
        <div ng-controller="EmployeeListCtrl">
            {{someVar}}
        </div>
    </jsp:body>
</t:general>