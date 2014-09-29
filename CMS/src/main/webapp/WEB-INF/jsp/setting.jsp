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
            <div ng-show="editMode || selected">
                <table>
                    <tr>
                        <td>
                            Nazwa: <input type="text" ng-model="selected.name"/>
                        </td>
                        <td>
                            Wartość: <input type="text" ng-model="selected.value"/>
                        </td>
                        <td>
                            Opis: <input type="text" ng-model="selected.description"/>
                        </td>
                    </tr>
                </table>
            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>