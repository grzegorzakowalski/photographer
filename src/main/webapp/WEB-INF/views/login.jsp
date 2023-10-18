<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <br>
                <form method="post" action="${pageContext.request.contextPath}/login">
                    <div class="field">
                        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Email</label>
                        <div class="control">
                            <input class="input" style="width: 20%" type="text" placeholder="Jan.Kowalski@example.pl" name="username">
                        </div>
                    </div>
                    <div class="field">
                        <label class="label <c:if test="${!siteColor.equals('is-warning')}">has-text-light</c:if>">Hasło</label>
                        <div class="control">
                            <input class="input" style="width: 20%" type="password" placeholder="password" name="password">
                        </div>
                    </div>
                    <div class="field is-grouped-right">
                        <div class="control">
                            <button class="button is-link" type="submit">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
