package entity.google;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for the Google Places API
 */

public class OpeningHours{

	@JsonProperty("open_now")
	private boolean openNow;

	public void setOpenNow(boolean openNow){
		this.openNow = openNow;
	}

	public boolean isOpenNow(){
		return openNow;
	}

	@Override
 	public String toString(){
		return 
			"OpeningHours{" + 
			"open_now = '" + openNow + '\'' + 
			"}";
		}
}