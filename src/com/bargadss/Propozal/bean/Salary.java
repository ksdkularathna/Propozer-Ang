package com.bargadss.Propozal.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Salary {

	private long serialNo;
	private String salaryValue;
	private long value;

	public Salary() {
	}

	public long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

	public String getSalaryValue() {
		return salaryValue;
	}

	public void setSalaryValue(String salaryValue) {
		this.salaryValue = salaryValue;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	// ***********************************************************************
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		jo.put("serialNo", serialNo);
		jo.put("value", value);
		jo.put("salaryValue", salaryValue);
		return jo;
	}

}
