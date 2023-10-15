<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 11.10.2023
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
  <c:import url="nav-bar.jsp"/>
  <div class="hero-body">
    <br>
<section class="section is-medium">
  <h2 class="title">W razie wystąpienia jakiegoś problemu należy wypełnić formularz.</h2>
</section>
    <form:form method="post" modelAttribute="issue">
    <div class="field">
      <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Podaj ogólne określenie problemu:</label>
      <div class="control">
        <form:input path="title" cssClass="input" />
      </div>
    </div>

        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Tutaj jak najdokładniejszy opis:</label>
        <div class="control">
          <form:textarea path="description" cssClass="input" rows="5"/>
        </div>

    <div class="field is-grouped is-grouped-right">
      <p class="control">
        <button type="submit" class="button ${siteColor} is-inverted">Zatwierdź</button>
      </p>
    </div>
    </form:form>

  </div>
  <c:import url="footer.jsp"/>
</section>
</body>
</html>
