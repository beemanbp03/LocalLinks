package entity.google;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Places{

	@JsonProperty("next_page_token")
	private String nextPageToken;

	@JsonProperty("html_attributions")
	private List<Object> htmlAttributions;

	@JsonProperty("results")
	private List<ResultsItem> results;

	@JsonProperty("status")
	private String status;

	public void setNextPageToken(String nextPageToken){
		this.nextPageToken = nextPageToken;
	}

	public String getNextPageToken(){
		return nextPageToken;
	}

	public void setHtmlAttributions(List<Object> htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}

	public List<Object> getHtmlAttributions(){
		return htmlAttributions;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Places{" + 
			"next_page_token = '" + nextPageToken + '\'' + 
			",html_attributions = '" + htmlAttributions + '\'' + 
			",results = '" + results + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}