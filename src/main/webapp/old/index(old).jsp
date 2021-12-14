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
        <div class="container">
            <h1>Welcome to Local Links!</h1>
            <h2>For the spontaneous golfer</h2>
            <p>Find golf courses within certain distances from you and check out what the weather looks like </p>
            <form action="golfCourseSearchResults">
                <input type="text" name="zipCodeSearch" id="zipCode" placeholder="Enter ZIP Code" />
                <button type="submit" name="submit" value="Search" >Search</button>
            </form>
        </div>
    </section>
</body>
</html>
