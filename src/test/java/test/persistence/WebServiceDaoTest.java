package test.persistence;


import entity.geo.Geometry;
import entity.geo.Location;
import entity.geo.ResultsItem;
import org.junit.Test;
import persistence.GoogleApiDao;
import persistence.WeatherApiDao;
import persistence.GeoCodeDao;
import entity.google.*;
import entity.weather.*;
import entity.geo.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class runs tests on all three API's we are consuming (Google Geo, Google Places, Weather)
 */
public class WebServiceDaoTest {

    /**
     * This verifies that Google Places API is able to retrieve a Places entity object
     * @throws Exception
     */
    @Test
    public void getPlacesSuccess() throws Exception {
        GoogleApiDao serviceClient = new GoogleApiDao();
        GeoCodeDao geoCodeDao = new GeoCodeDao();

        //Initialize the lat/lng variables and retrieve them from a GeoCodeDao api call
        GeoCode geo = geoCodeDao.getLatLng(35805);
        List<ResultsItem> geoResults = geo.getResults();
        double lat = geoResults.get(0).getGeometry().getLocation().getLat();
        double lng = geoResults.get(0).getGeometry().getLocation().getLng();


        Places places = serviceClient.getPlaces(10, lat, lng);
        List<entity.google.ResultsItem> results = places.getResults();

        //Assertions
        assertEquals("Hickory Grove Golf & Country Club", results.get(0).getName());
    }

    /**
     * This verifies that Weather API is able to retrieve a Weather entity object based off latitude and longitude
     * @throws Exception
     */
    @Test
    public void getWeatherSuccess() throws Exception {
        //initialize DAOs;
        WeatherApiDao serviceClient = new WeatherApiDao();
        GeoCodeDao geoDao = new GeoCodeDao();

        //Retrieve Lattitude and Longitude
        GeoCode geo = geoDao.getLatLng(53805);
        double lat = geo.getResults().get(0).getGeometry().getLocation().getLat();
        double lng = geo.getResults().get(0).getGeometry().getLocation().getLng();

        //Make the weather call using the WeatherApiDao
        Weather weather = serviceClient.getWeather(lat, lng);

        //Set the actual town name
        String townName = weather.getLocation().getName();

        //Assertions
        assertEquals("Boscobel", townName);

    }

    /**
     * This verifies that Google GEO API is able to retrieve the latitude and longitude of a zip code
     * @throws Exception
     */
    @Test
    public void getLatLongSuccess() throws Exception {
        GeoCodeDao serviceClient = new GeoCodeDao();
        GeoCode geoCode = serviceClient.getLatLng(53805);
        List<entity.geo.ResultsItem> results = geoCode.getResults();

        //Assertions
        assertEquals("43.1337266", String.valueOf(results.get(0).getGeometry().getLocation().getLat()));
        assertEquals("-90.7152749", String.valueOf(results.get(0).getGeometry().getLocation().getLng()));
    }


}
