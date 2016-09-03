package com.bargadss.Propozal.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bargadss.Propozal.bean.Salary;

public class SalaryDao {
	
	Session session;

	public String salaryList(SessionFactory factory) {
		
		session=factory.openSession();
		JSONObject records = new JSONObject();
		
		try {			
			Query query=session.createQuery("from Salary");
			List<Salary> listData = query.list();

			JSONArray record = new JSONArray();

			for (Salary salary : listData) {
				record.put(salary.toJSON());
			}
			records.put("records", record);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return records.toString();
	}

}
