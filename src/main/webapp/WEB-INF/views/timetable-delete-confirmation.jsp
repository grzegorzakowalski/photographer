<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 12.10.2023
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background-color: #F5F5F5">
<c:import url="header.jsp"/>
<body>
<c:import url="nav-bar.jsp"/>

<div class="container">
  <section class="section">
    <h2 class="subtitle">
      ${timetable.owner.firstName}, nr tel:${timetable.owner.phoneNumber}, email: ${timetable.owner.username}<br>
      Opis:<br>
      ${timetable.description}<br>
      Preferowana godzina: ${timetable.hour}<br>
    </h2>
    <h1 class="title">
      <form:form modelAttribute="timetable" method="post">
        <form:hidden path="id"/>
        <label class="label"><strong>Czy na pewno chcesz usunąć ten wpis?</strong></label>
        <button type="submit" class="button is-danger">Usuń</button>
        <a href="${pageContext.request.contextPath}/timetable/confirm?id=${timetable.id}" class="button is-link">Wróć</a>
      </form:form>
    </h1>
  </section>


</div>
</body>
</html>
