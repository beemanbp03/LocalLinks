package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ApiResult;
import entity.HourlyDetails;
import entity.geo.GeoCode;
import entity.places.*;
import entity.weather.HourItem;
import entity.weather.Weather;
import entity.details.*;
import util.PropertiesLoader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This DAO handles all Google Places API functionality
 */

public class ApiDao implements PropertiesLoader {

    public Places getPlaces(int distance, double lat, double lng) throws Exception {
        Properties properties = loadProperties("/api.properties");
        String distanceInMeters = String.valueOf(convertMilesToMeters(distance));
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lat + "," + lng + "&radius=" + distanceInMeters + "&keyword=public%20golf%20course&key=" + properties.getProperty("google.key"));
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Places places = null;
        try {
            places = mapper.readValue(response, Places.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return places;
    }

    /**
     * This DAO handles all Google Details API functionality
     */

    public Details getDetails(String placeIdParam) throws Exception {
        Properties properties = loadProperties("/api.properties");
        String placeId = placeIdParam;
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://maps.googleapis.com/maps/api/place/details/json?place_id=" + placeId + "&key=" + properties.getProperty("google.key"));
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Details responseToReturn = null;
        try {
            responseToReturn = mapper.readValue(response, Details.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return responseToReturn;
    }

    public int convertMilesToMeters(int miles) {
        int metersFromMiles = miles * 1609;
        return metersFromMiles;
    }

    /****** GEO CODE API CALLS ******/
    public GeoCode getLatLng(int zipCode) throws Exception {
        Properties properties = loadProperties("/api.properties");
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://maps.googleapis.com/maps/api/geocode/json?key=" + properties.getProperty("google.key") + "&components=postal_code:" + zipCode);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        GeoCode geoCode = null;
        try {
            geoCode = mapper.readValue(response, GeoCode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return geoCode;
    }

    /****** WEATHER API CALLS ******/
    /**
     * This method gets the weather based off latitude and longitude coordinates
     * @return Weather entity object under java/entity/weather
     * @throws Exception
     */
    public Weather getWeather(double lat, double lng) throws Exception{
        Properties properties = loadProperties("/api.properties");
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://api.weatherapi.com/v1/forecast.json?key=" + properties.getProperty("weather.key") + "&q=" + lat + "," + lng + "&days=1&aqi=no&alerts=no");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = mapper.readValue(response, Weather.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return weather;
    }

    /*********************************************Golf Course Results**************************************************/

    /**
     *
     * @param placesResults
     * @return
     * @throws Exception
     */
    public ArrayList<ApiResult> getResultsArray(List<ResultsItem> placesResults) throws Exception {

        ArrayList<ApiResult> resultsArray = new ArrayList<ApiResult>();
        /*
         * Loop through the placesResults array, extract the business's information, then run the weather api call,
         * then
         * */

        for (entity.places.ResultsItem item : placesResults) {

            //Instantiate variables for ApiResult.name, direction, and call instance variables
            String placeId = item.getPlaceId();
            Details details = getDetails(placeId);
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
        Weather itemWeather = getWeather(itemLat, itemLng);

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

