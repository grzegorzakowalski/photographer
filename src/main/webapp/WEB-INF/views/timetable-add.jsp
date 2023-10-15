<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 04.10.2023
  Time: 00:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background-color: #F5F5F5">
<c:import url="header.jsp"/>
<body>
<section class="hero ${siteColor} is-fullheight">
    <c:import url="nav-bar.jsp"/>
    <div class="hero-body">
    <br>
    <br>
<form:form modelAttribute="timetable" method="post">
    <section class="section">
        <h1 class="title">Witaj, jeżeli chcesz zabukować termin, wypełnij poniższy formularz. Pamiętaj, że wszystkie pola są wymagane. Również, jeżeli chcesz mieć dostęp do swoich zdjęć na stronie, polecam założyć konto najpierw :)</h1>
        <sec:authorize access="isAnonymous()">
        <h2 class="subtitle">
            Pola Email oraz numer telefonu są wymagane do kontaktu z Tobą.
        </h2>
        </sec:authorize>
    </section>
    <sec:authorize access="isAnonymous()">
    <div class="field">
        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Email</label>
        <div class="control">
            <form:input path="owner.username" cssClass="input" placeholder="Jan.Kowalski@example.pl"/>
        </div>
        <c:forEach items="${validated}" var="v">
            <c:if test="${v.propertyPath == 'owner.username'}">
                <p class="help is-danger">Podaj poprawny adres email</p>
            </c:if>
        </c:forEach>
    </div>
    <div class="field">
        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Numer telefonu</label>
        <div class="control">
            <form:input path="owner.phoneNumber" cssClass="input" placeholder="511 111 111"/>
        </div>
        <c:forEach items="${validated}" var="v">
            <c:if test="${v.propertyPath == 'owner.phoneNumber'}">
                <p class="help is-danger">Pole wymagane</p>
            </c:if>
        </c:forEach>
    </div>
        <form:hidden path="owner.role"/>
        <form:hidden path="owner.password"/>
        <form:hidden path="owner.active"/>

    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <form:hidden path="owner"/>
    </sec:authorize>
    <form:hidden path="date"/>
    <div class="field">
        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Godzina początku sesji, która by Ci najbardziej pasowała.</label>
        <div class="control">
            <form:input path="hour" cssClass="input" placeholder="15:00"/>
        </div>
        <c:forEach items="${validated}" var="v">
            <c:if test="${v.propertyPath == 'hour'}">
                <p class="help is-danger">Pole wymagane</p>
            </c:if>
        </c:forEach>
    </div>
    <div class="field">
        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Twój opis, czego oczekujesz, cokolwiek chcesz powiedzieć o tej sesji umieść to tutaj.</label>
        <div class="control">
            <form:textarea path="description" cssClass="textarea"/>
        </div>
        <c:forEach items="${validated}" var="v">
            <c:if test="${v.propertyPath == 'description'}">
                <p class="help is-danger">Pole wymagane</p>
            </c:if>
        </c:forEach>
    </div>
    <div class="field is-grouped is-grouped-right">
        <p class="control">
            <form:button class="button #{siteColor} is-inverted" type="submit">
                Submit
            </form:button>
        </p>
    </div>

</form:form>

</div>
        <c:import url="footer.jsp"/>
</section>
</body>
</html>
