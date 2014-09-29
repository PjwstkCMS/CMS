<%-- 
    Document   : contractReportForm
    Created on : 2014-01-07, 17:48:50
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="contractReportTemplate.htm">
    Numer umowy:
    <select name="contractNumber">
        <option ng-repeat="cont in contract" value="{{cont.id}}">
            {{cont.id}}
        </option>
    </select>
    <input class="raport-button" type="submit" value="POBIERZ"/>
</form>
