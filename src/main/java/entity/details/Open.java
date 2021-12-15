package entity.details;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Open{

	@JsonProperty("time")
	private String time;

	@JsonProperty("day")
	private int day;

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setDay(int day){
		this.day = day;
	}

	public int getDay(){
		return day;
	}

	@Override
 	public String toString(){
		return 
			"Open{" + 
			"time = '" + time + '\'' + 
			",day = '" + day + '\'' + 
			"}";
		}
}