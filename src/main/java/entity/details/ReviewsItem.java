package entity.details;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewsItem{

	@JsonProperty("author_name")
	private String authorName;

	@JsonProperty("profile_photo_url")
	private String profilePhotoUrl;

	@JsonProperty("author_url")
	private String authorUrl;

	@JsonProperty("rating")
	private int rating;

	@JsonProperty("language")
	private String language;

	@JsonProperty("text")
	private String text;

	@JsonProperty("time")
	private int time;

	@JsonProperty("relative_time_description")
	private String relativeTimeDescription;

	public void setAuthorName(String authorName){
		this.authorName = authorName;
	}

	public String getAuthorName(){
		return authorName;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl){
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public String getProfilePhotoUrl(){
		return profilePhotoUrl;
	}

	public void setAuthorUrl(String authorUrl){
		this.authorUrl = authorUrl;
	}

	public String getAuthorUrl(){
		return authorUrl;
	}

	public void setRating(int rating){
		this.rating = rating;
	}

	public int getRating(){
		return rating;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setTime(int time){
		this.time = time;
	}

	public int getTime(){
		return time;
	}

	public void setRelativeTimeDescription(String relativeTimeDescription){
		this.relativeTimeDescription = relativeTimeDescription;
	}

	public String getRelativeTimeDescription(){
		return relativeTimeDescription;
	}

	@Override
 	public String toString(){
		return 
			"ReviewsItem{" + 
			"author_name = '" + authorName + '\'' + 
			",profile_photo_url = '" + profilePhotoUrl + '\'' + 
			",author_url = '" + authorUrl + '\'' + 
			",rating = '" + rating + '\'' + 
			",language = '" + language + '\'' + 
			",text = '" + text + '\'' + 
			",time = '" + time + '\'' + 
			",relative_time_description = '" + relativeTimeDescription + '\'' + 
			"}";
		}
}