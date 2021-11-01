package test.persistence;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import persistence.WebServiceDao;
import entity.google.*;
import entity.People;
import entity.weather.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebServiceDaoTest {

    @Test
    public void getPlacesSuccess() throws Exception {
        WebServiceDao serviceClient = new WebServiceDao();
        Places places = serviceClient.getPlaces();
        List<ResultsItem> results = places.getResults();
        assertEquals("Hickory Grove Golf & Country Club", results.get(0).getName());
    }

    @Test
    public void getWeatherSuccess() throws Exception {
        WebServiceDao serviceClient = new WebServiceDao();
        Weather weather = serviceClient.getWeather();
        String townName = weather.getLocation().getName();
        assertEquals("Fennimore", townName);

    }


}
