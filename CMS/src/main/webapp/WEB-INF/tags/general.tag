<%@tag import="pl.edu.pjwstk.cms.utils.Utils"%>
<%@tag import="pl.edu.pjwstk.cms.dto.UserDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <script src="/CMS/resources/js/jquery-2.1.3.min.js"></script>      
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular.min.js"></script>
        <script src="/CMS/resources/js/services.js"></script>  
        <script src="/CMS/resources/js/resourceManagment/chatListCtrl.js"></script>
        <script src="/CMS/resources/js/bootstrap.js"></script>
        <script src="/CMS/resources/js/idleTimeout.js" type="text/javascript"></script>
        <script src="/CMS/resources/js/idleTimer.js" type="text/javascript"></script>
        <script src="/CMS/resources/js/jquery.blockUI.js"></script>
        
        <link href="/CMS/resources/css/jquery.rcrumbs.css" rel="stylesheet">
        <link href="/CMS/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="/CMS/resources/css/jquery-ui.min.css" rel="stylesheet">
        <link href="/CMS/resources/css/home.css" rel="stylesheet">
        <link href="/CMS/resources/css/sidebar.css" rel="stylesheet">
        <link href="/CMS/resources/css/fonts.css" rel="stylesheet">
        <link href="/CMS/resources/css/jquery.mCustomScrollbar.min.css" rel="stylesheet">
        
         <script type="text/javascript">
                $.idleTimeout('#idletimeout', '#idletimeout a', {
                    idleAfter: ${idleTimeout},
                    pollingInterval: 2,
                    serverResponseEquals: 'OK',
                    onTimeout: function () {
                        $(this).fadeOut();
                        window.location = "/CMS/logout.htm";
                    },
                    onIdle: function () {
                        $(this).fadeIn(); // show the warning bar
                        $.blockUI({message: null});

                    },
                    onCountdown: function (counter) {
                        $(this).find("#odliczanie").html(counter); // update the counter
                    },
                    onResume: function () {
                        $(this).fadeOut(); // hide the warning bar
                        $.unblockUI();
                    }
                });
            </script>
        
        

        <title>HR System</title>        
    </head>

    <c:if test="${user!=null}">
        <body ng-app="cms">
            
                 <div id="idletimeout" style="display: none;">
                <div class="idletimeout-top">
                    <div class="idletimeout-sign"><img src="" alt="aaa"/></div>
                    <span style="font-weight:700;" class="idletimeout-header">Twoja sesja wkrótce wygaśnie...</span></div>

                <div class="idletimeout-tekst">Zostaniesz wylogowany za <span style="font-weight:700;" id="odliczanie">
                       </span> sekund z powodu braku aktywności.
                    <br>Aby kontynuować pracę <a id="idletimeout-resume" href="#">kliknij tutaj</a> i zapomnij o sprawie... na jakiś czas... :)</div>



            </div>
            
            <div class="wrapper">

    <div class="box">

        <div class="row row-offcanvas row-offcanvas-left">


            <!-- sidebar -->
            <div class="column col-sm-2 col-xs-2 sidebar-offcanvas" id="sidebar">

                <c:if test="${user!=null}">
                    <div class="user-info">

                        <div class="user-image-container">
                            <img src="getUserImage.htm"/>
                        </div>
                        
                            <a href="#"><p>${user.login}
                            <br>
                            <span class="user-position">${userData.position}</span></p></a>
                </c:if>
                            
                       



                    </div> <!-- end of user-info -->


                <!-- big only nav-->
                <ul class="nav main-navigation hidden-xs cf" id="lg-menu">
                    <li><a class="main-category-link" href="#" id="toggle-zasoby"><span class="main-ico-align icon-zarzadzanie-zasobami-ico"></span>Zasoby<i class="icon-arrow-down-ico strzalka-position"></i> </a>
                        <ul id="zarzadzanie-zasobami">
                            
                                <li><a class="sub-category-link" href="/CMS/home.htm">
                                        <span class="sub-ico-align icon-home-ico"></span>Home</a>
                                </li>
                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewTerminals')}">
                                <li><a class="sub-category-link" href="/CMS/archive.htm">
                                        <span class="sub-ico-align icon-archiwum-ico"></span>Archiwum</a>
                                </li>
                            </c:if>
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewCompanies')}">
                                <li><a class="sub-category-link" href="/CMS/company.htm">
                                        <span class="sub-ico-align icon-firmy-ico"></span>Firmy</a>
                                </li>
                            </c:if>
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewCustomers')}">
                                <li><a class="sub-category-link" href="/CMS/customer.htm">
                                        <span class="sub-ico-align icon-klienci-ico"></span> Klienci</a>
                                </li>
                            </c:if>
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('DownloadFiles')}">
                                <li><a class="sub-category-link" href="/CMS/fileList.htm">
                                    <span class="sub-ico-align icon-pliki-ico"></span> Pliki</a>
                                </li>
                            </c:if>
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewEmployees')}"> 
                                <li><a class="sub-category-link" href="/CMS/employee.htm">
                                    <span class="sub-ico-align icon-pracownicy-ico"></span> Pracownicy</a>
                                </li>
                            </c:if>
                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('PrintReports')}">
                                <li><a class="sub-category-link" href="/CMS/report.htm">
                                    <span class="sub-ico-align icon-raporty-ico"></span> Raporty</a>
                                </li>
                            </c:if>
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewPositions')}">    
                                <li><a class="sub-category-link" href="/CMS/position.htm">
                                    <span class="sub-ico-align icon-stanowiska-ico"></span> Stanowiska</a>
                                </li>
                            </c:if>
                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewTerminals')}">
                                <li><a class="sub-category-link" href="/CMS/terminal.htm">
                                    <span class="sub-ico-align icon-terminale-ico"></span> Terminale</a>
                                </li>
                            </c:if>
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewContracts')}">
                                <li><a class="sub-category-link" href="/CMS/contract.htm">
                                    <span class="sub-ico-align icon-umowy-ico"></span> Umowy</a>
                                </li>
                            </c:if>
                            
                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewDepartments')}">
                                <li><a class="sub-category-link" href="/CMS/department.htm">
                                    <span class="sub-ico-align icon-wydzialy-ico"></span> Wydziały</a>
                                </li>
                            </c:if>
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewTasks')}">
                                 <li><a class="sub-category-link" href="/CMS/task.htm">
                                    <span class="sub-ico-align icon-task-ico"></span> Zadania</a>
                                </li>
                                
                            </c:if>
                                
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewEmployments')}">
                                 <li><a class="sub-category-link" href="/CMS/employment.htm">
                                    <span class="sub-ico-align icon-zatrudnienie-ico"></span> Zatrudnienie</a>
                                </li>
                                
                            </c:if>
                        </ul>
                    </li>

                    <li><a class="main-category-link" href="#" id="toggle-konfiguracja"><span class="main-ico-align icon-konfiguracja-systemu-ico"></span>System<i class="icon-arrow-right-ico strzalka-position"></i></a>
                        <ul id="konfiguracja-systemu" style="display:none;">
                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewGroups')}">
                                <li><a class="sub-category-link" href="/CMS/groupList.htm">
                                        <span class="sub-ico-align icon-grupy-ico"></span>Grupy</a>
                                </li>
                            </c:if>                             
                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewDictionaries')}">
                                <li><a class="sub-category-link" href="/CMS/dictionaryList.htm">
                                    <span class="sub-ico-align icon-slowniki-ico"></span> Słowniki</a>
                                </li>
                            </c:if>
                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewSettings')}">
                                <li><a class="sub-category-link" href="/CMS/setting.htm">
                                    <span class="sub-ico-align icon-ustawienia-systemowe-ico"></span> Ustawienia systemowe</a>
                                </li>
                            </c:if>
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewUsers')}">
                                <li><a class="sub-category-link" href="/CMS/userList.htm">
                                    <span class="sub-ico-align icon-uzytkownicy-ico"></span> Użytkownicy</a>
                                </li>
                            </c:if>
                            
                            <c:if test="${user.privilegeKeyCodes.contains('all') ||user.privilegeKeyCodes.contains('ViewManageFiles')}">
                                <li><a class="sub-category-link" href="/CMS/manageFile.htm">
                                        <span class="sub-ico-align icon-zarzadzanie-plikami-ico"></span> Zarządzanie plikami</a>
                                </li>
                            </c:if>
                        </ul>
                    </li>

                </ul>




                <!-- tiny only nav-->
                <ul class="nav main-navigation visible-xs cf" id="xs-menu">

                        <li>
                            <ul>
                                        <li><a class="sub-category-link" href="/CMS/home.htm">
                                            <span class="sub-ico-align icon-home-ico"></span></a>
                                        </li>
                                        
                                        
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewTerminals')}">
                                    <li><a class="sub-category-link" href="/CMS/archive.htm">
                                        <span class="sub-ico-align icon-archiwum-ico"></span></a>
                                    </li>
                                </c:if>
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewCompanies')}">
                                    <li><a class="sub-category-link" href="/CMS/company.htm">
                                        <span class="sub-ico-align icon-firmy-ico"></span></a>
                                    </li>
                                </c:if>
                                    
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewCustomers')}">
                                    <li><a class="sub-category-link" href="/CMS/customer.htm">
                                        <span class="sub-ico-align icon-klienci-ico"></span></a>
                                    </li>
                                </c:if>
                                    
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('DownloadFiles')}">
                                    <li><a class="sub-category-link" href="/CMS/fileList.htm">
                                        <span class="sub-ico-align icon-pliki-ico"></span></a>
                                    </li>
                                </c:if>
                                    
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewEmployees')}"> 
                                    <li><a class="sub-category-link" href="/CMS/employee.htm">
                                        <span class="sub-ico-align icon-pracownicy-ico"></span></a>
                                    </li>
                                </c:if>
                                
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('PrintReports')}">
                                    <li><a class="sub-category-link" href="/CMS/report.htm">
                                        <span class="sub-ico-align icon-raporty-ico"></span></a>
                                    </li>
                                </c:if>
                                    
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewPositions')}">    
                                    <li><a class="sub-category-link" href="/CMS/position.htm">
                                        <span class="sub-ico-align icon-stanowiska-ico"></span></a>
                                    </li>
                                </c:if>
                                
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewTerminals')}">
                                    <li><a class="sub-category-link" href="/CMS/terminal.htm">
                                        <span class="sub-ico-align icon-terminale-ico"></span></a>
                                    </li>
                                </c:if>
                                    
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewContracts')}">
                                    <li><a class="sub-category-link" href="/CMS/contract.htm">
                                        <span class="sub-ico-align icon-umowy-ico"></span></a>
                                    </li>
                                </c:if>
                                
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewDepartments')}">
                                    <li><a class="sub-category-link" href="/CMS/department.htm">
                                        <span class="sub-ico-align icon-wydzialy-ico"></span></a>
                                    </li>
                                </c:if>
                                    
                                      <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewTasks')}">
                                 <li><a class="sub-category-link" href="/CMS/task.htm">
                                    <span class="sub-ico-align icon-task-ico"></span></a>
                                </li>
                                
                            </c:if>
                                
                                
                            <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewEmployments')}">
                                 <li><a class="sub-category-link" href="/CMS/employment.htm">
                                    <span class="sub-ico-align icon-zatrudnienie-ico"></span></a>
                                </li>
                                
                            </c:if>
                                    
                            </ul>
                        </li>

                        <li>
                            <ul>
                                <c:if test="${user.privilegeKeyCodes.contains('all') ||user.privilegeKeyCodes.contains('ViewGroups')}">
                                    <li><a class="sub-category-link" href="/CMS/groupList.htm">
                                        <span class="sub-ico-align icon-grupy-ico"></span></a>
                                    </li>
                                </c:if>
                                    
                                
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewDictionaries')}">
                                    <li><a class="sub-category-link" href="/CMS/dictionaryList.htm">
                                        <span class="sub-ico-align icon-slowniki-ico"></span></a>
                                    </li>
                                </c:if>
                                
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewSettings')}">
                                    <li><a class="sub-category-link" href="/CMS/setting.htm">
                                    <span class="sub-ico-align icon-ustawienia-systemowe-ico"></span></a>
                                    </li>
                                </c:if>
                                    
                                <c:if test="${user.privilegeKeyCodes.contains('all') || user.privilegeKeyCodes.contains('ViewUsers')}">
                                    <li><a class="sub-category-link" href="/CMS/userList.htm">
                                        <span class="sub-ico-align icon-uzytkownicy-ico"></span></a>
                                    </li>
                                </c:if>
                                
                                <c:if test="${user.privilegeKeyCodes.contains('all') ||user.privilegeKeyCodes.contains('ViewManageFiles')}">
                                <li><a class="sub-category-link" href="/CMS/manageFile.htm">
                                        <span class="sub-ico-align icon-zarzadzanie-plikami-ico"></span></a>
                                </li>
                                </c:if>
                            </ul>
                        </li>

                </ul>


            </div>
            <!-- /sidebar -->

            <div class="column col-sm-10 col-xs-10" id="main">



                <div class="row notification-bar">

                    <div class="magic-button visible-xs">
                        <a href="#" data-toggle="offcanvas" class="visible-xs text-center">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>


                        </a>
                    </div>


                    <div class="left-side-container">
                
                            


                    </div>

                    <div class="right-side-container">

                        
                            <c:if test="${user!=null}">                        
                        
                                <div class="logout-container">
                                    <form method="get" action="logout.htm">
                                        <input class="logout-button" type="submit" value="wyloguj"/>
                                    </form>
                                </div>
                          
                            </c:if>
                        
                        <div ng-init="showChat = false" ng-click="showChat = !showChat" class="wiadomosci-container">                      
                            <span class="wiadomosci-text hidden-xs">czat</span>
                            <span class="icon-wiadomosci-ico wiadomosci-ico"></span>
                        </div>
                        
                   


                    </div> <!-- end right-side-panel -->





                    <div class="czat-container col-lg-5 col-md-6 col-xs-6" ng-show="showChat" ng-include='"getUserMessages.htm"'>
                     
                        
                    </div>
                    <!--<div class="czat-container col-lg-3" ng-show="showChat" ng-include='"getUserMessages.htm"'></div>-->
                </div>



                <div id="breadcrumbs" class="row breadcrumb-bar rcrumbs">
                    
                    <!--<ul class="breadcrumbs">
                        <li>jesteś w HRSystem</li>
                        <li>Zarządzanie zasobami</li>
                        <li>Firmy</li>
                    </ul>-->
                
                    
                    <div class="col-lg-12">            
                        <span class="wyszukiwarka-ico icon-wyszukiwarka-ico"></span>
                        <input class="wyszukiwarka" placeholder="wyszukaj..." type="text" ng-model="searchText"/>
                    </div>
              
                </div>


                <div class="row content-container">
                    <jsp:doBody/>
                </div>


            </div>



        </div>
    </div>
</div>


    
    <script src="/CMS/resources/js/jquery-ui.min.js"></script>
    <script src="/CMS/resources/js/bootstrap.js"></script>
    <script src="/CMS/resources/js/bootstrap.min.js"></script>
    <script src="/CMS/resources/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="/CMS/resources/js/jquery.rcrumbs.js"></script>
    <script src="/CMS/resources/js/custom.js"></script>

<script>
    $("#breadcrumbs").rcrumbs({animation: {activated: false}});



</script>
            
              
       
           

        </c:if>
        <c:if test="${user==null}">
            Może byś się zalogował?
        </c:if>
    </body>
</html>
