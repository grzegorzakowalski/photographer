<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 16.10.2023
  Time: 00:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
  <c:import url="nav-bar.jsp"/>
  <div class="hero-body">
    <form:form method="post" modelAttribute="addPhoto">
      <div class="field">
        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Poniżej wstaw link do zdjęć</label>
        <div class="control">
          <form:input path="pictures.link" cssClass="input"/>
        </div>
      </div>
      <div class="field">
        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Tutaj krótki opis sesji</label>
        <div class="control">
          <form:input path="pictures.description" cssClass="input"/>
        </div>
      </div>
      <form:hidden path="timetable"/>
      <button type="submit" class="button ${siteColor} is-inverted">Potwierdź</button>
    </form:form>
  </div>
  <c:import url="footer.jsp"/>
</section>
</body>
</html>
