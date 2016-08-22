package com.bargadss.Propozal.Admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bargadss.Propozal.Admin.bean.ImageUpload;
import com.bargadss.Propozal.Admin.bean.Registration;

public class RegistrationDao {

	Session session;

	// **********************************************************************//
	public String listActiveUsers(SessionFactory factory) throws JSONException {
		session = factory.openSession();

		JSONObject record = new JSONObject();
		JSONArray records = new JSONArray();

		try {
			Query query = session
					.createQuery("from Registration where status=:status");
			query.setString("status", "activate");

			List<Registration> activeList = query.list();

			if (!activeList.isEmpty()) {
				for (Registration regi : activeList) {

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

	// **************************************************************************************************************//
	public String UserDetails(String email, SessionFactory factory)
			throws JSONException {

		// email=email.concat(".com");
		System.out.println("the name is" + email);
		session = factory.openSession();
		List<Registration> detail = new ArrayList<Registration>();
		Registration obj = null;
		JSONObject records = new JSONObject();
		JSONArray record = new JSONArray();

		String pending = "PenDing0o";

		try {
			Query query = session
					.createQuery("from Registration where email=:email");
			query.setString("email", email);

			// detail = query.list();
			obj = (Registration) query.uniqueResult();

			System.out.println(obj.toString());

			String email1 = obj.getEmail();
			String firstName = obj.getFirstName();

			// System.out.println(email);

			record.put(obj.toJSON());

		} catch (Exception e) {
			e.printStackTrace();
		}

		records.put("records", record);
		return records.toString();
	}

	// ************************************************************************************//
	public boolean verifyUser(org.json.simple.JSONObject jsonObject,
			SessionFactory factory) {

		session = factory.openSession();
		boolean flag=false;

		String status = "done";

		try {

			String email = (String) jsonObject.get("email");
			String params = (String) jsonObject.get("params");

			Query query = session
					.createQuery("from Registration where email=:email");
			query.setString("email", email);

			Registration user = (Registration) query.uniqueResult();

			switch (params) {
			case "FB":

				user.setFBstatus(status);

				break;

			case "LKD":
				user.setLstatus(status);
				break;

			case "NIC":
				user.setNICstatus(status);
				break;

			default:
				break;
			}
			
			//
			System.out.println(user.toString());
			
			Transaction transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();

			flag = true;
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// *************************************************************************************//
	public String viewProfile(String emailId, SessionFactory factory)
			throws JSONException {

		session = factory.openSession();

		JSONObject record = new JSONObject();
		JSONArray records = new JSONArray();

		try {
			Transaction transaction = session.beginTransaction();

			Query query = session
					.createQuery("from Registration where email=:email");
			query.setString("email", emailId);

			List<Registration> list = query.list();

			System.out.println("list: " + list);

			if (!list.isEmpty()) {
				for (Registration regi : list) {

					org.json.JSONObject jo = new org.json.JSONObject();

					long countryId, stateId, cityId;
					String country, state, city;
					Query q;

					//jo = regi.toEditJSON();
					jo = regi.toJSON();

					// images---------------------------------------------------------------

					try {
						q = session
								.createQuery("select max(imageId) from ImageUpload where email=:email and imageType=:imageType");
						q.setString("email", emailId);
						q.setString("imageType", "profile");

						long imageId = (long) q.uniqueResult();

						System.out.println("imageId: " + imageId);

						q = session
								.createQuery("from ImageUpload where imageId=:imageId");
						q.setLong("imageId", imageId);

						ImageUpload upload = (ImageUpload) q.uniqueResult();

						String path = upload.getImagePath();

						System.out.println("path frtom db: " + path);

						// path="Penguins.jpg";

						jo.put("image", path);

						try {
							q = session
									.createQuery("from ImageUpload where email=:email and imageType=:imageType order by imageId desc");
							q.setString("email", emailId);
							q.setString("imageType", "other");

							List<ImageUpload> imageList = q.list();

							System.out
									.println("--------------------------------"
											+ imageList);

							JSONArray imageArray = new JSONArray();

							if (!imageList.isEmpty()) {
								for (ImageUpload im : imageList) {
									String imagePath = im.getImagePath();
									imageArray.put(imagePath);
								}
							}

							System.out.println("imageArray: " + imageArray);
							jo.put("otherImages", imageArray);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

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
	
//*****************************************//
	public boolean actionUser(org.json.simple.JSONObject jsonObject,
			SessionFactory factory) {

		session = factory.openSession();
		boolean flag=false;

		String status = "done";

		try {

			String email = (String) jsonObject.get("email");
			String params = (String) jsonObject.get("params");
System.out.println("email is"+email);
System.out.println("params is"+params);
			Query query = session
					.createQuery("from Registration where email=:email");
			query.setString("email", email);

			Registration user = (Registration) query.uniqueResult();

			switch (params) {
			case "deactivate":

				user.setAdminStatus("activate");
				user.setStatus("activate");
				break;

			case "activate":
				user.setAdminStatus("deactivate");
				user.setStatus("deactivate");
				break;
				
			case "reject":
				user.setAdminStatus("reject");
				break;

			

			default:
				break;
			}
			
			//
			System.out.println(user.toString());
			
			Transaction transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();

			flag = true;
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	
	public boolean deleteUser(org.json.simple.JSONObject jsonObject,
			SessionFactory factory) {

		session = factory.openSession();
		boolean flag=false;

		String status = "done";

		try {

			String email = (String) jsonObject.get("email");
			String params = (String) jsonObject.get("params");
//System.out.println("email is"+email);
//System.out.println("params is"+params);
			Query query = session
					.createQuery("delete from Registration where email=:email");
			query.setString("email", email);
			query.executeUpdate();
			
			Transaction transaction = session.beginTransaction();
			
			transaction.commit();

			flag = true;
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
}
