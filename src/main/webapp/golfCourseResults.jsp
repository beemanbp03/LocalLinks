<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 9/23/21
  Time: 8:09 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Results Page</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
  </head>

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
