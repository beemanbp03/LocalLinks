package entity.weather;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for the Weather API that will be consumed
 */

public class ForecastdayItem{

	@JsonProperty("date")
	private String date;

	@JsonProperty("astro")
	private Astro astro;

	@JsonProperty("date_epoch")
	private int dateEpoch;

	@JsonProperty("hour")
	private List<HourItem> hour;

	@JsonProperty("day")
	private Day day;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setAstro(Astro astro){
		this.astro = astro;
	}

	public Astro getAstro(){
		return astro;
	}

	public void setDateEpoch(int dateEpoch){
		this.dateEpoch = dateEpoch;
	}

	public int getDateEpoch(){
		return dateEpoch;
	}

	public void setHour(List<HourItem> hour){
		this.hour = hour;
	}

	public List<HourItem> getHour(){
		return hour;
	}

	public void setDay(Day day){
		this.day = day;
	}

	public Day getDay(){
		return day;
	}

	@Override
 	public String toString(){
		return 
			"ForecastdayItem{" + 
			"date = '" + date + '\'' + 
			",astro = '" + astro + '\'' + 
			",date_epoch = '" + dateEpoch + '\'' + 
			",hour = '" + hour + '\'' + 
			",day = '" + day + '\'' + 
			"}";
		}
}