package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HourlyDetails {

    @JsonProperty("hour")
    private String hour;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("windSpeed")
    private String windSpeed;

    @JsonProperty("precipitation")
    private String precipitation;

    @JsonProperty("chanceOfRain")
    private String chanceOfRain;

    @JsonProperty("temp")
    private String temp;

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

    public String getChanceOfRain() { return chanceOfRain; }

    public void setChanceOfRain(String chanceOfRain) { this.chanceOfRain = chanceOfRain; }

    public String getTemp() { return temp; }

    public void setTemp(String temp) { this.temp = temp; }

    @Override
    public String toString(){
        return
                "{" +
                        "hour = '" + hour + '\'' +
                        "icon = '" + icon + '\'' +
                        "windSpeed = '" + windSpeed + '\'' +
                        "precipitation = '" + precipitation + '\'' +
                        "chanceOfRain = '" + chanceOfRain + '\'' +
                        "temp = '" + temp + '\'' +
                        "}";
    }
}
