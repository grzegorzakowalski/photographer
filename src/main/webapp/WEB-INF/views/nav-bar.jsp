<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 29.09.2023
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="hero-head">
    <header class="navbar">
        <div class="container">
            <div class="navbar-brand">
                <a class="navbar-item">
                    <a href="${pageContext.request.contextPath}/"><img src="/photoLogo.png" alt="Logo" ></a>
                </a>
                <span class="navbar-burger" data-target="navbarMenuHeroC">
                    <span></span>
                    <span></span>
                    <span></span>
                </span>
            </div>
            <div id="navbarMenuHeroC" class="navbar-menu">
                <div class="navbar-end">
                    <a class="navbar-item <c:if test="${navIsActive.equals('home')}">is-active</c:if>" href="${pageContext.request.contextPath}/">
                        Strona startowa
                    </a>
                    <a class="navbar-item <c:if test="${navIsActive.equals('timetable')}">is-active</c:if>" href="${pageContext.request.contextPath}/timetable">
                        Zabukuj termin
                    </a>
                    <a class="navbar-item" href="${pageContext.request.contextPath}/about">
                        O stronie
                    </a>
                    <span class="navbar-item">
                        <div class="buttons">
                            <sec:authorize access="isAnonymous()">
                                <a class="button ${siteColor} is-inverted" href="${pageContext.request.contextPath}/registry">
                                    <strong>Zarejestruj się</strong>
                                </a>
                                <a class="button ${siteColor} is-inverted" href="${pageContext.request.contextPath}/login">
                                    <strong>Zaloguj się</strong>
                                </a>
                            </sec:authorize>
                            <sec:authorize access="isAuthenticated()">
                                <a class="button ${siteColor} is-inverted" href="${pageContext.request.contextPath}/panel">
                                    <strong>Twój panel</strong>
                                </a>
                                <a class="button ${siteColor} is-inverted" href="${pageContext.request.contextPath}/logout">
                                    Wyloguj się
                                </a>
                            </sec:authorize>
                        </div>
                    </span>
                </div>
            </div>
        </div>
    </header>
</div>
