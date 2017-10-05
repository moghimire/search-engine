package com.goglides.esservice.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchParam {

	private int pageNo = 1;
	// private int pageSize = 10;
	private String keyword;
	private String from_date;
	private String to_date;
	private int no_of_guests;
	private Double price_range_min;
	private Double price_range_max;
	private Double price;
	private List<String> schedule;
	private List<Integer> duration;
	private List<Float> rating;
	private String nationality;
	private String location;
	private Double latitude;
	private Double longitude;
	private Double neighbourhood;
	private String age_group;

	private List<String> listing_type;

	private String sortBy;
	private String sortOrder;

	private Boolean searchmap;
	private Double topleftlat;
	private Double topleftlon;
	private Double bottomrightlat;
	private Double bottomrightlon;

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getSearchmap() {
		return searchmap;
	}

	public void setSearchmap(Boolean searchMap) {
		this.searchmap = searchMap;
	}

	public Double getTopleftlat() {
		return topleftlat;
	}

	public void setTopleftlat(Double topleftlat) {
		this.topleftlat = topleftlat;
	}

	public Double getTopleftlon() {
		return topleftlon;
	}

	public void setTopleftlon(Double topleftlon) {
		this.topleftlon = topleftlon;
	}

	public Double getBottomrightlat() {
		return bottomrightlat;
	}

	public void setBottomrightlat(Double bottomrightlat) {
		this.bottomrightlat = bottomrightlat;
	}

	public Double getBottomrightlon() {
		return bottomrightlon;
	}

	public void setBottomrightlon(Double bottomrightlon) {
		this.bottomrightlon = bottomrightlon;
	}

	public void setDuration(List<String> duration) {
		if (duration == null) {
			this.duration = null;
		} else {
			this.duration = new ArrayList<Integer>();
			for (String s : duration)
				this.duration.add(Integer.valueOf(s));
		}
	}

	public void setRating(List<String> rating) {
		if (rating == null) {
			this.rating = null;
		} else {
			this.rating = new ArrayList<Float>();
			for (String s : rating)
				this.rating.add(Float.valueOf(s));
		}
	}

	public List<String> getListing_type() {
		return listing_type;
	}

	public void setListing_type(List<String> listing_type) {
		if (listing_type == null) {
			this.listing_type = null;
		} else {
			this.listing_type = new ArrayList<String>();
			for (String s : listing_type)
				this.listing_type.add(s.toLowerCase());
		}
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		// this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		// this.to_date = to_date;
	}

	public int getNo_of_guests() {
		return no_of_guests;
	}

	public void setNo_of_guests(int no_of_guests) {
		this.no_of_guests = no_of_guests;
	}

	public Double getPrice_range_min() {
		return price_range_min;
	}

	public void setPrice_range_min(Double price_range_min) {
		this.price_range_min = price_range_min;
	}

	public Double getPrice_range_max() {
		return price_range_max;
	}

	public void setPrice_range_max(Double price_range_max) {
		this.price_range_max = price_range_max;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<String> schedule) {
		this.schedule = schedule;
	}

	public List<Integer> getDuration() {
		return duration;
	}

	public List<Float> getRating() {
		return rating;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality.isEmpty() ? null : nationality;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location.isEmpty() ? null : location;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(Double neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getAge_group() {
		return age_group;
	}

	public void setAge_group(String age_group) {
		this.age_group = age_group.isEmpty() ? null : age_group;
	}

	public String toString() {
		return "Age Group: '" + this.age_group + "', Keyword: '" + this.keyword;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo == 0 ? 1 : pageNo;
	}

	// public int getPageSize() {
	// return pageSize;
	// }
	//
	// public void setPageSize(int pageSize) {
	// this.pageSize = pageSize;
	// }
}
