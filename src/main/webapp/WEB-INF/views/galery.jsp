<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 29.09.2023
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
<c:import url="nav-bar.jsp"/>
    <div class="hero-body">
<div class="container">
        <br>
            <div class="tile is-ancestor">
                <c:forEach items="${imgList}" var="miniList">
                    <div class="tile is-parent is-vertical">
                        <c:forEach items="${miniList}" var="img">
                            <div class="tile is-child">
                                <img src="${img.name}" title="${img.description}">
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <c:import url="footer.jsp"/>
</section>

</body>
</html>
