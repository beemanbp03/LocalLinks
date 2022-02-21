
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

  <br />

  <!-- Start of Results Section -->


  <c:set var="emptyCheck" value="true" />
  <c:set var="isEmpty" value="${isEmpty}" />


      <!-- Individual Results -->

      <!-- RESULT -->
      <c:forEach var="result" items="${results}" varStatus="loop">
      <br />
    <!-- Choosing the size of the result section based off the ability to add it to your favorites -->
    <c:choose>

        <c:when test="${userName != null}">
        <form id="removeFromFavorites">
        </form>
        <form id="addToFavorites">
        </form>

        <div class="row">
            <div class="rounded border border-4 col-lg-10">
        </c:when>

        <c:otherwise>
        <div class="row">
            <div class="rounded border border-4 col-12 justify-content-center">
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


                  <div class="row d-flex justify-content-between">
                    <c:forEach var="item" items="${result.hourlyWeather}" varStatus="loopCount">
                      <c:if test="${loopCount.index > 6 && loopCount.index < 19}">
                        <div class="col-6 col-sm-4 col-md-3 col-lg-2 col-xxl-1 border">


                                  <div class="text-center">
                                      <c:choose>
                                          <c:when test="${loopCount.index < 12}">
                                              <h5>${item.hour}am</h5>
                                          </c:when>
                                          <c:otherwise>
                                              <h5>${item.hour}pm</h5>
                                          </c:otherwise>
                                      </c:choose>

                                      <i><img alt="picture depicting weather" src="${item.icon}" /></i>
                                  </div>
                                  <div class="text-center text-lg-start">
                                      <p class="border-bottom">Wind: ${item.windSpeed}mph</p>
                                      <p class="border-bottom">Rain: ${item.chanceOfRain}%</p>
                                      <p class="border-bottom">Temp: ${item.temp}F</p>
                                  </div>
                        </div>
                      </c:if>


                    </c:forEach>
                  </div>
            </div>

            <c:if test="${userName != null}">

                <c:set var="favoriteExists" value="${false}" />

                  <c:forEach var="favorite" items="${favorites}" varStatus="loopCount">

                      <c:if test="${favorite.place_id == result.place_id}">
                          <c:set var="favoriteExists" value="${true}" />
                      </c:if>

                  </c:forEach>

                <c:choose>
                    <c:when test="${favoriteExists == true}">
                        <button form="removeFromFavorites" formaction="removeFromFavorites" formmethod="get"
                                class="btn btn-outline-danger rounded border border-4 border-danger col-lg-2" type="submit" name="result"
                                value="${result.place_id}">
                            <span><i class="fa fa-minus fa-5x"></i></span>
                        </button>
                    </c:when>
                    <c:when test="${favoriteExists == false}">
                        <button form="addToFavorites" formaction="addToFavorites" formmethod="get"
                                class="btn btn-outline-success rounded border border-4 border-success col-lg-2" type="submit" name="result"
                                value="${result.place_id} ${result.lat} ${result.lng} ${result.rating}">
                            <span><i class="fa fa-plus fa-5x"></i></span>
                        </button>
                    </c:when>
                </c:choose>


            </c:if>

          </div>
      </c:forEach>
          <!-- END RESULT -->
          <br />
          <br />
        </div>
      </div>
    </div>
      <!-- END Individual Results -->

</body>
</html>
