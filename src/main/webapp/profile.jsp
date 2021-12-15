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
      <h2>Your Profile</h2>
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
          <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.zipCode}</td>
          </tr>
        </tbody>
      </table>

      <br />
      <br />

      <h2>Favorite</h2>
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
        ${favorites}
        <c:forEach var="favorite" items="${favorites}">
          <tr>
            <td>${favorite.id}</td>
            <td>${favorite.name}</td>
            <td>${favorite.address1}</td>
            <td>${favorite.zip_code}</td>
            <td>${favorite.state}</td>
            <td>${favorite.distance}</td>
            <td>${favorite.rating}</td>
            <td>${favorite.user.userName}</td>
          </tr>

        </c:forEach>
        </tbody>
      </table>

    </div>

  </body>
</html>
