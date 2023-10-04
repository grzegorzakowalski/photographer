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
<c:import url="header.jsp"></c:import>
<body>

<c:import url="nav-bar.jsp"></c:import>
<div class="container" style="width: 30%">
    <br>
    <br>
<form method="post" action="${pageContext.request.contextPath}/login">
    <div class="field">
        <label class="label">Email</label>
        <div class="control">
            <input class="input" type="text" placeholder="Jan.Kowalski@example.pl" name="username">
        </div>
    </div>

    <div class="field">
        <label class="label">Hasło</label>
        <div class="control">
            <input class="input" type="password" placeholder="password" name="password">
        </div>
    </div>
    <div class="field is-grouped-right">
        <div class="control">
            <button class="button is-link">Submit</button>
        </div>
    </div>
</form>
</div>

</body>
</html>
