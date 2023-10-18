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
<html>
    <c:import url="header.jsp"/>
<body>
    <section class="hero ${siteColor} is-fullheight">
        <c:import url="nav-bar.jsp"/>
        <div class="hero-body">
            <div class="container has-text-centered">
                <form:form modelAttribute="user" method="post">
                    <div class="field">
                        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Email</label>
                        <div class="control">
                            <form:input path="username" cssClass="input ${siteColor}" style="width: 25%" placeholder="Jan.Kowalski@example.pl"/>
                        </div>
                        <c:forEach items="${validated}" var="v">
                            <c:if test="${v.propertyPath == 'username'}">
                                <p class="help is-danger">Podaj poprawny adres email</p>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="field">
                        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Hasło</label>
                        <div class="control">
                            <form:password path="password" cssClass="input ${siteColor}" style="width: 25%" placeholder="********"/>
                        </div>
                        <c:forEach items="${validated}" var="v">
                            <c:if test="${v.propertyPath == 'password'}">
                                <p class="help is-danger">Pole wymagane</p>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="field">
                        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Numer telefonu</label>
                        <div class="control">
                            <form:input path="phoneNumber" cssClass="input ${siteColor}" style="width: 25%" placeholder="516 615 516"/>
                        </div>
                        <c:forEach items="${validated}" var="v">
                            <c:if test="${v.propertyPath == 'phoneNumber'}">
                                <p class="help is-danger">Pole wymagane</p>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="field">
                        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Imię</label>
                        <div class="control">
                            <form:input path="firstName" cssClass="input ${siteColor}" style="width: 25%" placeholder="Jan"/>
                        </div>
                    </div>
                    <div class="field is-grouped is-grouped-right" style="width: 62.5%">
                        <p class="control">
                            <form:button class="button ${siteColor} is-inverted" type="submit">
                                Submit
                            </form:button>
                        </p>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
</body>
</html>
