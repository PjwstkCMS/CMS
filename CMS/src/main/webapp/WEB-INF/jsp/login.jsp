<%-- 
    Document   : login
    Created on : 2014-08-30, 14:42:49
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>
    <jsp:body>
        ${user}
        ${user.forename}
        <c:if test="${user==null}">
        <form method="post" action="login.htm">
            <input type="text" name="login"/>
            <input type="password" name="password"/>
            <input type="submit"/>
        </form>
        </c:if>
        <c:if test="${user!=null}">
        <form method="post" action="logout.htm">
            <input type="submit" value="logout"/>
        </form>
        </c:if>
    </jsp:body>
</t:general>
