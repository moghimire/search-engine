package com.goglides.esservice.enums;

public enum QueryFields {
	ID("id"), 
	LISTING_ID("listing_id"),
	TITLE("title"),
	LOGO("logo_name"),
	LOCATION("location"),
	LOCATION_ADDRESS("location.address"),
	LOCATION_CITY("location.city"),
	LOCATION_COUNTRY("location.country"),
	LOCATION_GEOLOCATION("location.geo_location"),
	LOCATION_GEOLOCATION_LAT("location.geo_location.lat"),
	LOCATION_GEOLOCATION_LON("location.geo_location.lon"),	
	DESCRIPTION("description"),
	CATEGORY("category"),
	LISTING_TYPE("listing_type"),
	SLUG("slug"),
	PRICE_PATH("price"),
	PRICE_ID("price.price_id"),
	PRICE_ACTIVITY("price.activity_price"),
	PRICE_TRANSPORTATION("price.transportation_price"),
	PRICE_MULTIMEDIA("price.multimedia_price"),
	DURATION("duration"),
	SCHEDULE("schedule"),
	COMPANY("company"),
	RATING_COUNT("rating_count"),
	RATING("rating"),
	COMPANYNAME("company.name"),
	COMPANYLAT("company.location.geo_location.lat"),
	COMPANYLON("company.location.geo_location.lon")
	;
	

	private String fieldName;

	private QueryFields(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String toString(){
		return this.fieldName;
		
	}
}
