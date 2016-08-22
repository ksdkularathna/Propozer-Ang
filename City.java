package com.bargadss.Propozal.Admin.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class City {

	private long Id, stateId;
	private String city;

	public City() {
		super();
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		jo.put("Id", Id);
		jo.put("city", city);
		jo.put("stateId", stateId);

		return jo;
	}

}
