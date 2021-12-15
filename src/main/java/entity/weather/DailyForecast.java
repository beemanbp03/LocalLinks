package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DailyForecast {

    @JsonProperty("hourlyDetailsMap")
    private List<HourlyDetailsMap> hourlyDetailsMap;

    public List<HourlyDetailsMap> getHourlyDetailsMap() {
        return hourlyDetailsMap;
    }

    public void setHourlyDetailsMap(List<HourlyDetailsMap> hourlyDetailsMap) {
        this.hourlyDetailsMap = hourlyDetailsMap;
    }

    @Override
    public String toString(){
        return
                "DailyForecast{" +
                        "hourlyDetailsMap = '" + hourlyDetailsMap + '\'' +
                        "}";
    }
}
