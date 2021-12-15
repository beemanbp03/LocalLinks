package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiResult {

    @JsonProperty("name")
    private String name;

    @JsonProperty("call")
    private String call;

    @JsonProperty("vicinity")
    private String vicinity;

    @JsonProperty("url")
    private String url;

    @JsonProperty("place_id")
    private String place_id;

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("lng")
    private String lng;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("hourlyWeather")
    private List<HourlyDetails> hourlyWeather;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<HourlyDetails> getHourlyWeather() {
        return hourlyWeather;
    }

    public void setHourlyWeather(List<HourlyDetails> hourlyWeather) {
        this.hourlyWeather = hourlyWeather;
    }

    @Override
    public String toString(){
        return
                "{" +
                        "name = '" + name + '\'' +
                        "call = '" + call + '\'' +
                        "vicinity = '" + vicinity + '\'' +
                        "place_id = '" + place_id + '\'' +
                        "lat = '" + lat + '\'' +
                        "lng = '" + lng + '\'' +
                        "rating = '" + rating + '\'' +
                        "weatherArray = '" + hourlyWeather + '\'' +
                        "}";
    }
}
