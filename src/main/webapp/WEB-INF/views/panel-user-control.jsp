<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 17.10.2023
  Time: 19:54
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
    <section class="section">
      <h1 class="title">
        Lista użytkowników:
      </h1>
      <h2 class="subtitle">
        <c:forEach items="${allUsers}" var="user">
          <a href="${pageContext.request.contextPath}/panel/change-user?id=${user.id}">
            Imię: ${user.firstName}<br>
            Email: ${user.username}<br>
            Numer telefonu: ${user.phoneNumber}<br>
            Rola konta: ${user.role.substring(5)}<br>
            ${"-".repeat(30)}<br>
          </a>
        </c:forEach>
        <h1><a href="/panel/new-user">Dodaj nowego użytkownika</a></h1>
      </h2>
    </section>
  </div>
  <c:import url="footer.jsp"/>
</section>

</body>
</html>
