<!DOCTYPE html>
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <head>
        <c:import url="/head.jsp" />
        <c:import url="/nav.jsp" />
    </head>
    <body>
        <ul>
            <li><a href="home"><<<<< Home (Signed in as: ${sessionScope.userName}</a></li>
        </ul>

        <section>
            <div class="container">
                <h1 style="color: red;">ERROR! STOP!! ERROR!! GO BACK!!</h1>
            </div>
        </section>
        <section></section>
    </body>
</html>
