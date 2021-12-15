package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for the Weather API that will be consumed
 */

public class WeatherArray{

    @JsonProperty("dailyForecastItems")
    private List<DailyForecast> dailyForecastItems;

    public void setDailyForecastItems(List<DailyForecast> dailyForecastItems){
        this.dailyForecastItems = dailyForecastItems;
    }

    public List<DailyForecast> getDailyForecastItems(){
        return dailyForecastItems;
    }


    @Override
    public String toString(){
        return
                "WeatherArray{" +
                        "dailyForecastItems = '" + dailyForecastItems + '\'' +
                        "}";
    }
}