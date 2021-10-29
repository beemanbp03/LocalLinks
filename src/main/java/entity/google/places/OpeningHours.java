package entity.google.places;

import com.fasterxml.jackson.annotation.JsonProperty;

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