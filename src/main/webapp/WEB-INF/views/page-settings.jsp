<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 14.10.2023
  Time: 17:17
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
        <div class="container has-text-centered">
            <form:form method="post" modelAttribute="pageSettings">
                <div class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Tutaj wpisz o sobie</div>
                <div class="control">
                    <form:textarea path="aboutMe" cssClass="textarea has-fixed-size"/>
                </div>
                <br>
                <div class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Wybierz kolor strony
                </div>
                <div class="control">
                    <div class="select is-rounded">
                        <form:select path="siteColor">
                            <c:forEach items="${colorMap}" var="color">
                                <form:option  value="${color.key}"  >${color.key}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
                <div class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Tutaj podaj kontaktowy numer telefonu</div>
                <div class="control">
                    <form:input path="contactPhoneNumber" cssClass="input"/>
                </div>
                <div class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Tutaj podaj kontaktowy adres email</div>
                <div class="control">
                    <form:input path="contactEmail" cssClass="input"/>
                </div>
                <br>
                <div class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Tutaj podaj przedział czasowy, w którym akceptujesz rozmowy telefoniczne</div>
                <div class="control">
                    <form:input path="contactHours" cssClass="input"/>
                </div>
                <br>
                <div class="buttons is-right">
                    <button type="submit" class="button ${siteColor} is-inverted is-rounded">Potwierdź</button>
                </div>
            </form:form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</section>
</body>
</html>
