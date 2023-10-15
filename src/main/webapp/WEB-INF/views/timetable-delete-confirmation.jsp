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
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
  <c:import url="nav-bar.jsp"/>
  <div class="hero-body">
    <div class="container has-text-centered">
      <section class="section is-large">
        <h2 class="subtitle">
          ${timetable.owner.firstName}, nr tel:${timetable.owner.phoneNumber}, email: ${timetable.owner.username}<br>
          Opis:<br>
          ${timetable.description}<br>
          Preferowana godzina: ${timetable.hour}<br>
        </h2>
        <h1 class="title">
          <form:form modelAttribute="timetable" method="post">
            <form:hidden path="id"/>
            <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>"><strong>Czy na pewno chcesz usunąć ten wpis?</strong></label>
            <button type="submit" class="button is-danger is-inverted">Usuń</button>
            <a href="${pageContext.request.contextPath}/timetable/confirm?id=${timetable.id}" class="button ${siteColor} is-inverted">Wróć</a>
          </form:form>
        </h1>
      </section>
    </div>
  </div>
  <c:import url="footer.jsp"/>
</section>

</body>
</html>
