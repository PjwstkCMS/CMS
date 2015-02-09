<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:general>    
    <jsp:body>
        <c:if test="${user!=null}">
            <script src="/CMS/resources/js/homeCtrl.js"></script>
            
            <div class="task-container" ng-controller="HomeCtrl">
                <t:dataTable/>
                <input type="button" ng-show="selected.id && selected.finalisationDate==null" ng-click="done()" value="Done">
                <input type="button" ng-show="selected.id && selected.finalisationDate!=null" ng-click="undo()" value="Undo">
            </div> 
                
                
                
                <div id="test" class="dane-container col-lg-6 col-md-12">
                    
                    <div class="avatar-container">
                    <h3>Ustaw swojego awatara</h3>
                    <div class="user-image-container-big">
                                        <img src="getUserImage.htm"/>
                                    </div>
                    <div class="avatar-form-container">
                        <form action="/CMS/uploadPhoto.htm" method="POST" enctype="multipart/form-data">
                            <input type="file" name="file"/>
                        <input type="submit" value="Wyslij plik"/>
                        </form>
                    </div>
                    
                    </div>
                      
                    <div class="twoje-dane-container">
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
                
                    
                    
                       <p style="color: red">${passwordChangeError}</p>
            <form action="/CMS/changeUserPassword.htm" method="POST">
                Stare hasło: <input type="password" name="oldPassword"/><br/>
                Nowe hasło: <input type="password" name="password1"/><br/>
                Potwierdź nowe hasło: <input type="password" name="password2"/><br/>
                <input type="submit" value="Zmień"/>
            </form>
                    </div></div>
           
            <div class="message-container col-lg-6 col-md-12">
                
                <div class="message-form-container col-lg-12">
                <h3>Wyślij wiadomość:</h3>
                <form class="message-form-text" action="sendMessage.htm" method="POST">
                <select name="sendTo">
                    <c:forEach items="${sendUsers}" var="sendUser">
                        <option value="${sendUser.id}">${sendUser.login}</option>
                    </c:forEach>
                </select><br>
               
                
                    <textarea style="width:100%;height:200px;" name="message"></textarea><br>
                <input type="submit" value="Wyślij wiadomość"/>
            </form>
                </div>
                </div>
                
           
                    
            
                         
        </c:if>
    </jsp:body>
</t:general>