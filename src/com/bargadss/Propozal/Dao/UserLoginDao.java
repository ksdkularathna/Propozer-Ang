package com.bargadss.Propozal.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;

import com.bargadss.Propozal.bean.Registration;
import com.bargadss.Propozal.bean.UserLogin;

public class UserLoginDao {

	Session session;

	public JSONObject loginValidation(String userName, String password,
			SessionFactory factory) {

		session = factory.openSession();

		JSONObject jo = new JSONObject();

		try {

			Query query = session
					.createQuery("from Registration where email=:email and status=:status");
			query.setString("email", userName);
			query.setString("status", "activate");

			List<Registration> list = query.list();

			System.out.println("login list: " + list);

			if (!list.isEmpty()) {

				for (Registration register : list) {

					String pass = register.getPassword();

					if (pass.equals(password)) {
						jo.put("firstName", register.getFirstName());
						jo.put("lastName", register.getLastName());
						jo.put("response", "success");
						jo.put("userId", userName);
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
