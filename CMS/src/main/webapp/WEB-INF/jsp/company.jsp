<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/companyListCtrl.js"></script>
        
        <div ng-init="archivable=true" ng-controller="CompanyListCtrl">
            <t:dataTable/>
            <div ng-show="editMode">
                <t:editTable map="editValues" object="selected"/>
                <input type="checkbox" ng-click="showContactPersonBox()" /> Dodać osobę kontaktową <br/>
                <div name="contactPersonBox" ng-show="contactPersonBoxShow">
                    <t:editTable map="contactPersonValues" object="selected"/>
                </div>
            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>