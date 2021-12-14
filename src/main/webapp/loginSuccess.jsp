<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <title>Local Links</title>
    <meta name="author" content="Boulder Beeman" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <!-- Bootstrap CSS and Javascript-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>

</head>

    <nav>
        <ul>
            <li><a href="home"><<<<< Home (Signed in as: ${sessionScope.userName}</a></li>
        </ul>
    </nav>

    <section>
        <h1 style="color: green;">SUCCESS!!</h1>
        <br>
        <p style="font-size: 32px;">Congrats <span style="color: green;">${sessionScope.userName}</span>! You have successfully logged in.</p>
    </section>


</body>
</html>
