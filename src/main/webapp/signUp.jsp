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
      <h1>Create Account</h1>
    </div>

    <div class="container h-75 mt-5 border border-dark border-3">

      <div class="row h-100 justify-content-center align-items-center">
        <section>
            <div>
                <form class="container">
                  <div class="col-6 justify-content-center mx-auto">
                    <input type="text" name="userName" class="mb-4 form-control text-center" placeholder="username" />
                    <input type="text" name="password" class="mb-4 form-control text-center" placeholder="password" />
                    <input type="text" name="Re-Type Password" class="mb-4 form-control text-center" placeholder="re-type password" />
                    <input type="text" name="firstName" class="mb-4 form-control text-center" placeholder="first name" />
                    <input type="text" name="lastName" class="mb-4 form-control text-center" placeholder="last name" />
                    <input type="text" name="zipCode" class="mb-4 form-control text-center" placeholder="ZIP code" />
                  </div>
                  <div class="d-flex justify-content-center">
                    <button class="btn btn-success" type="submit" name="submit" value="createUser">
                      Create Account
                    </button>
                  </div>

                </form>
            </div>

        </section>
      </div>

    </div>

</body>
</html>
