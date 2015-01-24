<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align: center">
            Lista logów Pracownika: ${logs[0].empName} ${logs[0].empSurname}
        </h1>
        <style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
.tg th{font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
</style>
        <table class="tg" width="100%">
            <tr>
                <th>
                    #
                </th>
                <th>
                    Data
                </th>
                <th>
                    Terminal
                </th>
            </tr>
            <c:forEach var="log" varStatus="status" items="${logs}">
                <tr>
                    <td>
                        ${status.count}
                    </td>
                    <td>
                        ${log.date}
                    </td>
                    <td>
                        ${log.terminalDesc}
                    </td>
                </tr>
            </c:forEach>
        </table>
                
    </body>
</html>
