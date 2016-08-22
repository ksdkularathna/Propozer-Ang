package com.bargadss.Propozal.Admin.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Years {

	private long serialNo;
	private long year;

	public Years() {
	}

	public long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		jo.put("serialNo", serialNo);
		jo.put("year", year);
		
		return jo;
	}

}
