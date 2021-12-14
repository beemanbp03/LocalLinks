<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <c:import url="/head.jsp" />
        <c:import url="/nav.jsp" />
    </head>
    <body>

    <section>
        <div>
            <label for="username" > Username </label>
            <input  type="text" name="username" id="username" />
        </div>
        <div>
            <label> Password </label>
            <input type="text" name="password" />
        </div>

        <button type="submit" name="submit" value="login">Login</button>
    </section>

    </body>
</html>
