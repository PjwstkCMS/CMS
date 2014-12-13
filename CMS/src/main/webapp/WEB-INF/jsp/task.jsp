<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/taskListCtrl.js"></script>
        <h1>Task!</h1>
        <div ng-controller="TaskListCtrl">
            <t:dataTable/>
            <div ng-show="editMode && newRecord">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>