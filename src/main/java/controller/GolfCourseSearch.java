package controller;

import entity.ApiResult;
import entity.HourlyDetails;
import entity.details.Details;
import entity.geo.*;
import entity.places.Places;
import entity.weather.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.SessionFactoryProvider;
import persistence.ApiDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@WebServlet(
        urlPatterns = {"/golfCourseSearchResults"}
)

/**
 * This servlet searches for golf courses and their weather based on a ZIP code entered by the user
 */
public class GolfCourseSearch extends HttpServlet {

    //Instantiate class variables
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    ApiDao apiServiceDao = new ApiDao();

    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //Establish the array of business results
        List<ApiResult> resultsArray = new ArrayList<ApiResult>();

        //Retrieve the zip code user input from the request
        String zipCodeParam = req.getParameter("zipCodeSearch");
        int zipCode = Integer.parseInt(zipCodeParam);

        GeoCode geo = null;
        try {

            geo = apiServiceDao.getLatLng(zipCode);
            List<ResultsItem> geoResults = geo.getResults();
            double lat = geoResults.get(0).getGeometry().getLocation().getLat();
            double lng = geoResults.get(0).getGeometry().getLocation().getLng();

            //Retrieve all the results of the Google Places API call
            Places places = apiServiceDao.getPlaces(Integer.parseInt(req.getParameter("options")), lat, lng);

            List<entity.places.ResultsItem> placesResults = places.getResults();

            //Create objects for the weatherArray, dailyForecastArray, and the HourlyDetailsMap objects
            int counter = 0;
            /*
            * Loop through the placesResults array, extract the business's information, then run the weather api call,
            * then
            * */
            for (entity.places.ResultsItem item : placesResults) {

                //Instantiate a single result to be displayed on the results page
                ApiResult linksResult = new ApiResult();

                //Instantiate variables for ApiResult.name, direction, and call instance variables
                String placeId = item.getPlaceId();
                Details details = apiServiceDao.getDetails(placeId);
                String name = item.getName();
                String vicinity = String.valueOf(item.getVicinity());
                String urlTemp = details.getResult().getUrl();
                String url;
                if (urlTemp == null) {
                    url = "";
                } else {
                    url = urlTemp;
                }
                String call = details.getResult().getFormattedPhoneNumber();

                //retrieve the latitude and longitude from the item (ResultsItem)
                double itemLat = item.getGeometry().getLocation().getLat();
                double itemLng = item.getGeometry().getLocation().getLng();

                //Instantiate a Weather entity & make arrays for the hourly details

                Weather itemWeather = apiServiceDao.getWeather(itemLat, itemLng);

                // SOURCE how to parse a String containing a date, then format it to only show the hour
                // SOURCE https://stackoverflow.com/questions/3504986/extract-time-from-date-string
                List<HourItem> hourItems = itemWeather.getForecast().getForecastday().get(0).getHour();

                List<HourlyDetails> hourlyList = new ArrayList<HourlyDetails>();
                for (HourItem itemHour : hourItems) {

                    //First, the current hour is retrieved in order to weed out any hours that don't apply
                    Date baseDate = Calendar.getInstance().getTime();

                    HourlyDetails hourlyDetails = new HourlyDetails();

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
                    hourlyDetails.setHour(hour);
                    hourlyDetails.setIcon(icon);
                    hourlyDetails.setWindSpeed(stringWindSpeed);
                    hourlyDetails.setPrecipitation(stringPrecipitation);

                    hourlyList.add(hourlyDetails);

                }
                /****** END OF HOURLY WEATHER LOOP ******/

                //Store all variables into the single ApiResult object
                linksResult.setName(name);
                linksResult.setCall(call);
                linksResult.setUrl(url);
                linksResult.setVicinity(vicinity);
                linksResult.setHourlyWeather(hourlyList);
                resultsArray.add(linksResult);
                counter++;
            }
            /****** END OF RESULT LOOP ******/



            //Set the request attributes
            req.setAttribute("results", resultsArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /****** END OF TRY/CATCH ******/

        RequestDispatcher dispatcher = req.getRequestDispatcher("golfCourseResults.jsp");
        dispatcher.forward(req, resp);
    }
    /****** END OF DO/GET METHOD *****/



}
