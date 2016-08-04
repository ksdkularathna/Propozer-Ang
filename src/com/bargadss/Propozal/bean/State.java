package com.bargadss.Propozal.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class State {

	private long Id, countryId;
	private String state;

	public State() {
		super();
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		jo.put("Id", Id);
		jo.put("state", state);
		jo.put("countryId", countryId);

		return jo;
	}

}
