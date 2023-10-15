<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 11.10.2023
  Time: 18:53
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
<section class="section is-large">
    <h1 class="title">Strona została stworzona przez <i class="fab fa-github"></i><a href="https://github.com/grzegorzakowalski">Github.com</a></h1>
    <h2 class="subtitle">
        Prawa autorskie zastrzeżone ©2023.
    </h2>
</section>
    </div>
    <c:import url="footer.jsp"/>
</section>
</body>
</html>
