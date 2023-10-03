<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form modelAttribute="user" method="post">
    <div class="field">
        <label class="label">Email</label>
        <div class="control">
            <form:input path="username" cssClass="input" placeholder="Jan.Kowalski@example.pl"></form:input>
        </div>
        <p class="help">On będzie także loginem</p>
    </div>
    <div class="field">
        <label class="label">Hasło</label>
        <div class="control">
            <form:password path="password" cssClass="input"></form:password>
        </div>
        <p class="help">Pole wymagane</p>
    </div>
    <div class="field">
        <label class="label">Numer telefonu</label>
        <div class="control">
            <form:input path="phoneNumber" cssClass="input" placeholder="123456789"></form:input>
        </div>
        <p class="help">Pole wymagane</p>
    </div>
    <div class="field">
        <label class="label">Imię</label>
        <div class="control">
            <form:input path="firstName" cssClass="input" placeholder="Jan"></form:input>
        </div>
    </div>
    <div class="field is-grouped is-grouped-right">
        <p class="control">
            <form:button class="button is-primary" type="submit">
                Submit
            </form:button>
        </p>
    </div>
</form:form>

</div>
</body>
</html>
