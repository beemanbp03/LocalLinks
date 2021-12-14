
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
  <head>
    <c:import url="/head.jsp" />
    <c:import url="/nav.jsp" />
  </head>
  <body>

    <div class="container-fluid">
      <!-- Golf Course Results -->
      <br />
      <c:forEach var="result" items="${places.results}" varStatus="loop">
        <h2>${result.name}</h2>
        <c:forEach var="item" items="${weather}" varStatus="loop">
          <h2>Hour: ${item.get(loop.index).get("hour")}</h2>
          <p>Temperature: ${item.get(loop.index).get("tempF")}F</p>
          <p>Wind Speed: ${item.get(loop.index).get("windSpeed")}mph</p>
          <p>Will It Rain? ${item.get(loop.index).get("rainYesNo")}</p>
          <p>Precipitation: ${item.get(loop.index).get("precipitation")}in.</p>
          <p>Humidity: ${item.get(loop.index).get("humidity")}%</p>
        </c:forEach>

        <hr />
        <br />
      </c:forEach>
    </div>

  </body>
</html>
