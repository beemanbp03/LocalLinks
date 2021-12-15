package entity.places;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity class for the Google Places API
 */

public class ResultsItem{

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("business_status")
	private String businessStatus;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("rating")
	private double rating;

	@JsonProperty("icon_background_color")
	private String iconBackgroundColor;

	@JsonProperty("photos")
	private List<PhotosItem> photos;

	@JsonProperty("reference")
	private String reference;

	@JsonProperty("user_ratings_total")
	private int userRatingsTotal;

	@JsonProperty("price_level")
	private int priceLevel;

	@JsonProperty("scope")
	private String scope;

	@JsonProperty("name")
	private String name;

	@JsonProperty("opening_hours")
	private OpeningHours openingHours;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("icon_mask_base_uri")
	private String iconMaskBaseUri;

	@JsonProperty("vicinity")
	private String vicinity;

	@JsonProperty("plus_code")
	private PlusCode plusCode;

	@JsonProperty("place_id")
	private String placeId;

	@JsonProperty("permanently_closed")
	private boolean permanentlyClosed;

	public void setTypes(List<String> types){
		this.types = types;
	}

	public List<String> getTypes(){
		return types;
	}

	public void setBusinessStatus(String businessStatus){
		this.businessStatus = businessStatus;
	}

	public String getBusinessStatus(){
		return businessStatus;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setIconBackgroundColor(String iconBackgroundColor){
		this.iconBackgroundColor = iconBackgroundColor;
	}

	public String getIconBackgroundColor(){
		return iconBackgroundColor;
	}

	public void setPhotos(List<PhotosItem> photos){
		this.photos = photos;
	}

	public List<PhotosItem> getPhotos(){
		return photos;
	}

	public void setReference(String reference){
		this.reference = reference;
	}

	public String getReference(){
		return reference;
	}

	public void setUserRatingsTotal(int userRatingsTotal){
		this.userRatingsTotal = userRatingsTotal;
	}

	public int getUserRatingsTotal(){
		return userRatingsTotal;
	}

	public void setPriceLevel(int priceLevel){
		this.priceLevel = priceLevel;
	}

	public int getPriceLevel(){
		return priceLevel;
	}

	public void setScope(String scope){
		this.scope = scope;
	}

	public String getScope(){
		return scope;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setOpeningHours(OpeningHours openingHours){
		this.openingHours = openingHours;
	}

	public OpeningHours getOpeningHours(){
		return openingHours;
	}

	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public void setIconMaskBaseUri(String iconMaskBaseUri){
		this.iconMaskBaseUri = iconMaskBaseUri;
	}

	public String getIconMaskBaseUri(){
		return iconMaskBaseUri;
	}

	public void setVicinity(String vicinity){
		this.vicinity = vicinity;
	}

	public String getVicinity(){
		return vicinity;
	}

	public void setPlusCode(PlusCode plusCode){
		this.plusCode = plusCode;
	}

	public PlusCode getPlusCode(){
		return plusCode;
	}

	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	public String getPlaceId(){
		return placeId;
	}

	public void setPermanentlyClosed(boolean permanentlyClosed){
		this.permanentlyClosed = permanentlyClosed;
	}

	public boolean isPermanentlyClosed(){
		return permanentlyClosed;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"types = '" + types + '\'' + 
			",business_status = '" + businessStatus + '\'' + 
			",icon = '" + icon + '\'' + 
			",rating = '" + rating + '\'' + 
			",icon_background_color = '" + iconBackgroundColor + '\'' + 
			",photos = '" + photos + '\'' + 
			",reference = '" + reference + '\'' + 
			",user_ratings_total = '" + userRatingsTotal + '\'' + 
			",price_level = '" + priceLevel + '\'' + 
			",scope = '" + scope + '\'' + 
			",name = '" + name + '\'' + 
			",opening_hours = '" + openingHours + '\'' + 
			",geometry = '" + geometry + '\'' + 
			",icon_mask_base_uri = '" + iconMaskBaseUri + '\'' + 
			",vicinity = '" + vicinity + '\'' + 
			",plus_code = '" + plusCode + '\'' + 
			",place_id = '" + placeId + '\'' + 
			",permanently_closed = '" + permanentlyClosed + '\'' + 
			"}";
		}
}