<%-- 
    Document   : contract
    Created on : 2014-08-30, 14:58:34
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        <script src="/CMS/resources/js/resourceManagment/companyListCtrl.js"></script>
        <h1>Company!</h1>
        <h3>${server}</h3>
        <div ng-controller="CompanyListCtrl">
            <t:dataTable/>
            <div ng-show="selected && !newRecord">
                <h3>
                    Adresy Firmy:
                </h3>
                <select ng-model="addressSelector" ng-options="add.streetName+' '+add.streetNumber+' '+add.city
                        for add in selected.addresses"></select><br>
                <table>
                    <tr>
                        <th ng-repeat="adatr in addressAttributes">
                            {{$parent.columnDescription(adatr)}}
                        </th>   
                    </tr>
                    <input type="button" ng-click="addAddress()" value="ADD"/>
                    <input type="button" ng-show="addressSelector" ng-click="editAddress()" value="EDIT"/>
                    <input type="button" ng-click="removeKey()" value="DELETE"/>
                    <tbody>
                        <tr>
                            <td ng-repeat="attr in addressAttributes">
                                <div ng-if="attr == 'dictId'"> 
                                    <div ng-repeat="dict in dictionaries" ng-show="dict.id == addressSelector[attr]">
                                        {{dict.description}}
                                    </div>
                                </div>
                                <div ng-if="attr != 'dictId'"> {{addressSelector[attr]}} </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div ng-show="addressEdit">
                    <t:editTable map="addressValues" object="addressSelector"/>
                    <input type="button" ng-click="addKey()" value="Add"/>
                    <input type="button" ng-click="cancelAddress()" value="Cancel"/>
                </div>
            </div>
            <div ng-show="editMode">
                <t:editTable map="editValues" object="selected"/>
            </div>
            <t:jsonOperations/>
        </div>
    </jsp:body>
</t:general>