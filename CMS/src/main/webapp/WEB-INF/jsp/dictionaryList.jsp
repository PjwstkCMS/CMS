<%-- 
    Document   : dictionary
    Created on : 2014-08-30, 14:59:30
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/configuration/dictionaryListCtrl.js"></script>
        <h1>Dictionary!</h1>        
        <div ng-controller="DictionaryListCtrl">
            <t:dataTable/>
        </div>
    </jsp:body>
</t:general>