package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.geo.*;
import util.PropertiesLoader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

/**
 * This DAO handles obtaining the user's latitude and longitude
 */

public class GeoCodeDao implements PropertiesLoader {

    public GeoCode getLatLong(int zipCode) throws Exception {
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

}
