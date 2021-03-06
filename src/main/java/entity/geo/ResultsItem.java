package entity.geo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for geo api
 */

public class ResultsItem{

	@JsonProperty("formatted_address")
	private String formattedAddress;

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("address_components")
	private List<AddressComponentsItem> addressComponents;

	@JsonProperty("place_id")
	private String placeId;

	@JsonProperty("postcode_localities")
	private List<String> postcodeLocalities;

	private void setPostcodeLocalities(List<String> postcodeLocalities) { this.postcodeLocalities = postcodeLocalities; }

	private List<String> getPostcodeLocalities() { return postcodeLocalities; }

	public void setFormattedAddress(String formattedAddress){
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedAddress(){
		return formattedAddress;
	}

	public void setTypes(List<String> types){
		this.types = types;
	}

	public List<String> getTypes(){
		return types;
	}

	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public void setAddressComponents(List<AddressComponentsItem> addressComponents){
		this.addressComponents = addressComponents;
	}

	public List<AddressComponentsItem> getAddressComponents(){
		return addressComponents;
	}

	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	public String getPlaceId(){
		return placeId;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"formatted_address = '" + formattedAddress + '\'' + 
			",types = '" + types + '\'' + 
			",geometry = '" + geometry + '\'' + 
			",address_components = '" + addressComponents + '\'' + 
			",place_id = '" + placeId + '\'' + 
			"}";
		}
}