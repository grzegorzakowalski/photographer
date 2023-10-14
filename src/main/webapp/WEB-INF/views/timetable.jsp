<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 02.10.2023
  Time: 21:03
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
<div class="container-fluid">
    <section class="section">
        <h1 class="title">${myMonth.name} ${year}</h1>
        <h2 class="subtitle">
            Jeżeli w polu jest tekst na czerwono, oznacza to, że tego dnia już wolnych terminów.
        </h2>
    </section>
    <div class="table-container">
    <table class=" is-striped is-hoverable is-fullwidth is-bordered">
        <thead >
        <tr class="${siteColor} is-inverted">
            <th>Poniedziałek</th>
            <th>Wtorek</th>
            <th>Środa</th>
            <th>Czwartek</th>
            <th>Piątek</th>
            <th>Sobota</th>
            <th>Niedziela</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${myMonth.listOfWeeks}" varStatus="i">
            <tr>
                <c:forEach begin="0" end="6" varStatus="j">
                    <c:set var="actualDayOfMonth" value="${((i.index) * 7) + (j.index + 1) - firstDayOfMonth + 1}"/>
                    <td>
                        <c:if test="${actualDayOfMonth > 0 && actualDayOfMonth <= lastDayOfMonth}">
                            <c:choose>
                                <c:when test="${!allUnavailable.get(actualDayOfMonth - 1)}">
                                    <p style="font-size: 1.25rem" class="is-selected"><strong class="">${actualDayOfMonth}.<c:if test="${actualDayOfMonth < 10}">&nbsp;&nbsp;</c:if></strong> <a href="/timetable/add?day=${actualDayOfMonth}&month=${month}&year=${year}">Zabukuj termin</a>
                                    <span class="icon">
                                        <i class="fas fa-scroll"></i>
                                    </span>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p class="help is-danger" style="font-size: 1.25rem"><strong class="">${actualDayOfMonth}.<c:if test="${actualDayOfMonth < 10}">&nbsp;&nbsp;</c:if></strong> Brak wolnych terminów</p>
                                </c:otherwise>
                            </c:choose>

                        </c:if>
                    </td>
                </c:forEach>
            </tr>

            </c:forEach>
        </tbody>
    </table>
    </div>
    <form method="get">
        <div class="buttons is-right">
            <p class="control">
                <button name="shift" value="${shift - 1}" type="submit" class="button ${siteColor} is-inverted">Poprzedni miesiąc</button>
                <button name="shift" value="${shift + 1}" type="submit" class="button ${siteColor} is-inverted">Następny miesiąc</button>
            </p>
        </div>
    </form>
        <section class="section">
            <h1 class="title">Uwaga!</h1>
            <h2 class="subtitle">
                Jeżeli nie ma wolnych terminów danego dnia, zawsze można spróbować kontaktu telefonicznego, albo poprzez Email.
            </h2>
        </section>
</div>
</div>
<c:import url="footer.jsp"/>
</section>
</body>
</html>
