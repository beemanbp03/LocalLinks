package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for the Weather API that will be consumed
 */

public class Current{

	@JsonProperty("feelslike_c")
	private double feelslikeC;

	@JsonProperty("uv")
	private double uv;

	@JsonProperty("last_updated")
	private String lastUpdated;

	@JsonProperty("feelslike_f")
	private double feelslikeF;

	@JsonProperty("wind_degree")
	private int windDegree;

	@JsonProperty("last_updated_epoch")
	private int lastUpdatedEpoch;

	@JsonProperty("is_day")
	private int isDay;

	@JsonProperty("precip_in")
	private double precipIn;

	@JsonProperty("wind_dir")
	private String windDir;

	@JsonProperty("gust_mph")
	private double gustMph;

	@JsonProperty("temp_c")
	private double tempC;

	@JsonProperty("pressure_in")
	private double pressureIn;

	@JsonProperty("gust_kph")
	private double gustKph;

	@JsonProperty("temp_f")
	private double tempF;

	@JsonProperty("precip_mm")
	private double precipMm;

	@JsonProperty("cloud")
	private int cloud;

	@JsonProperty("wind_kph")
	private double windKph;

	@JsonProperty("condition")
	private Condition condition;

	@JsonProperty("wind_mph")
	private double windMph;

	@JsonProperty("vis_km")
	private double visKm;

	@JsonProperty("humidity")
	private int humidity;

	@JsonProperty("pressure_mb")
	private double pressureMb;

	@JsonProperty("vis_miles")
	private double visMiles;

	public void setFeelslikeC(double feelslikeC){
		this.feelslikeC = feelslikeC;
	}

	public double getFeelslikeC(){
		return feelslikeC;
	}

	public void setUv(double uv){
		this.uv = uv;
	}

	public double getUv(){
		return uv;
	}

	public void setLastUpdated(String lastUpdated){
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdated(){
		return lastUpdated;
	}

	public void setFeelslikeF(double feelslikeF){
		this.feelslikeF = feelslikeF;
	}

	public double getFeelslikeF(){
		return feelslikeF;
	}

	public void setWindDegree(int windDegree){
		this.windDegree = windDegree;
	}

	public int getWindDegree(){
		return windDegree;
	}

	public void setLastUpdatedEpoch(int lastUpdatedEpoch){
		this.lastUpdatedEpoch = lastUpdatedEpoch;
	}

	public int getLastUpdatedEpoch(){
		return lastUpdatedEpoch;
	}

	public void setIsDay(int isDay){
		this.isDay = isDay;
	}

	public int getIsDay(){
		return isDay;
	}

	public void setPrecipIn(double precipIn){
		this.precipIn = precipIn;
	}

	public double getPrecipIn(){
		return precipIn;
	}

	public void setWindDir(String windDir){
		this.windDir = windDir;
	}

	public String getWindDir(){
		return windDir;
	}

	public void setGustMph(double gustMph){
		this.gustMph = gustMph;
	}

	public double getGustMph(){
		return gustMph;
	}

	public void setTempC(double tempC){
		this.tempC = tempC;
	}

	public double getTempC(){
		return tempC;
	}

	public void setPressureIn(double pressureIn){
		this.pressureIn = pressureIn;
	}

	public double getPressureIn(){
		return pressureIn;
	}

	public void setGustKph(double gustKph){
		this.gustKph = gustKph;
	}

	public double getGustKph(){
		return gustKph;
	}

	public void setTempF(double tempF){
		this.tempF = tempF;
	}

	public double getTempF(){
		return tempF;
	}

	public void setPrecipMm(double precipMm){
		this.precipMm = precipMm;
	}

	public double getPrecipMm(){
		return precipMm;
	}

	public void setCloud(int cloud){
		this.cloud = cloud;
	}

	public int getCloud(){
		return cloud;
	}

	public void setWindKph(double windKph){
		this.windKph = windKph;
	}

	public double getWindKph(){
		return windKph;
	}

	public void setCondition(Condition condition){
		this.condition = condition;
	}

	public Condition getCondition(){
		return condition;
	}

	public void setWindMph(double windMph){
		this.windMph = windMph;
	}

	public double getWindMph(){
		return windMph;
	}

	public void setVisKm(double visKm){
		this.visKm = visKm;
	}

	public double getVisKm(){
		return visKm;
	}

	public void setHumidity(int humidity){
		this.humidity = humidity;
	}

	public int getHumidity(){
		return humidity;
	}

	public void setPressureMb(double pressureMb){
		this.pressureMb = pressureMb;
	}

	public double getPressureMb(){
		return pressureMb;
	}

	public void setVisMiles(double visMiles){
		this.visMiles = visMiles;
	}

	public double getVisMiles(){
		return visMiles;
	}

	@Override
 	public String toString(){
		return 
			"Current{" + 
			"feelslike_c = '" + feelslikeC + '\'' + 
			",uv = '" + uv + '\'' + 
			",last_updated = '" + lastUpdated + '\'' + 
			",feelslike_f = '" + feelslikeF + '\'' + 
			",wind_degree = '" + windDegree + '\'' + 
			",last_updated_epoch = '" + lastUpdatedEpoch + '\'' + 
			",is_day = '" + isDay + '\'' + 
			",precip_in = '" + precipIn + '\'' + 
			",wind_dir = '" + windDir + '\'' + 
			",gust_mph = '" + gustMph + '\'' + 
			",temp_c = '" + tempC + '\'' + 
			",pressure_in = '" + pressureIn + '\'' + 
			",gust_kph = '" + gustKph + '\'' + 
			",temp_f = '" + tempF + '\'' + 
			",precip_mm = '" + precipMm + '\'' + 
			",cloud = '" + cloud + '\'' + 
			",wind_kph = '" + windKph + '\'' + 
			",condition = '" + condition + '\'' + 
			",wind_mph = '" + windMph + '\'' + 
			",vis_km = '" + visKm + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",pressure_mb = '" + pressureMb + '\'' + 
			",vis_miles = '" + visMiles + '\'' + 
			"}";
		}
}