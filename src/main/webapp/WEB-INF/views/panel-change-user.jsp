<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 17.10.2023
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
    <c:import url="nav-bar.jsp"/>
    <div class="hero-body">
        <br>
        <c:if test="${error != null}">
            <p class="help is-danger">Podane poprzednio dane były nieprawidłowe</p>
        </c:if>
        <br>
        <section class="section">
            <form:form modelAttribute="user" method="post">
            <div class="field">
                <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Email:</label>
                <div class="control">
                    <form:input path="username" cssClass="input"/>
                </div>
            </div>
            <div class="field">
                <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Numer Telefonu:</label>
                <div class="control">
                    <form:input path="phoneNumber" cssClass="input"/>
                </div>
            </div>
            <div class="field">
                <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Hasło(Zakodowane):</label>
                <div class="control">
                    <form:password path="password" cssClass="input"/>
                </div>
            </div>
            <div class="field">
                <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Imię:</label>
                <div class="control">
                    <form:input path="firstName" cssClass="input"/>
                </div>
            </div>
            <form:hidden path="id"/>
            <form:hidden path="pictures"/>
            <form:hidden path="active"/>
            <div class="field">
                <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Rola konta:</label>
                <div class="control">
                <form:select path="role">
                    <form:option value="ROLE_ADMIN" >ADMIN</form:option>
                    <form:option value="ROLE_USER">USER</form:option>
                </form:select>
                </div>
            </div>
                    <button type="submit" class="button ${siteColor} is-inverted">Potwierdź zmiany</button>
                    </form:form>
                <form method="post" action="${pageContext.request.contextPath}/panel/delete-user">
                    <input type="hidden" value="${user.id}" name="id">
                        <button type="submit" class="button is-danger is-inverted">Usuń użytkownika</button>
                </form>
        </section>
    </div>
    <c:import url="footer.jsp"/>
</section>
</body>
</html>
