package controller;

import entity.geo.*;
import entity.google.Places;
import entity.weather.*;
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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;


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

            //Create objects for the weatherArray, dailyForecastArray, and the HourlyDetailsMap objects
            WeatherArray weatherArray = new WeatherArray();
            List<DailyForecast> dailyForecastList = new ArrayList<DailyForecast>();

            for (entity.google.ResultsItem item : placesResults) {

                //retrieve the latitude and longitude from the item (ResultsItem)
                double itemLat = item.getGeometry().getLocation().getLat();
                double itemLng = item.getGeometry().getLocation().getLng();

                //logger.info("Item Latitude: " + itemLat);
                //logger.info("Item Longitude: " + itemLng);

                //Instantiate a Weather entity & make arrays for the hourly details
                Weather itemWeather = weatherServiceDao.getWeather(itemLat, itemLng);


                // SOURCE how to parse a String containing a date, then format it to only show the hour
                // SOURCE https://stackoverflow.com/questions/3504986/extract-time-from-date-string
                List<HourItem> hourItems = itemWeather.getForecast().getForecastday().get(0).getHour();

                DailyForecast daily = new DailyForecast();
                List<HourlyDetailsMap> hourlyList = new ArrayList<HourlyDetailsMap>();

                for (HourItem itemHour : hourItems) {

                    //First, the current hour is retrieved in order to weed out any hours that don't apply
                    Date baseDate = Calendar.getInstance().getTime();

                    HourlyDetailsMap hourlyMap = new HourlyDetailsMap();

                    //Convert and format hour
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(itemHour.getTime());
                    String hour = new SimpleDateFormat("H:mm").format(date);
                    //Retrieve rest of hourly weather details
                    double tempF = itemHour.getTempF();
                    double humidity = itemHour.getHumidity();
                    String condition = itemHour.getCondition().getText();
                    String icon = itemHour.getCondition().getIcon();
                    double windSpeed = itemHour.getWindMph();
                    int rainYesNo = itemHour.getWillItRain();
                    double precipitation = itemHour.getPrecipIn();

                    //Convert all non string details to strings
                    String stringTempF = String.valueOf(tempF);
                    String stringHumidity = String.valueOf(humidity);
                    String stringWindSpeed = String.valueOf(windSpeed);
                    String stringRainYesNo = String.valueOf(rainYesNo);
                    String stringPrecipitation = String.valueOf(precipitation);

                    //Put all string details into the hourlyDetailsMap
                    hourlyMap.setHour(hour);
                    hourlyMap.setIcon(icon);
                    hourlyList.add(hourlyMap);

                }
                /****** END OF HOURLY WEATHER LOOP ******/
                daily.setHourlyDetailsMap(hourlyList);
                dailyForecastList.add(daily);
            }
            /****** END OF SINGLE RESULT LOOP ******/
            weatherArray.setDailyForecastItems(dailyForecastList);

            logger.info("weather: " + weatherArray);

            //Set the request attributes
            req.setAttribute("places", places);
            req.setAttribute("weather", weatherArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /****** END OF TRY/CATCH ******/

        RequestDispatcher dispatcher = req.getRequestDispatcher("golfCourseResults.jsp");
        dispatcher.forward(req, resp);
    }
    /****** END OF DO/GET METHOD *****/

}
