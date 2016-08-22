package com.bargadss.Propozal.Admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;

import com.bargadss.Propozal.Admin.bean.AdminLogin;

public class AdminLoginDao {

	Session session;

	public JSONObject loginValidation(String userName, String password,
			SessionFactory factory) {

		session = factory.openSession();

		JSONObject jo = new JSONObject();

		try {

			Query query = session
					.createQuery("from AdminLogin where userName=:userName");
			query.setString("userName", userName);
			
			List<AdminLogin> list = query.list();

			System.out.println("login list: " + list);

			if (!list.isEmpty()) {

				for (AdminLogin admin : list) {

					String pass = admin.getPassword();

					if (pass.equals(password)) {
						//jo.put("firstName", register.getFirstName());
						//jo.put("lastName", register.getLastName());
						jo.put("response", "success");
						jo.put("userName", userName);

					} else {
						jo.put("response", "fail");
					}
				}
			} else {
				jo.put("response", "fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jo.put("response", "fail");
		}

		return jo;
	}

}
