package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for the Weather API that will be consumed
 */

public class Weather{

	@JsonProperty("current")
	private Current current;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("forecast")
	private Forecast forecast;

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

	@Override
 	public String toString(){
		return 
			"Weather{" + 
			"current = '" + current + '\'' + 
			",location = '" + location + '\'' + 
			",forecast = '" + forecast + '\'' + 
			"}";
		}
}