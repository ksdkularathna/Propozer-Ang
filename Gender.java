package com.bargadss.Propozal.Admin.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Gender {
	
	private long genderId;
	private String gender;	
	
	public Gender() {
		super();
	}
	public long getGenderId() {
		return genderId;
	}
	public void setGenderId(long genderId) {
		this.genderId = genderId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	// ***********************************************************************
		public JSONObject toJSON() throws JSONException {

			JSONObject jo = new JSONObject();
			jo.put("genderId", genderId);
			jo.put("gender", gender);			
			return jo;
		}

}
