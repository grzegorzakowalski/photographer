<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 14.10.2023
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${navIsActive.equals('home')}">
<div class="hero-foot">
    <nav class="tabs is-boxed is-fullwidth">
        <div class="container">
            <ul>
                <li class="<c:if test="${footerIsActive.equals('home')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/">O mnie</a></li>
                <li class="<c:if test="${footerIsActive.equals('galery')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/galery">Galeria</a></li>
                <sec:authorize access="hasRole('ADMIN')">
                <li class="<c:if test="${footerIsActive.equals('aboutMe')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/page-settings">Ustawienia strony</a></li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
</div>
</c:if>
<c:if test="${navIsActive.equals('timetable')}">
    <div class="hero-foot">
        <nav class="tabs is-boxed is-fullwidth">
            <div class="container">
                <ul>
                    <li class="<c:if test="${footerIsActive.equals('timetable')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/timetable">Zabukuj termin</a></li>
                    <li class="<c:if test="${footerIsActive.equals('contact')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/contact">Kontakt</a></li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li class="<c:if test="${footerIsActive.equals('timetableList')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/timetable/list">Terminy do zabukowania i ustawienia</a></li>
                    </sec:authorize>
                </ul>
            </div>
        </nav>
    </div>
</c:if>
<c:if test="${navIsActive.equals('about')}">
    <div class="hero-foot">
        <nav class="tabs is-boxed is-fullwidth">
            <div class="container">
                <ul>
                    <li class="<c:if test="${footerIsActive.equals('about')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/about">O stronie</a></li>
                    <li class="<c:if test="${footerIsActive.equals('problem')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/problem">Zgłoś problem</a></li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li class="<c:if test="${footerIsActive.equals('problemList')}">is-active</c:if>"><a href="${pageContext.request.contextPath}/problem/list">Lista zgłoszonych problemów</a></li>
                    </sec:authorize>
                </ul>
            </div>
        </nav>
    </div>
</c:if>
