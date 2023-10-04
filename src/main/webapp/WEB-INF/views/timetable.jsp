<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 02.10.2023
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="background-color: #F5F5F5">
<c:import url="header.jsp"/>
<body>
<c:import url="nav-bar.jsp"/>

<div class="container-fluid">
    <section class="section">
        <h1 class="title">${monthName}</h1>
        <h2 class="subtitle">
            Jeżeli w polu jest tekst na czerwono, oznacza to, że tego dnia już wolnych terminów.
        </h2>
    </section>
    <table class="table is-bordered is-striped is-hoverable is-fullwidth">
        <thead style="background-color: #001F3F">
        <tr>
            <th class="has-text-light">Poniedziałek</th>
            <th class="has-text-light">Wtorek</th>
            <th class="has-text-light">Środa</th>
            <th class="has-text-light">Czwartek</th>
            <th class="has-text-light">Piątek</th>
            <th class="has-text-light">Sobota</th>
            <th class="has-text-light">Niedziela</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach begin="0" end="${weeks - 1}" varStatus="i"> <%-- TODO napraw to wyświetlanie!! --%>
            <tr>
                <c:forEach begin="0" end="6" varStatus="j">
                    <c:set var="actualDayOfMonth" value="${((i.index) * 7) + (j.index + 1) - firstDayOfMonth + 1}"/>
                    <td>
                        <c:if test="${actualDayOfMonth > 0 && actualDayOfMonth <= lastDayOfMonth}">
                            <c:choose>
                                <c:when test="${!allUnavailable.get(actualDayOfMonth - 1)}">
                                    <p class="help is-success" style="font-size: 1.25rem"><strong class="">${actualDayOfMonth}.<c:if test="${actualDayOfMonth < 10}">&nbsp;&nbsp;</c:if></strong> <a href="/timetable/add?day=${actualDayOfMonth}&month=${month}&year=${year}">Zabukuj termin</a>
                                    <span class="icon">
                                        <i class="fas fa-calendar-check"></i>
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
    <form method="get">
        <div class="field is-grouped is-grouped-right">
            <p class="control">
                <button name="shift" value="${shift - 1}" type="submit" class="button is-link">Poprzedni miesiąc</button>
                <button name="shift" value="${shift + 1}" type="submit" class="button is-link">Następny miesiąc</button>
            </p>
        </div>
    </form>
        <section class="section">
            <h1 class="title">Uwaga!</h1>
            <h2 class="subtitle">
                Jeżeli nie ma wolnych terminów danego dnia, zawsze można spróbować kontaktu telefonicznego. Może jednak znajdę chwilkę czasu. ;)
            </h2>
        </section>

</div>

</body>
</html>
