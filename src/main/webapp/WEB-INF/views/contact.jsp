<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 11.10.2023
  Time: 01:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
<c:import url="nav-bar.jsp"/>
    <div class="hero-body">

<section class="section is-large">
    <h1 class="title">Tutaj znajdziesz dane do kontaktu.</h1>
    <h2 class="subtitle">
        Mój numer telefonu to: 666-666-666. Polecam kontaktować się w godzinach 14:00 - 20:00.
        Jeżeli jednak preferujesz kontakt Email, proszę bardzo: email@Email.com.
    </h2>
</section>
    </div>
<c:import url="footer.jsp"/>
</section>

</body>
</html>
