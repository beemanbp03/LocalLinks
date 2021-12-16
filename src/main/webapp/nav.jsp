<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav justify-content-start">

    <div class="d-inline-flex mt-3 me-2">
        <li class="nav-item ">
            <!-- THIS IS WHERE THE USERNAME and profile button WILL GO WHEN SOMEONE SIGNS IN -->
            <a class="btn btn-outline-success me-1 mt-1" href="home">Home</a>
            <c:if test="${userName != beemanbp03}">
                <a class="btn btn-outline-success me-1 mt-1" href="searchUsers.jsp">Admin</a>
            </c:if>
            <c:if test="${userName != null}">
                <a class="btn btn-outline-success me-1 mt-1" href="profile">Profile</a>
            </c:if>

        </li>

        <c:choose>
            <c:when test="${userName == null}">
                <li class="nav-item">
                    <a class="btn btn-success text-white me-1 mt-1 position-absolute end-0" href="login">Login</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="btn btn-success text-white me-1 mt-1 position-absolute end-1" href="logout">Sign Out [${sessionScope.userName}]</a>
                </li>
            </c:otherwise>
        </c:choose>

    </div>

</ul>
<hr>
