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
<%--            <p class="title has-text-centered">Tutaj pare słów o sobie </p>--%>
            <form method="post">
                <div class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Tutaj wpisz o sobie</div>
                <div class="control">
                    <textarea name="aboutMe" class="textarea has-fixed-size">${aboutMe}</textarea>
                </div>
                <br>
                <div class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Wybierz kolor strony</div>
                <div class="control">
                    <div class="select is-rounded">
                        <select name="color">
                            <c:forEach items="${colorMap}" var="color">
                                <option value="${color.key}" <c:if test="${color.value.equals(siteColor)}">selected</c:if>>${color.key}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
                <div class="buttons is-right">
                    <button type="submit" class="button ${siteColor} is-inverted is-rounded">Potwierdź</button>
                </div>
            </form>
        </div>

    </div>

    <c:import url="footer.jsp"/>

</section>
</body>
</html>
