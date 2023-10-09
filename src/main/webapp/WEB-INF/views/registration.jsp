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
<c:import url="header.jsp"/>
<body>

<c:import url="nav-bar.jsp"/>

<div class="container" style="width: 30%">
    <br>
    <br>
<form:form modelAttribute="user" method="post">
    <div class="field">
        <label class="label">Email</label>
        <div class="control">
            <form:input path="username" cssClass="input" placeholder="Jan.Kowalski@example.pl"/>
        </div>
        <c:forEach items="${validated}" var="v">
            <c:if test="${v.propertyPath == 'username'}">
                <p class="help is-danger">Podaj poprawny adres email</p>
            </c:if>
        </c:forEach>

    </div>
    <div class="field">
        <label class="label">Hasło</label>
        <div class="control">
            <form:password path="password" cssClass="input"/>
        </div>
        <c:forEach items="${validated}" var="v">
            <c:if test="${v.propertyPath == 'password'}">
                <p class="help is-danger">Pole wymagane</p>
            </c:if>
        </c:forEach>
    </div>
    <div class="field">
        <label class="label">Numer telefonu</label>
        <div class="control">
            <form:input path="phoneNumber" cssClass="input" placeholder="123456789"/>
        </div>
        <c:forEach items="${validated}" var="v">
            <c:if test="${v.propertyPath == 'phoneNumber'}">
                <p class="help is-danger">Pole wymagane</p>
            </c:if>
        </c:forEach>
    </div>
    <div class="field">
        <label class="label">Imię</label>
        <div class="control">
            <form:input path="firstName" cssClass="input" placeholder="Jan"/>
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
