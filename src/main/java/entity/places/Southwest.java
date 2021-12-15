package entity.places;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for the Google Places API
 */

public class Southwest{

	@JsonProperty("lng")
	private double lng;

	@JsonProperty("lat")
	private double lat;

	public void setLng(double lng){
		this.lng = lng;
	}

	public double getLng(){
		return lng;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"Southwest{" + 
			"lng = '" + lng + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}