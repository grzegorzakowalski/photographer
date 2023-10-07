<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 29.09.2023
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar" role="navigation" aria-label="main navigation" style="background-color: #001F3F">
    <div class="navbar-brand">
        <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item has-text-light" href="/">
                Strona startowa
            </a>

            <a class="navbar-item has-text-light" href="${pageContext.request.contextPath}/galery">
                Galeria
            </a>

            <a class="navbar-item has-text-light" href="${pageContext.request.contextPath}/timetable?shift=0">
                Terminarz
            </a>

            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link has-text-light">
                    Więcej
                </a>

                <div class="navbar-dropdown">
                    <a class="navbar-item">
                        Kontakt
                    </a>
                    <a class="navbar-item">
                        O stronie
                    </a>
                    <hr class="navbar-divider">
                    <a class="navbar-item">
                        Zgłoś problem
                    </a>
                </div>
            </div>
        </div>

        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                    <sec:authorize access="isAnonymous()">
                    <a class="button is-primary" href="${pageContext.request.contextPath}/registry">
                        <strong>Zarejestruj się</strong>
                    </a>
                    <a class="button is-light" href="${pageContext.request.contextPath}/login">
                        Zaloguj się
                    </a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a class="button is-primary" href="${pageContext.request.contextPath}/panel">
                            <strong>Twój panel</strong>
                        </a>
                        <a class="button is-light" href="${pageContext.request.contextPath}/logout">
                            Wyloguj się
                        </a>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</nav>
