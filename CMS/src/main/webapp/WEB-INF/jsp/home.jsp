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
        <script src="/CMS/resources/js/homeCtrl.js"></script>
        <h1>Hello World!</h1> 
        <h3>Ustaw swojego awatara</h3>
        <form action="/CMS/uploadPhoto.htm" method="POST" enctype="multipart/form-data">
            Plik: <input type="file" name="file"/>
            <input type="submit" value="Wyslij plik"/>
        </form>
        <h3>Twoje dane</h3>
        <form action="/CMS/changeUserData.htm" method="POST">
            Imię: ${userData.forename}<br/>
            Nazwisko: ${userData.surname}<br/>
            Stanowisko ${userData.position}<br/>
            Departament ${userData.department}<br/>
            E-Mail: <input type="text" name="email" value="${userData.email}"/><br/>
            Numer telefonu: <input type="text" name="phone" value="${userData.phone}"/><br/>
            <input type="submit" value="Zmień"/>
        </form>
        <h3>Zmiana hasła</h3>
        <p style="color: red">${passwordChangeError}</p>
        <form action="/CMS/changeUserPassword.htm" method="POST">
            Stare hasło: <input type="password" name="oldPassword"/><br/>
            Nowe hasło: <input type="password" name="password1"/><br/>
            Potwierdź nowe hasło: <input type="password" name="password2"/><br/>
            <input type="submit" value="Zmień"/>
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
        <div ng-controller="HomeCtrl">
            <t:dataTable/>
            <input type="button" ng-click="done()" value="Done">
            <input type="button" ng-click="undo()" value="Undo">
        </div>                        
    </jsp:body>
</t:general>