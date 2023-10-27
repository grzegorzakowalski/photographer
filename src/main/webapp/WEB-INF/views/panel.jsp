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
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
    <c:import url="nav-bar.jsp"/>
    <div class="hero-body has-text-centered">
        <div class="container is-fluid">
            <section class="section">
                <h1 class="title">Witaj ${user.firstName}!</h1>
                <h2 class="subtitle">
                    Oto panel do kontrolowania twojego konta.
                </h2>
            </section>
            <c:if test="${user.pictures.size() > 0}">
                <section class="section">
                    <h1 class="title">Oto lista twoich zdjęć:</h1>
                    <h2 class="subtitle">
                        <c:forEach items="${user.pictures}" var="pic">
                                <a href="${pic.link}">${pic.description}</a><br>
                        </c:forEach>
                    </h2>
                </section>
            </c:if>
            <c:if test="${user.pictures.size() == 0}">
                <section class="section">
                    <h1 class="title">Nie masz na razie żadnych zdjęć.</h1>
                    <br>
                    <h2 class="subtitle">
                        Jednak zawsze możemy to zmienić! <a href="${pageContext.request.contextPath}/timetable">Tutaj</a>, albo poprzez <a href="${pageContext.request.contextPath}/contact">kontakt</a>.
                    </h2>
                </section>
            </c:if>
            <section class="section">
                <h1 class="title">Lista twoich potwierdzonych sesji:</h1>
                <br>
                <h2 class="subtitle">
                    <c:if test="${timetableConfirmed.size() == 0}">Niestety nie ma żadnej.</c:if>
                    <c:forEach items="${timetableConfirmed}" var="tt">
                        ${tt.date} : ${tt.hour}<br>
                        <a href="${pageContext.request.contextPath}/timetable/view?id=${tt.id}">${tt.description}</a><br><br>
                    </c:forEach>
                </h2>
            </section>
            <section class="section">
                <h1 class="title">Lista twoich niepotwierdzonych sesji:</h1>
                <br>
                <h2 class="subtitle">
                    <c:if test="${timetableNotConfirmed.size() == 0}">Nie ma żadnej.</c:if>
                    <c:forEach items="${timetableNotConfirmed}" var="tt">
                        ${tt.date} : ${tt.hour}<br>
                        <a href="${pageContext.request.contextPath}/timetable/view?id=${tt.id}">${tt.description}</a><br><br>
                    </c:forEach>
                </h2>
            </section>
            <br>
            <br>
            <form method="post">
                <div class="field">
                    <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Zmień hasło:</label>
                    <div class="control">
                        <input type="password" class="input" name="password" style="width: 10%"/>
                    </div>
                </div>
                <button type="submit" class="button ${siteColor} is-inverted">Zmień hasło</button>
            </form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</section>

</body>
</html>
