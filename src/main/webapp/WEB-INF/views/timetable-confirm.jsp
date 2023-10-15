<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 09.10.2023
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
    <c:import url="nav-bar.jsp"/>
    <div class="hero-body">
        <br>
        <div class="container has-text-centered">
    <section class="section">
        <h1 class="title">${timetable.owner.firstName}, nr tel:${timetable.owner.phoneNumber}, email: ${timetable.owner.username}</h1>
        <h2 class="subtitle">
            Opis:<br>
            ${timetable.description}<br>
            Preferowana godzina: ${timetable.hour}
        </h2>
    </section>
    <form:form modelAttribute="timetable" method="post">
        <div class="field">
            <label class="label">Popraw opis (wymagane, ale jak opis jest według Ciebie poprawny i zrozumiały to go przekopiuj).</label>
            <div class="control">
                <form:input path="description" cssClass="input"/>
            </div>
        </div>
        <div class="field">
            <label class="label">Popraw godzinę (wymagane, ale jeżeli godzina się zgadza to ją przekopiuj).</label>
            <div class="control">
                <form:input path="hour" cssClass="input"/>
            </div>
        </div>
        <form:hidden path="owner"/>
        <form:hidden path="date"/>
        <form:hidden path="id"/>
        <form:hidden path="confirmed"/>
        <button type="submit" class="button ${siteColor} is-inverted">Zatwierdź</button>
    </form:form>
    <form:form method="get" modelAttribute="timetable" action="/timetable/delete">
        <form:hidden path="id"/>
        <button type="submit" class="button is-danger is-inverted">Usuń</button>
    </form:form>

</div>
    </div>
    <c:import url="footer.jsp"/>
</section>
</body>
</html>
