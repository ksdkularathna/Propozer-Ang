package com.bargadss.Propozal.Admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bargadss.Propozal.Admin.bean.Registration;

public class UserDao {

	Session session;

	// **********************************************************************//
	public String listActiveUsers(SessionFactory factory) throws JSONException {
		session = factory.openSession();

		JSONObject record = new JSONObject();
		JSONArray records = new JSONArray();

		try {
			Query query = session
					.createQuery("from Registration");
			//query.setString("status", "activate");

			List<Registration> activeList = query.list();

			if (!activeList.isEmpty()) {
				for (Registration regi : activeList) {
				System.out.println(regi.getStatus());
					
               //  System.out.println(regi.getAdminStatus());
					JSONObject jo = new JSONObject();

					long countryId, stateId, cityId;
					String country, state, city;
					Query q;

					jo = regi.toEditJSON();
					
					jo.put("adminStatus", regi.getAdminStatus());

					try {
						countryId = regi.getCountry();
						q = session
								.createQuery("select country from Country where id=:id");
						q.setLong("id", countryId);

						country = (String) q.uniqueResult();
						System.out.println("country: " + country);

						jo.put("country", country);

						try {
							stateId = regi.getState();
							q = session
									.createQuery("select state from State where Id=:Id");
							q.setLong("Id", stateId);

							// State stateObj = (State) q.uniqueResult();

							// stateId = stateObj.getId();
							state = (String) q.uniqueResult();

							jo.put("state", state);

							try {
								cityId = regi.getCity();
								q = session
										.createQuery("select city from City where Id=:Id");
								q.setLong("Id", cityId);

								// City cityObj = (City) q.uniqueResult();

								// cityId = cityObj.getId();
								city = (String) q.uniqueResult();

								jo.put("city", city);

							} catch (Exception e) {
								e.printStackTrace();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					records.put(jo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		record.put("records", records);
		return record.toString();
	}

	// **************************************************************************************************************//
	public String listInactiveUsers(SessionFactory factory)
			throws JSONException {
		session = factory.openSession();

		JSONObject record = new JSONObject();
		JSONArray records = new JSONArray();

		String pending = "PenDing0o";

		try {
			Query query = session
					.createQuery("from Registration where status=:status");
			query.setString("status", "pending");

			List<Registration> inactiveList = query.list();
			
			System.out.println(inactiveList);

			if (!inactiveList.isEmpty()) {
				for (Registration regi : inactiveList) {

					String email = regi.getEmail();
					String firstName = regi.getFirstName();
					
					System.out.println(email);

					if (!email.equalsIgnoreCase(pending)) {

						JSONObject jo = new JSONObject();

						long countryId, stateId, cityId;
						String country, state, city;
						Query q;

						jo = regi.toEditJSON();

						try {
							countryId = regi.getCountry();
							q = session
									.createQuery("select country from Country where id=:id");
							q.setLong("id", countryId);

							country = (String) q.uniqueResult();
							System.out.println("country: " + country);

							jo.put("country", country);

							try {
								stateId = regi.getState();
								q = session
										.createQuery("select state from State where Id=:Id");
								q.setLong("Id", stateId);

								// State stateObj = (State) q.uniqueResult();

								// stateId = stateObj.getId();
								state = (String) q.uniqueResult();

								jo.put("state", state);

								try {
									cityId = regi.getCity();
									q = session
											.createQuery("select city from City where Id=:Id");
									q.setLong("Id", cityId);

									// City cityObj = (City) q.uniqueResult();

									// cityId = cityObj.getId();
									city = (String) q.uniqueResult();

									jo.put("city", city);

								} catch (Exception e) {
									e.printStackTrace();
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						records.put(jo);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		record.put("records", records);
		return record.toString();
	}

}
