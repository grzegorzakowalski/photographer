<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 07.10.2023
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background-color: #F5F5F5">
<c:import url="header.jsp"/>
<body>
<c:import url="nav-bar.jsp"/>
<div class="container is-fluid">
    <section class="section is-large">
        <h1 class="title">Witaj ${user.firstName}!</h1>
        <h2 class="subtitle">
            Oto panel do kontrolowania twojego konta.
        </h2>
    </section>
    <c:if test="${user.pictures.size() > 0}">
        <section class="section">
            <h1 class="title">Oto lista twoich sesji:</h1>
            <h2 class="subtitle">
                <c:forEach items="${user.pictures}" var="pic">
                        <a href="http://${pic.link}">${pic.description}</a><br>
                </c:forEach>
            </h2>
        </section>
    </c:if>
    <c:if test="${user.pictures.size() == 0}">
        <section class="section">
            <h1 class="title">Nie masz na razie żadnych zdjęć</h1>
            <h2 class="subtitle">
                Ale zawsze możemy to zmienić! <a href="${pageContext.request.contextPath}/timetable">Tutaj</a>, albo poprzez <a href="${pageContext.request.contextPath}/contact">kontakt</a>.
            </h2>
        </section>
    </c:if>
    <sec:authorize access="hasRole('ADMIN')">
        <section class="section">
            <h1 class="title">Lista oczekujących terminów do zatwierdzenia:</h1>
            <h2 class="subtitle">
                <c:forEach items="${notConfirmedTimetable}" var="entry">
                        <a href="${pageContext.request.contextPath}/timetable/confirm?id=${entry.id}">${entry.date}: ${entry.description}</a>
                </c:forEach>
            </h2>
        </section>
    </sec:authorize>
</div>

</body>
</html>
