package controller;

import entity.geo.*;
import entity.google.Places;
import entity.weather.HourItem;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
            ArrayList<ArrayList<HashMap<String, String>>> weatherArray = new ArrayList<ArrayList<HashMap<String, String>>>();

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

                ArrayList<HashMap<String, String>> dailyForecastList = new ArrayList<HashMap<String, String>>();
                for (HourItem itemHour : hourItems) {

                    HashMap<String, String> hourlyDetailsMap = new HashMap<String, String>();

                    //Convert and format hour
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(itemHour.getTime());
                    String hour = new SimpleDateFormat("H:mm").format(date);
                    //Retrieve rest of hourly weather details
                    double tempF = itemHour.getTempF();
                    double humidity = itemHour.getHumidity();
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
                    hourlyDetailsMap.put("hour", hour);
                    hourlyDetailsMap.put("tempF", stringTempF);
                    hourlyDetailsMap.put("humidity", stringHumidity);
                    hourlyDetailsMap.put("windSpeed", stringWindSpeed);
                    hourlyDetailsMap.put("rainYesNo", stringRainYesNo);
                    hourlyDetailsMap.put("precipitation", stringPrecipitation);

                    /*
                    logger.info("\nHour: " + hour + "\n" +
                            "Temp: " + tempF + "\n" +
                            "Humidity: " + humidity + "\n" +
                            "Wind Speed: " + windSpeed + "\n" +
                            "Will It Rain: " + rainYesNo + "\n" +
                            "Precipitation: " + precipitation + "in" + "\n" );

                     */
                    dailyForecastList.add(hourlyDetailsMap);
                }
                weatherArray.add(dailyForecastList);
                logger.info(weatherArray);
            }

            //logger.info("weatherArray: " + weatherArray);
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
