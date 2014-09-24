<%-- 
    Document   : customer
    Created on : 2014-08-30, 14:58:55
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/customerListCtrl.js"></script>
        <h1>Customer!</h1>
        <h3>${server}</h3>
        <div ng-controller="CustomerListCtrl">            
            <t:dataTable/>
            <t:jsonOperations/>
            <div ng-show="editMode || selected">
                <table>
                    <tr>
                        <td>
                            Imię: <input type="text" ng-model="selected.name"/>
                        </td>
                        <td>
                            Nazwisko: <input type="text" ng-model="selected.surname"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        </jsp:body>
    </t:general>
