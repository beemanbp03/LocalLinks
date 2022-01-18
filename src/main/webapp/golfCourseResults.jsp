
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
    <div class="row text-center">
      <div>
      <c:if test="${userName != null}">
          <form action="addToFavorites">
              <button class="brn btn-success" type="submit" name="submit" value="addFavorite">ADD ALL SELECTED FAVORITES TO DATABASE</button>
          </form>
      </c:if>
      </div>
    </div>
    <br />

    <!-- Start of Results Section -->
    <div class="row justify-content-center">
      <!-- Individual Results -->

      <!-- RESULT -->
      <c:forEach var="result" items="${results}" varStatus="loop">
        <br />
          <!-- Choosing the size of the result section based off the ability to add it to your favorites -->
          <c:choose>
            <c:when test="${userName != null}">
            <div class="container-fluid row justify-content-between mb-4">
            <div class="rounded border border-5 container-fluid row col-10 justify-content-center">
            </c:when>
            <c:otherwise>
            <div class="container-fluid row justify-content-center mb-4">
            <div class="rounded border border-5 container-fluid row col justify-content-center">
            </c:otherwise>
          </c:choose>

            <div class="row border-bottom">
              <div class="col justify-content-start">
                <h1>${result.name}</h1>
              </div>

              <div class="col text-end">
                <a href="${result.url}">
                  <button type="button" name="getDirections" class="btn btn-outline-success m-2">
                    Directions
                  </button>
                </a>
                <a href="tel:${result.call}">
                  <button type="button" name="getCallLink" class="btn btn-outline-success m-2">
                    Call
                  </button>
                </a>
              </div>
            </div>

            <div class="row">
              <div class="container col-12">
                <div class="row text-center">
                  <c:forEach var="item" items="${result.hourlyWeather}" varStatus="loopCount">
                    <c:if test="${loopCount.index > 6 && loopCount.index < 19}">
                      <div class="col m-1">
                        <i><img alt="picture depicting weather" src="${item.icon}" /></i>
                        <p>${item.hour}</p>
                        <p>${item.windSpeed}mph</p>
                        <p>${item.precipitation}in.</p>
                      </div>
                    </c:if>


                  </c:forEach>
                </div>
              </div>
            </div>
          </div>

          <c:if test="${userName != null}">

              <button class="rounded border border-5 btn btn-outline-success col-2" type="submit" name="submit" value="addToFavorites" >
                <span><i class="fa fa-plus fa-5x"></i></span>
              </button>

          </c:if>

        </div>
      </c:forEach>
      <!-- END RESULT -->
      <br />
      <br />
    </div>
    <!-- END Individual Results -->

  </div>
  </body>
</html>
