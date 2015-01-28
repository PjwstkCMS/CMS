<%-- 
    Document   : login
    Created on : 2014-08-30, 14:42:49
    Author     : Macha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <head>
        <script src="/CMS/resources/js/jquery-2.1.3.min.js"></script> 
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script> 
       <link href="/CMS/resources/css/screen.css" media="screen, projection" rel="stylesheet" type="text/css" />
          
        
    </head>
    <body ng-app>
        ${error}
        <c:if test="${user==null}">
            <div class="container">

            <c:if test="${mailSent == true}">
                <div class="request-sent">
                    <span style="float:left;color:#d4d4d4;margin-left:155px;margin-top: 10px;">Wysłano prośbę<span> </div>
                </c:if>
            <c:if test="${error == false}">
                <div class="request-sent-error"><span style="float:left;color:#d4d4d4;margin-left:130px;margin-top: 10px;">${error}<span></div></c:if>


            <div class="top-container">
                <div class="logo"><img src="/CMS/resources/images/hr-logo.png" width="113" height="40"  alt=""/></div>

                <div id="tekst-bg" ng-hide="newAccount">
                    <div class="button-tekst" >
                        nie masz konta?<br>
                        <span style="font-weight:700;" ng-click="newAccount = !newAccount"><a href="">wyślij prośbę</a></span></div>
                </div>
                <div id="tekst-bg-after" ng-show="newAccount">
                    <div class="button-tekst">
                        po pozytywnej weryfikacji<br>
                        <span style="font-weight:700;">otrzymasz dane do konta</span></div>
                </div>
                <div id="strzalka-button" ng-click="newAccount = !newAccount" ng-hide="newAccount"><div class="strzalka"><img src="/CMS/resources/images/strzalka.png" width="11" height="18"  alt=""/></div></div>





            </div> <!-- end of top-container -->
            <div class="form-container" ng-hide="newAccount">
                <form class="formularz" action="login.htm" method="POST">
                    <div class="stay-logged">
                        <input type="checkbox" id="stay-logged" ng-init="stayLogged = 'false'" ng-model="stayLogged" ng-true-value="true" ng-false-value="false" name="" />
                        <label for="stay-logged"><span></span>zapamiętaj mnie</label>
                        <input type="text" ng-hide="true" name="stayLogged" ng-model="stayLogged"/>
                    </div>
                    <div class="user-icon"><img src="/CMS/resources/images/user-icon.png" width="18" height="18"  alt=""/></div>


                    <input type="text" class="user-name" name="login" placeholder="Nazwa użytkownika" maxlength="32" autocomplete="off" autofocus/>


                    <div class="password-icon"><img src="/CMS/resources/images/password-icon.png" width="18" height="18"  alt=""/></div>


                    <input type="password" class="password-input" name="password" placeholder="Hasło" maxlength="32"/>


                    <input type="submit" class="zaloguj-button" name="loginButton" value="ZALOGUJ"/>

                    
                    
                </form>
                
            </div> <!-- end of form-container -->

        </div> <!-- end of container -->      

        <div class="container-after" ng-show="newAccount">
            <div class="top-container">
                <div class="logo"><img src="/CMS/resources/images/hr-logo.png" width="113" height="40"  alt=""/></div>
                <div id="tekst-bg-after">
                    <div class="button-tekst">
                        po pozytywnej weryfikacji<br>
                        <span style="font-weight:700;">otrzymasz dane do konta</span></div>
                </div>

            </div> <!-- end of top-container -->

            <div class="form-container-after">
                <form class="formularz" action="createAccount.htm" method="POST">
                    <c:if test="${mailSent != true}">

                        <div class="user-icon"><img src="/CMS/resources/images/user-icon.png" width="18" height="18"  alt=""/></div>
                        <input type="text" class="imie-input" name="forename" placeholder="Imię..." maxlength="32" autocomplete="off" autofocus/>

                        <div class="surname-icon"><img src="/CMS/resources/images/user-icon.png" width="18" height="18"  alt=""/></div>
                        <input type="text" class="nazwisko-input" name="surname" placeholder="Nazwisko..." maxlength="32" autocomplete="off"/>

                        <div class="email-icon"><img src="/CMS/resources/images/email-icon.png" width="18" height="18"  alt=""/></div>
                        <input type="email" class="email-input" name="email" placeholder="Adres e-mail..." maxlength="32" autocomplete="off"/>

                        <div class="login-icon"><img src="/CMS/resources/images/user-icon.png" width="18" height="18"  alt=""/></div>
                        <input type="text" class="login-input" name="login" placeholder="Nazwa użytkownika..." maxlength="32" autocomplete="off"/>

                        <div class="pass-icon"><img src="/CMS/resources/images/password-icon.png" width="18" height="18"  alt=""/></div>
                        <input type="password" class="password-input" name="password" placeholder="Hasło..." maxlength="32" autocomplete="off"/>

                        <input type="submit" class="wyslij-button" value="WYŚLIJ">
                        <input type="button" class="anuluj-button" ng-click="newAccount = false" value="ANULUJ">

                    </c:if>

                    <c:if test="${mailSent == true}">
                        Wysłano już prośbę o konto.
                    </c:if>
                </form>

            </div> <!-- end of form-container-after -->
            
            <!--<div class="login">
                Logowanie:<br/>
                <form method="post" action="login.htm">
                    Login: <input type="text" name="login"/>
                    Hasło: <input type="password" name="password"/>
                    <input type="submit"/>
                </form>
            </div>
            <h3>${sendStatus}</h3>
            <c:if test="${sendStatus == null || sendStatus == 'Niewysłane'}">
                <div class="createAccount">                    
                    Tworzenie konta:<br/>
                    <form method="post" action="createAccount.htm">
                     
                        Imię: <input type="text" name="forename"/>
                        Nazwisko: <input type="text" name="surname"/>
                        Login: <input type="text" name="login"/>
                        Hasło: <input type="password" name="password"/>
                        <input type="submit"/>
                    </form>
                </div>
            </c:if>-->
        </c:if>
        <c:if test="${user!=null}">
            Jesteś zalogowany!
        </c:if>
    </body>
</html>
