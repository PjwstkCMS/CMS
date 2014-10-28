<%-- 
    Document   : login
    Created on : 2014-08-30, 14:42:49
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <body>
        <c:if test="${user==null}">
            <div class="login">
                Logowanie:<br/>
                <form method="post" action="login.htm">
                    Login: <input type="text" name="login"/>
                    Hasło: <input type="password" name="password"/>
                    <input type="submit"/>
                </form>
            </div>
            <div class="createAccount">
                Tworzenie konta:<br/>
                <form method="post" action="createAccount.htm">
                    Imię: <input type="text" name="name"/>
                    Nazwisko: <input type="text" name="surname"/>
                    Login: <input type="text" name="login"/>
                    Hasło: <input type="password" name="password"/>
                    <input type="submit"/>
                </form>
            </div>
        </c:if>
        <c:if test="${user!=null}">
            Jesteś zalogowany!
        </c:if>
        </body>
    </html>
