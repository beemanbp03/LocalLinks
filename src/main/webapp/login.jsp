<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="/head.jsp" />
    <c:import url="/nav.jsp" />
</head>

<body class="container-fluid h-100">

  <ul class="nav justify-content-end">

      <li class="nav-item position-absolute start-0">
        <a class="nav-link" href="index.jsp">Local Links</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="searchUsers.jsp">Search Users</a>
      </li>


      <li class="nav-item">
        <a class="nav-link" href="signup.html">Sign Up</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="login.jsp">Login</a>
      </li>

  </ul>
  <hr>

  <div class="container text-center">
    <h1>Sign In</h1>
  </div>

    <div class="container h-75 border border-dark border-3">

      <div class="row h-100 mt-5 justify-content-center align-items-center">
        <section>
            <div>
                <form class="container">
                  <div class="col-6 justify-content-center mx-auto">
                    <input type="text" name="userName" class="mb-4 form-control text-center" placeholder="username" />
                    <input type="text" name="password" class="mb-4 form-control text-center" placeholder="password" />
                  </div>
                  <div class="d-flex justify-content-center">
                    <button class="btn btn-success" type="submit" name="submit" value="signIn">
                      Sign In
                    </button>
                  </div>

                </form>
            </div>

        </section>
      </div>
    </div>


</body>
</html>
