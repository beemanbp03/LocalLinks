
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
      <h1 class="col">Results</h1>
    </div>
    <br />

    <!-- Start of Results Section -->
    <div class="row justify-content-center">
      <!-- Individual Results -->

      <!-- RESULT -->
      <c:forEach var="result" items="${places.results}" varStatus="loop">
        <br />
        <div class="container-fluid row justify-content-between mb-4">
          <div class="rounded border border-5 container-fluid row col-10 justify-content-center">

            <div class="row border-bottom">
              <div class="col justify-content-start">
                <h1>${result.name}</h1>
              </div>
              <div class="col text-end">
                <button type="button" name="getDirections" class="btn btn-outline-success m-2">
                  Directions
                </button>
                <button type="button" name="getCallLink" class="btn btn-outline-success m-2">
                  Call
                </button>
              </div>
            </div>

            <div class="row">
              <div class="container col-12">
                <div class="row text-center">
                  <c:forEach var="item" items="${weather}" varStatus="loop">

                    <div class="col m-1">
                      <i><img src="${item.get(loop.index).get("icon")}" /></i>
                      <p>${item.get(loop.index).get("condition")}</p>
                      <p>${item.get(loop.index).get("hour")}</p>
                    </div>

                  </c:forEach>
                </div>
              </div>
            </div>
          </div>

          <button class="rounded border border-5 btn btn-outline-success col-2" type="button" name="addToFavorites">
            <span><i class="fa fa-plus fa-5x"></i></span>
          </button>

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
