package controller;

import com.google.protobuf.Api;
import entity.ApiResult;
import entity.Favorite;
import entity.HourlyDetails;
import entity.User;
import entity.details.Details;
import entity.geo.*;
import entity.places.Places;
import entity.weather.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import persistence.Dao;
import persistence.SessionFactoryProvider;
import persistence.ApiDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
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
    Dao dao = new Dao();

    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Get all user's Favorites and set it as an attribute to be used on the golfCourseSearch Results Page
        HttpSession session = req.getSession();


        //Retrieve the zip code user input from the request
        String zipCodeParam = req.getParameter("zipCodeSearch");
        int zipCode = Integer.parseInt(zipCodeParam);

        GeoCode geo = null;
        try {
            List<Favorite> favorites = dao.getFavoritesByUserId((User)session.getAttribute("user"));
            geo = apiServiceDao.getLatLng(zipCode);
            List<ResultsItem> geoResults = geo.getResults();
            double lat = geoResults.get(0).getGeometry().getLocation().getLat();
            double lng = geoResults.get(0).getGeometry().getLocation().getLng();

            //Retrieve all the results of the Google Places API call
            Places places = apiServiceDao.getPlaces(Integer.parseInt(req.getParameter("options")), lat, lng);

            List<entity.places.ResultsItem> placesResults = places.getResults();
            ArrayList<ApiResult> resultsArray = getResultsArray(placesResults);

            req.setAttribute("results", resultsArray);
            req.setAttribute("favorites", favorites);


        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("golfCourseResults.jsp");
        dispatcher.forward(req, resp);
    }
    /****** END OF DO/GET METHOD *****/


    public ArrayList<ApiResult> getResultsArray(List<entity.places.ResultsItem> placesResults) throws Exception {

        ArrayList<ApiResult> resultsArray = new ArrayList<ApiResult>();
        /*
         * Loop through the placesResults array, extract the business's information, then run the weather api call,
         * then
         * */

        for (entity.places.ResultsItem item : placesResults) {

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
            double itemLat = item.getGeometry().getLocation().getLat();
            String itemLatString = String.valueOf(itemLat);
            double itemLng = item.getGeometry().getLocation().getLng();
            String itemLngString = String.valueOf(itemLng);
            String rating = String.valueOf(details.getResult().getUserRatingsTotal());

            //Use the fetchWeather method to add everything into an ApiResult object
            ApiResult linksResult = fetchWeather(itemLat, itemLng);
            //Store all variables into the single ApiResult object
            linksResult.setName(name);
            linksResult.setPlace_id(placeId);
            linksResult.setCall(call);
            linksResult.setUrl(url);
            linksResult.setVicinity(vicinity);
            linksResult.setLat(itemLatString);
            linksResult.setLng(itemLngString);
            linksResult.setRating(rating);

            resultsArray.add(linksResult);
        }

        return resultsArray;
    }

    public ApiResult fetchWeather(double itemLat, double itemLng) throws Exception {

        ApiResult linksResult = new ApiResult();
        //Instantiate a Weather entity & make arrays for the hourly details
        Weather itemWeather = apiServiceDao.getWeather(itemLat, itemLng);

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

        linksResult.setHourlyWeather(hourlyList);

        return linksResult;
    }
}
