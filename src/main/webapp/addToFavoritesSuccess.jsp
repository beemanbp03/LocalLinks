<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <head>
        <c:import url="/head.jsp" />
        <c:import url="/nav.jsp" />
    </head>

    <body>
        <a href="home"><<<<< Home (Signed in as: ${sessionScope.userName}</a></li>

        <section>
            <h1 style="color: green;">SUCCESS!!</h1>
            <br>
            <p style="font-size: 32px;">Congrats <span style="color: green;">${sessionScope.userName}</span>! You have successfully logged in.</p>
        </section>
    </body>
</html>
