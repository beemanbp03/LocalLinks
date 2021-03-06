package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class HourlyDetailsMap {

    @JsonProperty("hour")
    private String hour;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("wind_speed")
    private String windSpeed;

    @JsonProperty("precipitation")
    private String precipitation;

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String wind_speed) {
        this.windSpeed = wind_speed;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString(){
        return
                "HourlyDetailsMap{" +
                        "hour = '" + hour + '\'' +
                        "icon = '" + icon + '\'' +
                        "wind_speed = '" + windSpeed + '\'' +
                        "precipitation = '" + precipitation + '\'' +
                        "}";
    }
}
