<%-- 
    Document   : jsonOperations
    Created on : 2013-10-28, 17:19:08
    Author     : Sergio
--%>

<%@tag description="SaveEditDelete" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>


<%-- any content can be specified here e.g.: --%>
        
 
    
    <input type="button" class="zapisz-button" ng-show="editMode && checkEditPrivileges()" ng-click="save()" value="ZAPISZ">
    <input type="button" class="anuluj-button" ng-show="editMode && checkEditPrivileges()" ng-click="cancel()" value="ANULUJ">
