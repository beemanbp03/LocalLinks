package controller;

import entity.geo.*;
import entity.google.Places;
import entity.weather.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.GeoCodeDao;
import persistence.SessionFactoryProvider;
import persistence.GoogleApiDao;
import persistence.WeatherApiDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(
        urlPatterns = {"/golfCourseSearchResults"}
)

/**
 * This servlet searches for golf courses and their weather based on a ZIP code entered by the user
 */
public class GolfCourseSearch extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();


    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Initialize the DAOs
        GoogleApiDao googleServiceDao = new GoogleApiDao();
        WeatherApiDao weatherServiceDao = new WeatherApiDao();
        GeoCodeDao geoCodeDao = new GeoCodeDao();

        //Retrieve the zip code user input from the request
        String zipCodeParam = req.getParameter("zipCodeSearch");
        int zipCode = Integer.parseInt(zipCodeParam);

        GeoCode geo = null;
        try {

            geo = geoCodeDao.getLatLng(zipCode);
            List<ResultsItem> geoResults = geo.getResults();
            double lat = geoResults.get(0).getGeometry().getLocation().getLat();
            double lng = geoResults.get(0).getGeometry().getLocation().getLng();

            /*
            What I need to do is get the zip codes of all the Google places results, store them in a list,
            Loop through the list and call the getWeather() method on each item,
            store those weather results in a hashSet or just an array, then send
            that array to the JSP in order to match the correct weather with the correct golf course
            */

            //Retrieve all the results of the Google Places API call
            Places places = googleServiceDao.getPlaces(50, lat, lng);
            List<entity.google.ResultsItem> placesResults = places.getResults();

            //Initialize the array of weather objects that will be
            ArrayList weatherArray = new ArrayList();

            for (entity.google.ResultsItem item : placesResults) {

                //retrieve the latitude and longitude from the item (ResultsItem)
                double itemLat = item.getGeometry().getLocation().getLat();
                double itemLng = item.getGeometry().getLocation().getLng();

                logger.info("Item Latitude: " + itemLat);
                logger.info("Item Longitude: " + itemLng);

                //Instantiate a Weather entity
                Weather itemWeather = weatherServiceDao.getWeather(itemLat, itemLng);
                //TODO itemTemp is somehow retrieving the same latitude and longitude on each pass in this loop, so figure out why that is happening
                /*
                Retrieve the item's temperature in fahrenheit and add it to the weatherArray that will be set as a
                request attribute (req.setAttribute())
                 */
                double itemTemp = itemWeather.getForecast().getForecastday().get(0).getHour().get(5).getTempF();

                logger.info("Item Temperature: " + itemTemp);
                weatherArray.add(itemTemp);

            }

            logger.info("weatherArray: " + weatherArray);
            //Set the request attributes
            req.setAttribute("places", places);
            req.setAttribute("weather", weatherArray);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("golfCourseResults.jsp");
        dispatcher.forward(req, resp);
    }

}
