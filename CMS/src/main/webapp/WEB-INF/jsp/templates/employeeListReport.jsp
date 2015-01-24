<%-- 
    Document   : employeeListReport
    Created on : 2015-01-24, 13:53:10
    Author     : sergio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align: center">
            Lista pracowników
        </h1>
        <table width="100%">
            <tr>
                <th>
                    #
                </th>
                <th>
                    Nazwisko
                </th>
                <th>
                    Imię
                </th>
                <th>
                    Stanowisko
                </th>
                <th>
                    Departament
                </th>
            </tr>
            <c:forEach var="emp" varStatus="status" items="${employees}">
            <tr>
                <td>
                    ${status.count}
                </td>
                <td>
                    ${emp.surname}
                </td>
                <td>
                    ${emp.forename}
                </td>
                <td>
                    ${emp.position}
                </td>
                <td>
                    ${emp.department}
                </td>
            </tr>
            </c:forEach>
        </table>                    
    </body>
</html>
