<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="employeeLogReport.htm" method="POST">
    Id pracownika: 
    <select name="employeeId">
        <c:forEach items="${employees}" var="emp">
            <option value="${emp.id}">${emp.forename} ${emp.surname}</option>
        </c:forEach>
    </select>
    <input class="raport-button" type="submit" value="POBIERZ"/>
</form>
