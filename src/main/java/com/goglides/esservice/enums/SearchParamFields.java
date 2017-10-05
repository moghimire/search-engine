package com.goglides.esservice.enums;

public enum SearchParamFields {

	/*{
	    "search_params": {
	        "keyword": "paragliding",
	        "from_date": "",
	        "to_date": "",
	        "no_of_guests": "2",
	        "price_range_min": "40.00",
	        "price_range_max": "160.00",
	        "price": "",
	        "schedule": "10:00am",
	        "duration": "30",
	        "rating": "",
	        "nationality": "nepali",
	        "location": "pokhara",
	        "latitude": "",
	        "longitude": "",
	        "neighbourhood": "",
	        "age_group": "adult",
	        "listing_type" : "tandem"       
	    }
	}*/
	KEYWORD("keyword"), 
	FROM_DATE("from_date"),
	TO_DATE("to_date"),
	NO_OF_GUESTS("no_of_guests"),
	PRICE_RANGE_MIN("price_range_min"),
	PRICE_RANGE_MAX("price_range_max"),
	PRICE("price"),
	SCHEDULE("schedule"),
	DURATION("duration"),
	RATING("rating"),
	NATIONALITY("nationality"),
	LOCATION("location"),
	LATITUDE("latitude"),	
	LONGITUDE("longitude"),
	NEIGHBOURHOOD("neighbourhood"),
	AGE_GROUP("age_group"),
	LISTING_TYPE("listing_type"),
	PAGE_NO("pageno"),
	SORT_BY("sortby"),
	SORT_ORDER("sortorder"),
	SEARCH_MAP("searchmap"),
	TOPLEFTLAT("topleftlat"),
	TOPLEFTLON("topleftlon"),
	BOTRIGHTLAT("bottomrightlat"),
	BOTRIGHTLON("bottomrightlon")
	;

	private String fieldName;

	private SearchParamFields(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String toString(){
		return this.fieldName;
		
	}
}
