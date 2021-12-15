package entity.details;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpeningHours{

	@JsonProperty("open_now")
	private boolean openNow;

	@JsonProperty("periods")
	private List<PeriodsItem> periods;

	@JsonProperty("weekday_text")
	private List<String> weekdayText;

	public void setOpenNow(boolean openNow){
		this.openNow = openNow;
	}

	public boolean isOpenNow(){
		return openNow;
	}

	public void setPeriods(List<PeriodsItem> periods){
		this.periods = periods;
	}

	public List<PeriodsItem> getPeriods(){
		return periods;
	}

	public void setWeekdayText(List<String> weekdayText){
		this.weekdayText = weekdayText;
	}

	public List<String> getWeekdayText(){
		return weekdayText;
	}

	@Override
 	public String toString(){
		return 
			"OpeningHours{" + 
			"open_now = '" + openNow + '\'' + 
			",periods = '" + periods + '\'' + 
			",weekday_text = '" + weekdayText + '\'' + 
			"}";
		}
}