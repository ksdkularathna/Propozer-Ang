package com.bargadss.Propozal.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Religion {

	private long religionId;
	private String religion;

	public Religion() {
	}

	public long getReligionId() {
		return religionId;
	}

	public void setReligionId(long religionId) {
		this.religionId = religionId;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		jo.put("religionId", religionId);
		jo.put("relegion", religion);

		return jo;
	}

}
