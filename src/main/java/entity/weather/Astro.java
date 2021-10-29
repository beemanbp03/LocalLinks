package entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Astro{

	@JsonProperty("moonset")
	private String moonset;

	@JsonProperty("moon_illumination")
	private String moonIllumination;

	@JsonProperty("sunrise")
	private String sunrise;

	@JsonProperty("moon_phase")
	private String moonPhase;

	@JsonProperty("sunset")
	private String sunset;

	@JsonProperty("moonrise")
	private String moonrise;

	public void setMoonset(String moonset){
		this.moonset = moonset;
	}

	public String getMoonset(){
		return moonset;
	}

	public void setMoonIllumination(String moonIllumination){
		this.moonIllumination = moonIllumination;
	}

	public String getMoonIllumination(){
		return moonIllumination;
	}

	public void setSunrise(String sunrise){
		this.sunrise = sunrise;
	}

	public String getSunrise(){
		return sunrise;
	}

	public void setMoonPhase(String moonPhase){
		this.moonPhase = moonPhase;
	}

	public String getMoonPhase(){
		return moonPhase;
	}

	public void setSunset(String sunset){
		this.sunset = sunset;
	}

	public String getSunset(){
		return sunset;
	}

	public void setMoonrise(String moonrise){
		this.moonrise = moonrise;
	}

	public String getMoonrise(){
		return moonrise;
	}

	@Override
 	public String toString(){
		return 
			"Astro{" + 
			"moonset = '" + moonset + '\'' + 
			",moon_illumination = '" + moonIllumination + '\'' + 
			",sunrise = '" + sunrise + '\'' + 
			",moon_phase = '" + moonPhase + '\'' + 
			",sunset = '" + sunset + '\'' + 
			",moonrise = '" + moonrise + '\'' + 
			"}";
		}
}