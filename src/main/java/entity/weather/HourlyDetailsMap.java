package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class HourlyDetailsMap {

    @JsonProperty("hour")
    private String hour;

    @JsonProperty("icon")
    private String icon;

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
                        "}";
    }
}
