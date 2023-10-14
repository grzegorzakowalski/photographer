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
                <li class=""><a>Zmodyfikuj o mnie</a></li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
</div>
</c:if>
