package entity.details;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeriodsItem{

	@JsonProperty("close")
	private Close close;

	@JsonProperty("open")
	private Open open;

	public void setClose(Close close){
		this.close = close;
	}

	public Close getClose(){
		return close;
	}

	public void setOpen(Open open){
		this.open = open;
	}

	public Open getOpen(){
		return open;
	}

	@Override
 	public String toString(){
		return 
			"PeriodsItem{" + 
			"close = '" + close + '\'' + 
			",open = '" + open + '\'' + 
			"}";
		}
}