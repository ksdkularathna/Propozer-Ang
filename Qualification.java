package com.bargadss.Propozal.Admin.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Qualification {

	private long id;
	private String qualification;
	
	public Qualification() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	// ***********************************************************************
		public JSONObject toJSON() throws JSONException {
			JSONObject jo = new JSONObject();
			jo.put("id", id);
			jo.put("qualification", qualification);
			
			return jo;
		}
}
