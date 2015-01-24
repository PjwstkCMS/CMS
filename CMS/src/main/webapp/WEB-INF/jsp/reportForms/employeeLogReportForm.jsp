<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="employeeLogReport.htm" method="POST">
    Id pracownika: 
    <select required="required" name="employeeId">
        <option ng-repeat="emp in ${employees}" value="{{emp.id}}" >{{emp.forename}} {{emp.surname}}</option>
    </select>
    
    <input class="raport-button" type="submit" value="POBIERZ"/>
</form>
