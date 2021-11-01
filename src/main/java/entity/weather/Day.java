package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Day{

	@JsonProperty("avgvis_km")
	private double avgvisKm;

	@JsonProperty("uv")
	private double uv;

	@JsonProperty("avgtemp_f")
	private double avgtempF;

	@JsonProperty("avgtemp_c")
	private double avgtempC;

	@JsonProperty("daily_chance_of_snow")
	private int dailyChanceOfSnow;

	@JsonProperty("maxtemp_c")
	private double maxtempC;

	@JsonProperty("maxtemp_f")
	private double maxtempF;

	@JsonProperty("mintemp_c")
	private double mintempC;

	@JsonProperty("avgvis_miles")
	private double avgvisMiles;

	@JsonProperty("daily_will_it_rain")
	private int dailyWillItRain;

	@JsonProperty("mintemp_f")
	private double mintempF;

	@JsonProperty("totalprecip_in")
	private double totalprecipIn;

	@JsonProperty("avghumidity")
	private double avghumidity;

	@JsonProperty("condition")
	private Condition condition;

	@JsonProperty("maxwind_kph")
	private double maxwindKph;

	@JsonProperty("maxwind_mph")
	private double maxwindMph;

	@JsonProperty("daily_chance_of_rain")
	private int dailyChanceOfRain;

	@JsonProperty("totalprecip_mm")
	private double totalprecipMm;

	@JsonProperty("daily_will_it_snow")
	private int dailyWillItSnow;

	public void setAvgvisKm(double avgvisKm){
		this.avgvisKm = avgvisKm;
	}

	public double getAvgvisKm(){
		return avgvisKm;
	}

	public void setUv(double uv){
		this.uv = uv;
	}

	public double getUv(){
		return uv;
	}

	public void setAvgtempF(double avgtempF){
		this.avgtempF = avgtempF;
	}

	public double getAvgtempF(){
		return avgtempF;
	}

	public void setAvgtempC(double avgtempC){
		this.avgtempC = avgtempC;
	}

	public double getAvgtempC(){
		return avgtempC;
	}

	public void setDailyChanceOfSnow(int dailyChanceOfSnow){
		this.dailyChanceOfSnow = dailyChanceOfSnow;
	}

	public int getDailyChanceOfSnow(){
		return dailyChanceOfSnow;
	}

	public void setMaxtempC(double maxtempC){
		this.maxtempC = maxtempC;
	}

	public double getMaxtempC(){
		return maxtempC;
	}

	public void setMaxtempF(double maxtempF){
		this.maxtempF = maxtempF;
	}

	public double getMaxtempF(){
		return maxtempF;
	}

	public void setMintempC(double mintempC){
		this.mintempC = mintempC;
	}

	public double getMintempC(){
		return mintempC;
	}

	public void setAvgvisMiles(double avgvisMiles){
		this.avgvisMiles = avgvisMiles;
	}

	public double getAvgvisMiles(){
		return avgvisMiles;
	}

	public void setDailyWillItRain(int dailyWillItRain){
		this.dailyWillItRain = dailyWillItRain;
	}

	public int getDailyWillItRain(){
		return dailyWillItRain;
	}

	public void setMintempF(double mintempF){
		this.mintempF = mintempF;
	}

	public double getMintempF(){
		return mintempF;
	}

	public void setTotalprecipIn(double totalprecipIn){
		this.totalprecipIn = totalprecipIn;
	}

	public double getTotalprecipIn(){
		return totalprecipIn;
	}

	public void setAvghumidity(double avghumidity){
		this.avghumidity = avghumidity;
	}

	public double getAvghumidity(){
		return avghumidity;
	}

	public void setCondition(Condition condition){
		this.condition = condition;
	}

	public Condition getCondition(){
		return condition;
	}

	public void setMaxwindKph(double maxwindKph){
		this.maxwindKph = maxwindKph;
	}

	public double getMaxwindKph(){
		return maxwindKph;
	}

	public void setMaxwindMph(double maxwindMph){
		this.maxwindMph = maxwindMph;
	}

	public double getMaxwindMph(){
		return maxwindMph;
	}

	public void setDailyChanceOfRain(int dailyChanceOfRain){
		this.dailyChanceOfRain = dailyChanceOfRain;
	}

	public int getDailyChanceOfRain(){
		return dailyChanceOfRain;
	}

	public void setTotalprecipMm(double totalprecipMm){
		this.totalprecipMm = totalprecipMm;
	}

	public double getTotalprecipMm(){
		return totalprecipMm;
	}

	public void setDailyWillItSnow(int dailyWillItSnow){
		this.dailyWillItSnow = dailyWillItSnow;
	}

	public int getDailyWillItSnow(){
		return dailyWillItSnow;
	}

	@Override
 	public String toString(){
		return 
			"Day{" + 
			"avgvis_km = '" + avgvisKm + '\'' + 
			",uv = '" + uv + '\'' + 
			",avgtemp_f = '" + avgtempF + '\'' + 
			",avgtemp_c = '" + avgtempC + '\'' + 
			",daily_chance_of_snow = '" + dailyChanceOfSnow + '\'' + 
			",maxtemp_c = '" + maxtempC + '\'' + 
			",maxtemp_f = '" + maxtempF + '\'' + 
			",mintemp_c = '" + mintempC + '\'' + 
			",avgvis_miles = '" + avgvisMiles + '\'' + 
			",daily_will_it_rain = '" + dailyWillItRain + '\'' + 
			",mintemp_f = '" + mintempF + '\'' + 
			",totalprecip_in = '" + totalprecipIn + '\'' + 
			",avghumidity = '" + avghumidity + '\'' + 
			",condition = '" + condition + '\'' + 
			",maxwind_kph = '" + maxwindKph + '\'' + 
			",maxwind_mph = '" + maxwindMph + '\'' + 
			",daily_chance_of_rain = '" + dailyChanceOfRain + '\'' + 
			",totalprecip_mm = '" + totalprecipMm + '\'' + 
			",daily_will_it_snow = '" + dailyWillItSnow + '\'' + 
			"}";
		}
}