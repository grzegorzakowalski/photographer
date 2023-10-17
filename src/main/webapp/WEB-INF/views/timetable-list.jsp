<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 15.10.2023
  Time: 01:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
    <c:import url="nav-bar.jsp"/>
    <div class="hero-body">
        <c:if test="${msg != null}">
            <c:if test="${msg.equals('delete-error')}">
                Coś poszło nie tak przy usuwaniu<br>
            </c:if>
        </c:if>
        <section class="section">
            <h1 class="title">Lista oczekujących terminów do zatwierdzenia:</h1>
            <h2 class="subtitle">
                <c:forEach items="${allNotConfirmed}" var="entry">
                    <a href="${pageContext.request.contextPath}/timetable/confirm?id=${entry.id}">${entry.date}: ${entry.description}</a><br>
                </c:forEach>
            </h2>
        </section>
        <section class="section">
            <h1 class="title">Lista potwierdzonych terminów bez dodanych zdjęć:</h1>
            <h2 class="subtitle">
                <c:forEach items="${allConfirmed}" var="entry">
                    <a href="${pageContext.request.contextPath}/timetable/add-photo?id=${entry.id}">${entry.date} ${entry.hour}: ${entry.description}</a><br>
                </c:forEach>
            </h2>
        </section>
        <form method="post">
            <div class="field">
                <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Podaj maksymalną ilość zatwierdzonych danego dnia</label>
                <div class="control">
                    <input name="maxPerDay" type="number" min="1" value="${maxPerDay}">
                </div>
            </div>
            <button type="submit" class="button ${siteColor} is-inverted">Zatwierdź</button>
        </form>
    </div>
    <c:import url="footer.jsp"/>
</section>

</body>
</html>
