package entity.details;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Details{

	@JsonProperty("result")
	private Result result;

	@JsonProperty("html_attributions")
	private List<Object> htmlAttributions;

	@JsonProperty("status")
	private String status;

	public void setResult(Result result){
		this.result = result;
	}

	public Result getResult(){
		return result;
	}

	public void setHtmlAttributions(List<Object> htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}

	public List<Object> getHtmlAttributions(){
		return htmlAttributions;
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
			"Details{" + 
			"result = '" + result + '\'' + 
			",html_attributions = '" + htmlAttributions + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}