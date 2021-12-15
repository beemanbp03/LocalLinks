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
                        "weatherArray = '" + hourlyWeather + '\'' +
                        "}";
    }
}
