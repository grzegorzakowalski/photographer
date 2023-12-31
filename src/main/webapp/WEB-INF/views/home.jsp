<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 29.09.2023
  Time: 18:59
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
        <div class="tile is-ancestor">
            <div class="tile">
                <figure class="image">
                    <img src="home-page-photo.jpg" alt="Moje zdjęcie" class="is-rounded">
                </figure>
            </div>
            <div class="container">
                <p class="title has-text-centered">${aboutMe}</p>
            </div>
        </div>

    </div>

    <c:import url="footer.jsp"/>

</section>
</body>
</html>
