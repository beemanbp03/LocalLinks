package entity.places;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for the Google Places API
 */

public class PlusCode{

	@JsonProperty("compound_code")
	private String compoundCode;

	@JsonProperty("global_code")
	private String globalCode;

	public void setCompoundCode(String compoundCode){
		this.compoundCode = compoundCode;
	}

	public String getCompoundCode(){
		return compoundCode;
	}

	public void setGlobalCode(String globalCode){
		this.globalCode = globalCode;
	}

	public String getGlobalCode(){
		return globalCode;
	}

	@Override
 	public String toString(){
		return 
			"PlusCode{" + 
			"compound_code = '" + compoundCode + '\'' + 
			",global_code = '" + globalCode + '\'' + 
			"}";
		}
}