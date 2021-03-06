package entity.weather;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for the Weather API that will be consumed
 */

public class Forecast{

	@JsonProperty("forecastday")
	private List<ForecastdayItem> forecastday;

	public void setForecastday(List<ForecastdayItem> forecastday){
		this.forecastday = forecastday;
	}

	public List<ForecastdayItem> getForecastday(){
		return forecastday;
	}

	@Override
 	public String toString(){
		return 
			"Forecast{" + 
			"forecastday = '" + forecastday + '\'' + 
			"}";
		}
}