<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript" class="init">
    $(document).ready( function() {
      $('#userTable').DataTable();
    } );
</script>
<html>
  <head>
    <c:import url="/head.jsp" />
    <c:import url="/nav.jsp" />
  </head>
  <body>

    <div class="container-fluid">
      <h2>User Results: </h2>
      <!-- USER RESULTS TABLE -->
      <table id="userTable" class="display" cellspacing="0" width="100%">
        <thead>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>ZIP Code</th>
        </thead>
        <tbody>
        <c:choose>
          <c:when test="{not empty users}">
            <c:forEach var="user" items="${users}">
              <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.password}</td>
                <td>${user.email}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.zipCode}</td>
              </tr>

            </c:forEach>
          </c:when>
          <c:otherwise>
            <tr>
              <td>${user.id}</td>
              <td>${user.userName}</td>
              <td>${user.password}</td>
              <td>${user.email}</td>
              <td>${user.firstName}</td>
              <td>${user.lastName}</td>
              <td>${user.zipCode}</td>
            </tr>
          </c:otherwise>
        </c:choose>
        </tbody>
      </table>

      <br />
      <br />

      <h2>Favorite Results: </h2>
      <!-- FAVORITES RESULTS TABLE -->
      <table id="favoriteTable" class="display" cellspacing="0" width="100%">
        <thead>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>ZIP Code</th>
        <th>State</th>
        <th>Distance</th>
        <th>Rating</th>
        <th>Owner Id</th>
        </thead>
        <tbody>
        <c:forEach var="favorite" items="${favorites}">
          <tr>
            <td>${favorite.Id}</td>
            <td>${favorite.name}</td>
            <td>${favorite.address1}</td>
            <td>${favorite.zip_code}</td>
            <td>${favorite.state}</td>
            <td>${favorite.distance}</td>
            <td>${favorite.rating}</td>
            <td>${favorite.user.username}</td>
          </tr>

        </c:forEach>
        </tbody>
      </table>

    </div>

  </body>
</html>
