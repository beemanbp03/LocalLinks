package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.weather.Weather;
import util.PropertiesLoader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

/**
 * This DAO handles all Weather api functionality for getting the weather for each Google Places result's location
 */

public class WeatherApiDao implements PropertiesLoader {

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
