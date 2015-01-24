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
            Lista klientów
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
                    Firma
                </th>
                <th>
                    E-Mail
                </th>
                <th>
                    Telefon
                </th>
            </tr>
            <c:forEach var="emp" varStatus="status" items="${customers}">
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
                    ${emp.company}
                </td>
                <td>
                    ${emp.email}
                </td>
                <td>
                    ${emp.phone}
                </td>
            </tr>
            </c:forEach>
        </table>                    
    </body>
</html>
