package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.google.*;
import entity.weather.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class WebServiceDao {

    public Places getPlaces() {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://maps.googleapis.com/maps/api/place/textsearch/json?location=42.97946295530269,-90.6501901218642&query=golf%20course&radius=1000&key=AIzaSyCZjCGg1YlbpjORMLeNB7SWd02X0XQq5uE");
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

    public Weather getWeather() {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://api.weatherapi.com/v1/forecast.json?key=358f00cc60094145931155832212910&q=53809&days=1&aqi=no&alerts=no");
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
