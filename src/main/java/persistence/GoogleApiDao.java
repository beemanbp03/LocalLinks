package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.geo.GeoCode;
import entity.google.*;
import util.PropertiesLoader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

/**
 * This DAO handles all Google Places API functionality
 */

public class GoogleApiDao implements PropertiesLoader {

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

    public int convertMilesToMeters(int miles) {
        int metersFromMiles = miles * 1609;
        return metersFromMiles;
    }

}
