package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.geo.GeoCode;
import entity.places.*;
import entity.weather.Weather;
import entity.details.*;
import util.PropertiesLoader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

/**
 * This DAO handles all Google Places API functionality
 */

public class ApiDao implements PropertiesLoader {

    public Places getPlaces(int distance, double lat, double lng) throws Exception {
        Properties properties = loadProperties("/api.properties");
        int distanceInMeters = convertMilesToMeters(distance);
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


}
