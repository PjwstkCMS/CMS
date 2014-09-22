<%-- 
    Document   : file
    Created on : 2014-08-30, 15:00:23
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/fileListCtrl.js"></script>
        <h1>FileList!</h1>        
        <div ng-controller="FileListCtrl">
            <h1>File!</h1>
            <t:dataTable/>
            <div ng-show="selected">
                <form action="fileList/download.htm">
                    <input ng-hide="true" type="text" name="id" value="{{selected.id}}">
                    <input class="pobierz-button" type="submit" value="Pobierz {{selected.name}}"/>
                </form>
            </div>
        </div>
    </jsp:body>
</t:general>