<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="/head.jsp" />
    <c:import url="/nav.jsp" />
</head>

<body>

    <div class="container-fluid h-100">

      <div class="row h-100 justify-content-center align-items-center">

          <section>
              <div class="d-flex justify-content-center">
                  <h1>
                      <img class="img-fluid" src="local-links-logo.png" alt="Local Links Logo"/>
                  </h1>
              </div>
          </section>
        <section>
            <div>
                <form action="golfCourseSearchResults" class="container">
                  <div class="col-xs-12 col-md-8 col-lg-6 btn-group btn-group-toggle d-flex justify-content-center mx-auto" data-toggle="buttons">
                    <label class="btn btn-success active">
                      <input type="radio" name="options" id="50miles" value="50" autocomplete="off" checked> 50 Miles
                    </label>
                    <label class="btn btn-success active">
                      <input type="radio" name="options" id="100miles" value="100" autocomplete="off" > 100 Miles
                    </label>
                    <label class="btn btn-success active">
                      <input type="radio" name="options" id="150miles" value="150" autocomplete="off" > 150 Miles
                    </label>
                  </div>

                  <br />

                  <div class="d-flex justify-content-center">
                    <input id="zipCode" class="mt-4 mb-4 form-control text-center " style="width: 10em;" type="text" name="zipCodeSearch" placeholder="Enter ZIP Code" />
                    <button class="mt-4 mb-4 btn btn-outline-success rounded" type="submit" name="submit" value="Search">
                      Search
                    </button>
                  </div>

                  <div class="d-flex justify-content-center">
                  <!-- CURRENT LOCATION BUTTON -->>
                  </div>

                </form>
            </div>

        </section>
      </div>

    </div>

</body>
</html>
