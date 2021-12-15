package entity.details;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Result{

	@JsonProperty("utc_offset")
	private int utcOffset;

	@JsonProperty("formatted_address")
	private String formattedAddress;

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("website")
	private String website;

	@JsonProperty("business_status")
	private String businessStatus;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("rating")
	private double rating;

	@JsonProperty("icon_background_color")
	private String iconBackgroundColor;

	@JsonProperty("address_components")
	private List<AddressComponentsItem> addressComponents;

	@JsonProperty("photos")
	private List<PhotosItem> photos;

	@JsonProperty("url")
	private String url;

	@JsonProperty("reference")
	private String reference;

	@JsonProperty("user_ratings_total")
	private int userRatingsTotal;

	@JsonProperty("reviews")
	private List<ReviewsItem> reviews;

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

	@JsonProperty("adr_address")
	private String adrAddress;

	@JsonProperty("plus_code")
	private PlusCode plusCode;

	@JsonProperty("formatted_phone_number")
	private String formattedPhoneNumber;

	@JsonProperty("international_phone_number")
	private String internationalPhoneNumber;

	@JsonProperty("place_id")
	private String placeId;

	@JsonProperty("permanently_closed")
	private String permanentlyClosed;

	public void setUtcOffset(int utcOffset){
		this.utcOffset = utcOffset;
	}

	public int getUtcOffset(){
		return utcOffset;
	}

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

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
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

	public void setAddressComponents(List<AddressComponentsItem> addressComponents){
		this.addressComponents = addressComponents;
	}

	public List<AddressComponentsItem> getAddressComponents(){
		return addressComponents;
	}

	public void setPhotos(List<PhotosItem> photos){
		this.photos = photos;
	}

	public List<PhotosItem> getPhotos(){
		return photos;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
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

	public void setReviews(List<ReviewsItem> reviews){
		this.reviews = reviews;
	}

	public List<ReviewsItem> getReviews(){
		return reviews;
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

	public void setAdrAddress(String adrAddress){
		this.adrAddress = adrAddress;
	}

	public String getAdrAddress(){
		return adrAddress;
	}

	public void setPlusCode(PlusCode plusCode){
		this.plusCode = plusCode;
	}

	public PlusCode getPlusCode(){
		return plusCode;
	}

	public void setFormattedPhoneNumber(String formattedPhoneNumber){
		this.formattedPhoneNumber = formattedPhoneNumber;
	}

	public String getFormattedPhoneNumber(){
		return formattedPhoneNumber;
	}

	public void setInternationalPhoneNumber(String internationalPhoneNumber){
		this.internationalPhoneNumber = internationalPhoneNumber;
	}

	public String getInternationalPhoneNumber(){
		return internationalPhoneNumber;
	}

	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	public String getPlaceId(){
		return placeId;
	}

	public String getPermanentlyClosed() {
		return permanentlyClosed;
	}

	public void setPermanentlyClosed(String permanentlyClosed) {
		this.permanentlyClosed = permanentlyClosed;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"utc_offset = '" + utcOffset + '\'' + 
			",formatted_address = '" + formattedAddress + '\'' + 
			",types = '" + types + '\'' + 
			",website = '" + website + '\'' + 
			",business_status = '" + businessStatus + '\'' + 
			",icon = '" + icon + '\'' + 
			",rating = '" + rating + '\'' + 
			",icon_background_color = '" + iconBackgroundColor + '\'' + 
			",address_components = '" + addressComponents + '\'' + 
			",photos = '" + photos + '\'' + 
			",url = '" + url + '\'' + 
			",reference = '" + reference + '\'' + 
			",user_ratings_total = '" + userRatingsTotal + '\'' + 
			",reviews = '" + reviews + '\'' + 
			",name = '" + name + '\'' + 
			",opening_hours = '" + openingHours + '\'' + 
			",geometry = '" + geometry + '\'' + 
			",icon_mask_base_uri = '" + iconMaskBaseUri + '\'' + 
			",vicinity = '" + vicinity + '\'' + 
			",adr_address = '" + adrAddress + '\'' + 
			",plus_code = '" + plusCode + '\'' + 
			",formatted_phone_number = '" + formattedPhoneNumber + '\'' + 
			",international_phone_number = '" + internationalPhoneNumber + '\'' + 
			",place_id = '" + placeId + '\'' +
			",permanently_closed = '" + permanentlyClosed + '\'' +
			"}";
		}
}