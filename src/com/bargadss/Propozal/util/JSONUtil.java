package com.bargadss.Propozal.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONUtil {

	public JSONObject toJsonObject(String json) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			Object obj = parser.parse(json);
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
			e.printStackTrace();
		}
		return jsonObject;
	}

}
