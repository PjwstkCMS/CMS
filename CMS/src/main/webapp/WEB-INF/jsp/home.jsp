<%-- 
    Document   : home
    Created on : 2014-08-30, 15:45:47
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>    
    <jsp:body>
        <h1>Hello World!</h1>        
        <form action="/CMS/uploadPhoto.htm" method="POST" enctype="multipart/form-data">
            Plik: <input type="file" name="file"/>
            <input type="submit" value="Wyslij plik"/>
        </form>
        Wyślij wiadomość:
        <form action="sendMessage.htm" method="POST">
            <select name="sendTo">
                <c:forEach items="${sendUsers}" var="sendUser">
                    <option value="${sendUser.id}">${sendUser.login}</option>
                </c:forEach>
            </select>
            <textarea name="message" rows="30" cols="100"></textarea>
            <input type="submit"/>
        </form>
    </jsp:body>
</t:general>