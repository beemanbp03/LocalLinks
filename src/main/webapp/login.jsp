<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 9/14/21
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Login</title>
</head>
<body>

<nav>
    <ul>
        <li><a href="index.jsp">Home</a></li>
        <li><a href="#">Login</a></li>
    </ul>
</nav>
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
