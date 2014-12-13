<%-- 
    Document   : gen
    Created on : 2014-08-30, 14:40:07
    Author     : Macha
--%>
<%@tag import="pl.edu.pjwstk.cms.utils.Utils"%>
<%@tag import="pl.edu.pjwstk.cms.dto.UserDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<html >
    <head>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.min.js"></script> 
        <script src="/CMS/resources/js/services.js"></script>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HR System</title>        
    </head>
    <body ng-app="cms">
        <style>
            .content-table {
                width: 100%;
                height: 100%;
            }
            .jspBody {
                width: 100%;
                height: 100%;
            }
            .menu {
                width: 15%;
                vertical-align: top;
            }
        </style>
        <table class="content-table">
            <tr>
                <td class="menu">
                    <h3>${server}</h3>
                    <c:if test="${user!=null}">                        
                        <img src="getUserImage.htm" alt="Brak obrazka" />
                        <form method="post" action="logout.htm">
                            <input type="submit" value="logout"/>
                        </form>
                        <iframe src="getUserMessages.htm" width=450 height=100>
                        </iframe>
                    </c:if>
                    <h1>
                        ${user.login}   
                        ${user.privilegeKeyCodes}
                    </h1>
                    <ul>
                        <li id="nav14">Zarządzanie zasobami</li>
                        <a href="/CMS/home.htm"><li id="nav0">home</li></a>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewContracts')}">
                            <a href="/CMS/contract.htm"><li id="nav0">Contract</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewCompanies')}">
                            <a href="/CMS/company.htm"><li id="nav0">Company</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewCustomers')}">
                            <a href="/CMS/customer.htm"><li id="nav0">Customer</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewDepartments')}">
                            <a href="/CMS/department.htm"><li id="nav0">Department</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewEmployees')}">
                            <a href="/CMS/employee.htm"><li id="nav0">employee</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewEmployments')}">
                            <a href="/CMS/employment.htm"><li id="nav0">Employment</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewTasks')}">
                            <a href="/CMS/task.htm"><li id="nav0">Task</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('DownloadFiles')}">
                            <a href="/CMS/fileList.htm"><li id="nav0">fileList</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewPositions')}">
                            <a href="/CMS/position.htm"><li id="nav0">position</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('PrintReports')}">
                            <a href="/CMS/report.htm"><li id="nav0">report</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewTerminals')}">
                            <a href="/CMS/terminal.htm"><li id="nav0">terminal</li></a>
                                </c:if>
                        <br/>
                        <li id="nav14">Konfiguracja</li>
                            <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                          user.privilegeKeyCodes.contains('ViewDictionaries')}">
                            <a href="/CMS/dictionaryList.htm"><li id="nav0">dictionaryList</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewUsers')}">
                            <a href="/CMS/userList.htm"><li id="nav0">userList</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewSettings')}">
                            <a href="/CMS/setting.htm"><li id="nav0">setting</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewManageFiles')}">
                            <a href="/CMS/manageFile.htm"><li id="nav0">fileManage</li></a>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || 
                                              user.privilegeKeyCodes.contains('ViewGroups')}">
                            <a href="/CMS/groupList.htm"><li id="nav0">groupList</li></a>
                                </c:if>
                    </ul>
                </td>
                <td>

                    <div class="jspBody">
                        <jsp:doBody/>
                    </div>
                </td>
            </tr>
        </table>

    </body>
</html>
