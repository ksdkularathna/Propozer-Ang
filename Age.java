package com.bargadss.Propozal.Admin.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Age {

	private long ageId, age;

	public Age() {
	}

	public long getAgeId() {
		return ageId;
	}

	public void setAgeId(long ageId) {
		this.ageId = ageId;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		jo.put("ageId", ageId);
		jo.put("age", age);

		return jo;
	}

}
