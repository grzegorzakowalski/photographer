<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 29.09.2023
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background-color: #F5F5F5">
<c:import url="header.jsp"/>
<body>

<c:import url="nav-bar.jsp"/>
    <br>
    <div class="tile is-ancestor">
        <div class="tile is-parent is-vertical">
            <div class="tile is-child box">
                <img src="home-page-photo.jpg">
            </div>
            <div class="tile is-child box">
                <p class="title">${user}</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis.</p>
            </div>
        </div>
        <div class="tile is-parent is-vertical">
            <div class="tile is-child box">
                <p class="title">Witaj na mojej stronie!</p>
                <p>Jestem początkującym fotografem </p>
            </div>
            <div class="tile is-child box">
                <img src="home-page-photo.jpg">
            </div>
        </div>
    </div>
    <div class="tile is-ancestor">
        <div class="tile is-parent is-vertical">
            <div class="tile is-child box">
                <img src="home-page-photo.jpg">
            </div>
            <div class="title is-child box">
                <p class="title">Witaj na mojej stronie!</p>
                <p>Jestem początkującym fotografem </p>
            </div>
        </div>
        <div class="tile is-parent is-vertical">
            <div class="tile is-child box">
                <p class="title">Witaj na mojej stronie!</p>
                <p>Jestem początkującym fotografem </p>
            </div>
            <div class="title is-child box">
                <img src="home-page-photo.jpg">

            </div>
        </div>
    </div>


</body>
</html>
