package test.persistence;


import org.junit.Test;
import persistence.GoogleApiDao;
import persistence.WeatherApiDao;
import persistence.GeoCodeDao;
import entity.google.*;
import entity.weather.*;
import entity.geo.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WebServiceDaoTest {

    @Test
    public void getPlacesSuccess() throws Exception {
        GoogleApiDao serviceClient = new GoogleApiDao();
        Places places = serviceClient.getPlaces(50);
        List<entity.google.ResultsItem> results = places.getResults();

        //Assertions
        assertEquals("Hickory Grove Golf & Country Club", results.get(0).getName());
    }

    @Test
    public void getWeatherSuccess() throws Exception {
        WeatherApiDao serviceClient = new WeatherApiDao();
        Weather weather = serviceClient.getWeather();
        String townName = weather.getLocation().getName();

        //Assertions
        assertEquals("Fennimore", townName);

    }

    @Test
    public void getLatLongSuccess() throws Exception {
        GeoCodeDao serviceClient = new GeoCodeDao();
        GeoCode geoCode = serviceClient.getLatLong(53805);
        List<entity.geo.ResultsItem> results = geoCode.getResults();

        //Assertions
        assertEquals("43.1337266", String.valueOf(results.get(0).getGeometry().getLocation().getLat()));
        assertEquals("-90.7152749", String.valueOf(results.get(0).getGeometry().getLocation().getLng()));
    }


}
