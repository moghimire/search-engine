package com.goglides.esservice.dto;

import java.sql.Date;
import java.sql.Time;

public class ResultParam {
	private int listing_ID;
	private String listing_title;
	private String schedule;
	private int duration;
	private Double priceActivity;
	private Double priceTransportation;
	private Double priceMultiMedia;
	private String listing_type;
	private Date from_date;
	private float rating;
	private String companyName;
	
	
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getPriceActivity() {
		return priceActivity;
	}

	public void setPriceActivity(Double priceActivity) {
		this.priceActivity = priceActivity;
	}

	public Double getPriceTransportation() {
		return priceTransportation;
	}

	public void setPriceTransportation(Double priceTransportation) {
		this.priceTransportation = priceTransportation;
	}

	public Double getPriceMultiMedia() {
		return priceMultiMedia;
	}

	public void setPriceMultiMedia(Double priceMultiMedia) {
		this.priceMultiMedia = priceMultiMedia;
	}

	public String getListing_title() {
		return listing_title;
	}

	public void setListing_title(String listing_title) {
		this.listing_title = listing_title;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getListing_type() {
		return listing_type;
	}

	public void setListing_type(String listing_type) {
		this.listing_type = listing_type;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getListing_ID() {
		return listing_ID;
	}

	public void setListing_ID(int listing_ID) {
		this.listing_ID = listing_ID;
	}

}
