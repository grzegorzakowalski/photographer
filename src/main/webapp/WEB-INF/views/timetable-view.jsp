<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 27.10.2023
  Time: 20:49
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
            <c:if test="${timetable.id > -1}">
                <section class="section">
                    <h1 class="title">${timetable.date} : ${timetable.hour}</h1>
                    <h2 class="subtitle">
                        ${timetable.description}
                    </h2>
                </section>
            </c:if>
            <c:if test="${timetable.id == -1}">
                <section class="section">
                    <h1 class="title">Coś poszło nie tak</h1>
                </section>
            </c:if>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</section>
</body>
</html>
