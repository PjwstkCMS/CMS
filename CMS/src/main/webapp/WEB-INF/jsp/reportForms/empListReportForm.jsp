<%-- 
    Document   : empListReportForm
    Created on : 2014-01-08, 10:30:04
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="empListReportTemplate.htm">
    Wydział:
    <select name="deptNumber">
        <option ng-repeat="dept in departments" value="{{dept.id}}">
            {{dept.name}}
        </option>
    </select>
    <input class="raport-button" type="submit" value="POBIERZ"/>
</form>
