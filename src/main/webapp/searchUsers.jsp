<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="/head.jsp" />
    <c:import url="/nav.jsp" />
</head>

    <body>

      <h2>Search By: User</h2>

      <form action="searchUser" class="form-inline">
          <div class="form-group">
              <label for="searchByUserId">User ID</label>
              <input type="radio" id="searchByUserId" name="searchTypeUser" value="id" />
              <label for="searchByUsername">User Name</label>
              <input type="radio" id="searchByUsername" name="searchTypeUser" value="user_name" />
              <label for="searchByFirstName">First Name</label>
              <input type="radio" id="searchByFirstName" name="searchTypeUser" value="first_name" />
              <label for="searchByLastName">Last Name</label>
              <input type="radio" id="searchByLastName" name="searchTypeUser" value="last_name" />

              <input type="text" class="form-control" id="searchTermUser" name="searchTermUser" aria-describedby="searchTermHelp" placeholder="enter search term here" />
          </div>
          <button type="submit" name="submit" value="searchUser" class="btn btn-primary">Search</button>
          <button type="submit" name="submit" value="searchUserAll" class="btn btn-primary">Search All Users</button>
      </form>

        <br />
        <br />

      <h2>Search By: Favorites</h2>

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
