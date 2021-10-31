package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Forecast{

	@JsonProperty("current")
	private Current current;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("forecast")
	private Forecast forecast;

	@JsonProperty("forecastday")
	private List<ForecastdayItem> forecastday;

	public void setCurrent(Current current){
		this.current = current;
	}

	public Current getCurrent(){
		return current;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setForecast(Forecast forecast){
		this.forecast = forecast;
	}

	public Forecast getForecast(){
		return forecast;
	}

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
			"current = '" + current + '\'' + 
			",location = '" + location + '\'' + 
			",forecast = '" + forecast + '\'' + 
			",forecastday = '" + forecastday + '\'' + 
			"}";
		}
}