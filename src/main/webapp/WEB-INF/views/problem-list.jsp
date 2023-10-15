<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 15.10.2023
  Time: 15:57
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
        <c:forEach items="${issues}" var="issue">
            <section class="section">
                <h1 class="title">${issue.title}</h1>
                <h2 class="subtitle">${issue.description}<br>
                    <form method="post" action="${pageContext.request.contextPath}/problem/resolve">
                        <input type="hidden" name="id" value="${issue.id}">
                        <button type="submit" class="button ${siteColor} is-inverted">Rozwiązany</button>
                    </form>
                </h2>
            </section>
        </c:forEach>

    </div>
    <c:import url="footer.jsp"/>
</section>

</body>
</html>
