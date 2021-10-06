<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 9/23/21
  Time: 8:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Search for Users</title>

        <!-- Bootstrap CSS and Javascript -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>

    </head>

    <body>
        <h2>User Search</h2>

        <form action="searchUser" class="form-inline">
            <div class="form-group">
                <label for="searchByUserId">User ID</label>
                <input type="radio" id="searchByUserId" name="searchTypeUser" value="id" required/>
                <label for="searchByUsername">User Name</label>
                <input type="radio" id="searchByUsername" name="searchTypeUser" value="user_name" required/>
                <label for="searchByFirstName">First Name</label>
                <input type="radio" id="searchByFirstName" name="searchTypeUser" value="first_name" required/>
                <label for="searchByLastName">Last Name</label>
                <input type="radio" id="searchByLastName" name="searchTypeUser" value="last_name" required/>

                <input type="text" class="form-control" id="searchTermUser" name="searchTermUser" aria-describedby="searchTermHelp" placeholder="enter search term here" required/>
            </div>
            <button type="submit" name="submit" value="searchUser" class="btn btn-primary">Search</button>
            <button type="submit" name="submit" value="searchUserAll" class="btn btn-primary">Search</button>
        </form>

        <br />
        <br />

        <h2>Favorites Search</h2>

        <form action="searchFavorites" class="form-inline">
            <div class="form-group">
                <label for="searchByFavoriteId">Favorite ID</label>
                <input type="radio" id="searchByFavoriteId" name="searchTypeFavorite" value="id" pattern="\d*" required/>
                <label for="searchByUsername">Name</label>
                <input type="radio" id="searchByName" name="searchTypeFavorite" value="name" required/>
                <label for="searchByFirstName">Distance</label>
                <input type="radio" id="searchByDistance" name="searchTypeFavorite" value="distance" required/>
                <label for="searchByLastName">Rating</label>
                <input type="radio" id="searchByRating" name="searchTypeFavorite" value="rating" required/>

                <input type="text" class="form-control" id="searchTermFavorite" name="searchTerm" aria-describedby="searchTermHelp" placeholder="enter search term here" required/>
            </div>
            <button type="submit" name="submit" value="searchFavorite" class="btn btn-primary">Search</button>
            <button type="submit" name="submit" value="searchFavoriteAll" class="btn btn-primary">Search</button>
        </form>
    </body>

</html>
