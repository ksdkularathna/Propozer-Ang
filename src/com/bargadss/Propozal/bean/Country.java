package com.bargadss.Propozal.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Country {
	
	private long id;
	private String country;	
	
	public Country() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		jo.put("id", id);
		jo.put("country", country);
		
		return jo;
	}

}
