package com.bargadss.Propozal.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bargadss.Propozal.bean.State;
import com.bargadss.Propozal.bean.Years;

public class YearsDao {

	Session session;

	// **************************************************************//
	public String listYear(SessionFactory factory) {
		session = factory.openSession();
		JSONObject records = new JSONObject();

		try {
			Query query = session.createQuery("from Years");			

			List<Years> listData = query.list();

			JSONArray record = new JSONArray();

			for (Years year : listData) {
				record.put(year.toJSON());
			}
			records.put("records", record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records.toString();
	}

}
