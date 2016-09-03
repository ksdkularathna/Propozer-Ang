package com.bargadss.Propozal.Dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.util.WeakReferenceMonitor.ReleaseListener;

import com.bargadss.Propozal.bean.Age;
import com.bargadss.Propozal.bean.City;
import com.bargadss.Propozal.bean.Country;
import com.bargadss.Propozal.bean.Gender;
import com.bargadss.Propozal.bean.ImageProcessing;
import com.bargadss.Propozal.bean.ImageUpload;
import com.bargadss.Propozal.bean.Qualification;
import com.bargadss.Propozal.bean.Random;
import com.bargadss.Propozal.bean.Registration;
import com.bargadss.Propozal.bean.Religion;
import com.bargadss.Propozal.bean.State;
import com.mysql.jdbc.util.Base64Decoder;

public class RegistrationDao {

	Session session;

	public boolean registerUser(Registration registration,
			SessionFactory factory) {
		boolean flag = false;

		session = factory.openSession();

		try {
			Transaction transaction = session.beginTransaction();
			session.save(registration);
			transaction.commit();

			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// *************************************************************************************//
	public boolean checkEmail(String email, SessionFactory factory) {

		session = factory.openSession();
		boolean flag;

		System.out.println(email);

		try {
			Query query = session
					.createQuery("from Registration where email=:email");
			query.setString("email", email);

			List<Registration> list = query.list();

			System.out.println("list: " + list);

			if (list.isEmpty()) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	// *************************************************************************************//
	public String viewProfile(String emailId, SessionFactory factory) {

		session = factory.openSession();

		JSONObject record = new JSONObject();
		JSONArray records = new JSONArray();

		try {
			Transaction transaction = session.beginTransaction();

			Random random = new Random();
			session.save(random);
			transaction.commit();

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

					jo = regi.toEditJSON();

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
									imageArray.add(imagePath);
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
					records.add(jo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		record.put("records", records);

		return record.toString();
	}

	// *************************************************************************************//
	public String searchLists(JSONObject jsonObject, SessionFactory factory) {

		session = factory.openSession();

		String none = " ";

		JSONObject record = new JSONObject();
		JSONArray records = new JSONArray();

		JSONArray searchArray = new JSONArray();

		String gender = null, edu = null;
		long city = 0;
		long minAge = 0, maxAge = 0;

		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());

		long year = cal.get(Calendar.YEAR);

		long matchingCount = 0;

		try {
			Registration registration = new Registration();
			Class classN = registration.getClass();
			Criteria cr = session.createCriteria(classN);

			Criterion gen, cit, ed, mins, maxs;

			// Criterion user = Restrictions.like("username", username);
			// Criterion pass = Restrictions.like("password", password);

			// cr.add(user);
			// cr.add(pass);

			// To get records matching with AND condistions
			// LogicalExpression andExp = Restrictions.and(user, pass);
			// cr.add(andExp);
			// login = (Login) cr.uniqueResult();

			// List<Login> list = cr.list();

			if (jsonObject.containsKey("gender")) {
				try {
					gender = (String) jsonObject.get("gender");

					if (gender != null && !gender.isEmpty()
							&& !gender.trim().isEmpty()) {
						gen = Restrictions.like("gender", gender);
						cr.add(gen);

						JSONObject searchJson = new JSONObject();

						searchJson.put("searchName", "Gender");
						searchJson.put("searchValue", gender);

						searchArray.add(searchJson);

					}
				} catch (Exception e) {
					gender = null;
				}

				System.out.println(gender);
			}

			if (jsonObject.containsKey("qualification")) {
				try {
					edu = (String) jsonObject.get("qualification");

					System.out.println("qualification" + edu + "qualification");

					if (edu != null && !edu.isEmpty() && !edu.trim().isEmpty()) {

						System.out.println("not null: " + edu);

						ed = Restrictions.like("higestEdu", edu);
						cr.add(ed);

						JSONObject searchJson = new JSONObject();

						searchJson.put("searchName", "Qualification");
						searchJson.put("searchValue", edu);

						searchArray.add(searchJson);
					}
				} catch (Exception e) {
					edu = null;
				}
				System.out.println(edu);
			}

			if (jsonObject.containsKey("city")) {
				try {

					String cities = (String) jsonObject.get("city");

					Query q = session
							.createQuery("select Id from City where city=:city");
					q.setString("city", cities);

					city = (long) q.uniqueResult();

					// city = Long.valueOf((String) jsonObject.get("city"));

					System.out.println("city code: " + city);

					if (city != 0) {
						cit = Restrictions.eq("city", city);
						cr.add(cit);

						JSONObject searchJson = new JSONObject();

						searchJson.put("searchName", "City");
						searchJson.put("searchValue", cities);

						searchArray.add(searchJson);

					}
				} catch (Exception e) {
					city = 0;
					e.printStackTrace();
				}
				System.out.println(city);
			}

			if (jsonObject.containsKey("minAge")) {
				try {
					minAge = Long.valueOf((String) jsonObject.get("minAge"));

					if (minAge != 0) {

						long birthYear = (year - minAge);

						// String dobYear=current.to
						System.out.println(birthYear);

						mins = Restrictions.le("birthYear", birthYear);
						cr.add(mins);

						JSONObject searchJson = new JSONObject();

						searchJson.put("searchName", "Age");
						searchJson.put("searchValue", minAge);

						searchArray.add(searchJson);
					}
				} catch (Exception e) {

				}
			}

			if (jsonObject.containsKey("maxAge")) {
				try {
					maxAge = Long.valueOf((String) jsonObject.get("maxAge"));

					if (maxAge != 0) {

						long birthYear = (year - maxAge);

						System.out.println(birthYear);

						// String dobYear=current.to

						maxs = Restrictions.ge("birthYear", birthYear);
						cr.add(maxs);

						JSONObject searchJson = new JSONObject();

						searchJson.put("searchName", "Age");
						searchJson.put("searchValue", maxAge);

						searchArray.add(searchJson);
					}
				} catch (Exception e) {

				}
			}

			List<Registration> list = cr.list();

			System.out.println(list);

			if (!list.isEmpty()) {

				for (Registration regi : list) {

					records.add(regi.toJSON());
					matchingCount = matchingCount + 1;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		record.put("records", records);
		record.put("matchingCount", matchingCount);
		record.put("searchVal", searchArray);

		return record.toString();
	}

	// ********************************************************************************//
	public boolean editProfile(Registration registration, SessionFactory factory) {

		session = factory.openSession();

		boolean flag = false;

		try {
			System.out.println(registration.toString());

			Transaction transaction = session.beginTransaction();

			System.out.println(registration.toString());

			session.update(registration);

			session.getTransaction().commit();
			System.out.println("----------data updated------------");

			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// ********************************************************************************//
	public String viewProfileForEdit(String emailId, SessionFactory factory) {
		session = factory.openSession();

		JSONObject record = new JSONObject();
		JSONArray records = new JSONArray();

		try {
			Query query = session
					.createQuery("from Registration where email=:email");
			query.setString("email", emailId);

			List<Registration> list = query.list();

			if (!list.isEmpty()) {
				for (Registration regi : list) {

					org.json.JSONObject jo = new org.json.JSONObject();

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
					records.add(jo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		record.put("records", records);

		return record.toString();
	}

	// ******************************************************************************//
	public String pendingProfile(String emailId, SessionFactory factory) {

		session = factory.openSession();

		String pending = "PenDing0o";
		JSONObject record = new JSONObject();
		JSONArray records = new JSONArray();

		try {
			Query query = session
					.createQuery("from Registration where email=:email");
			query.setString("email", emailId);

			List<Registration> list = query.list();

			if (!list.isEmpty()) {
				for (Registration registration : list) {

					// org.json.JSONObject jo = new org.json.JSONObject();

					// -----------------------------------------------------------------//
					// try {
					// if (registration.getSerialNo() != 0) {
					// jo.put("value", registration.getSerialNo());
					// jo.put("name", "Serial No.");
					// }
					// } catch (Exception e) {
					// e.printStackTrace();
					// }
					// -----------------------------------------------------------------//
					try {
						if (registration.getFirstName().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getFirstName());
							jo.put("name", "First Name");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// -----------------------------------------------------------------//
					try {
						if (registration.getLastName().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getLastName());
							jo.put("name", "Last Name");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// -----------------------------------------------------------------//
					try {
						if (registration.getHigestEdu().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getHigestEdu());
							jo.put("name", "Highest Education");
							jo.put("varField", "higestEdu");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getCareerField().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getCareerField());
							jo.put("name", "Career Field");
							jo.put("varField", "careerField");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getForSalary().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getForSalary());
							jo.put("name", "Monthly Salary");
							jo.put("varField", "monthlySal");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getPersonType().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getPersonType());
							jo.put("name", "Person Type");
							jo.put("varField", "personType");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getFamilyBackground().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getFamilyBackground());
							jo.put("name", "Family Background");
							jo.put("varField", "familyBackground");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getEduAndCareer().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getEduAndCareer());
							jo.put("name", "Education And Career");
							jo.put("varField", "eduAndCareer");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getLookingFor().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getLookingFor());
							jo.put("name", "Looking For");
							jo.put("varField", "lookingFor");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getNicNumber().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getNicNumber());
							jo.put("name", "Nic Number");
							jo.put("varField", "nicNumber");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getForMobile().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getMobileNumber());
							jo.put("name", "Mobile Number");
							jo.put("varField", "mobileNumber");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// -----------------------------------------------------------------//
					try {
						if (registration.getFacebookUrl().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getFacebookUrl());
							jo.put("name", "Enter Facebook URL");
							jo.put("varField", "facebookUrl");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// -----------------------------------------------------------------//

					try {
						if (registration.getLinkdinUrl().equals(pending)) {

							JSONObject jo = new JSONObject();

							jo.put("value", registration.getLinkdinUrl());
							jo.put("name", "Enter LinkdinUrl URL");
							jo.put("varField", "linkdinUrl");
							records.add(jo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// -----------------------------------------------------------------//

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		record.put("records", records);
		return record.toString();
	}

	// ******************************************************************************//
	public boolean updatePendingValue(JSONObject jsonObject,
			SessionFactory factory) {
		session = factory.openSession();

		boolean flag = false;

		String pending = "PenDing0o";
		String done = "D0ne";

		try {

			JSONObject user = new JSONObject();
			JSONObject data = new JSONObject();

			if (jsonObject.containsKey("user")) {
				user = (JSONObject) jsonObject.get("user");
			}

			if (jsonObject.containsKey("data")) {
				data = (JSONObject) jsonObject.get("data");
			}

			System.out.println("user:" + user);
			System.out.println("date:" + data);

			String country = null, city = null, state = null;
			if (user.containsKey("country")) {

				System.out.println("****************");
				country = (String) user.get("country");
			}
			if (user.containsKey("city")) {
				city = (String) user.get("city");
			}
			if (user.containsKey("state")) {
				state = (String) user.get("state");
			}

			System.out.println(country);
			System.out.println(city);
			System.out.println(state);

			long countryId = 0;
			long cityId = 0;
			long stateId = 0;

			if (country != null) {

				Query q = session
						.createQuery("select id from Country where country=:country");
				q.setString("country", country);

				countryId = (long) q.uniqueResult();

				try {
					q = session
							.createQuery("select Id from State where state=:state");
					q.setString("state", state);

					stateId = (long) q.uniqueResult();
				} catch (Exception e) {
				}

				try {
					q = session
							.createQuery("select Id from City where city=:city");
					q.setString("city", city);

					cityId = (long) q.uniqueResult();
				} catch (Exception e) {
				}

				user.put("country", countryId);
				user.put("state", stateId);
				user.put("city", cityId);
			}

			Registration registration = new Registration(user);

			if (data.containsKey("varField")) {
				String varField = (String) data.get("varField");

				String value = (String) data.get("enteredValue");

				switch (varField) {

				case "higestEdu":
					registration.setHigestEdu(value);
					break;

				case "facebookUrl":
					registration.setFacebookUrl(value);
					break;

				case "linkdinUrl":
					registration.setLinkdinUrl(value);
					break;

				case "careerField":
					registration.setCareerField(value);
					break;

				case "monthlySal":
					registration.setMonthlySal(Long.valueOf(value));
					registration.setForSalary(done);
					break;

				case "personType":
					registration.setPersonType(value);
					break;

				case "familyBackground":
					registration.setFamilyBackground(value);
					break;

				case "eduAndCareer":
					registration.setEduAndCareer(value);
					break;

				case "lookingFor":
					registration.setLookingFor(value);
					break;

				case "nicNumber":
					registration.setNicNumber(value);
					break;

				case "mobileNumber":
					registration.setMobileNumber(Long.valueOf(value));
					registration.setForMobile(done);
					break;

				default:
					break;
				}

				// if (varField.equalsIgnoreCase("higestEdu")) {
				// registration.setHigestEdu(value);
				//
				// } else if (varField.equalsIgnoreCase("facebookUrl")) {
				// registration.setFacebookUrl(value);
				//
				// } else if (varField.equalsIgnoreCase("linkdinUrl")) {
				// registration.setLinkdinUrl(value);
				//
				// }
			}

			System.out.println(registration.toString());

			Transaction transaction = session.beginTransaction();

			session.update(registration);

			session.getTransaction().commit();
			flag = true;
			System.out.println("----------data updated------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	// ***********************************************************************//
	public String listSearchOption(SessionFactory factory) {
		session = factory.openSession();

		JSONObject record = new JSONObject();

		try {
			Query query = session.createQuery("from Gender order by gender");
			List<Gender> genderList = query.list();

			if (!genderList.isEmpty()) {

				JSONArray records = new JSONArray();
				for (Gender gender : genderList) {
					records.add(gender.toJSON());
				}

				record.put("genders", records);
			}

			query = session
					.createQuery("from Qualification order by qualification");
			List<Qualification> qualificationList = query.list();

			if (!qualificationList.isEmpty()) {

				JSONArray records = new JSONArray();
				for (Qualification qualification : qualificationList) {
					records.add(qualification.toJSON());
				}
				record.put("qualifications", records);
			}

			query = session.createQuery("from City order by city");
			List<City> cityList = query.list();

			if (!cityList.isEmpty()) {

				JSONArray records = new JSONArray();
				for (City city : cityList) {
					records.add(city.toJSON());
				}
				record.put("cities", records);
			}

			query = session.createQuery("from Age order by age");
			List<Age> ageList = query.list();

			if (!ageList.isEmpty()) {

				JSONArray records = new JSONArray();
				for (Age age : ageList) {
					records.add(age.toJSON());
				}
				record.put("ages", records);
			}

			query = session.createQuery("from Country order by country");
			List<Country> countryList = query.list();

			if (!countryList.isEmpty()) {

				JSONArray records = new JSONArray();
				for (Country country : countryList) {
					records.add(country.toJSON());
				}
				record.put("countries", records);
			}

			query = session.createQuery("from Religion order by religion");
			List<Religion> religionList = query.list();

			if (!religionList.isEmpty()) {

				JSONArray records = new JSONArray();
				for (Religion religion : religionList) {
					records.add(religion.toJSON());
				}
				record.put("religion", records);
			}

			// -------------------------------------------------------------Height
			JSONArray records = new JSONArray();

			JSONObject json = new JSONObject();
			json.put("height", "60");
			records.add(json);

			json = new JSONObject();
			json.put("height", "61");
			records.add(json);

			json = new JSONObject();
			json.put("height", "62");
			records.add(json);

			record.put("height", records);

			// -------------------------------------------------------------MaritalStatus
			records = new JSONArray();

			json = new JSONObject();
			json.put("maritalStatus", "Married");
			records.add(json);

			json = new JSONObject();
			json.put("maritalStatus", "Unmarried");
			records.add(json);

			json = new JSONObject();
			json.put("maritalStatus", "Single");
			records.add(json);

			record.put("MaritalStatus", records);

			// -------------------------------------------------------------Languages

			json = new JSONObject();
			json.put("motherTongue", "English");
			records.add(json);

			json = new JSONObject();
			json.put("motherTongue", "Hindi");
			records.add(json);

			json = new JSONObject();
			json.put("motherTongue", "Tamil");
			records.add(json);

			json = new JSONObject();
			json.put("motherTongue", "Sinhala");
			records.add(json);

			record.put("languages", records);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return record.toString();
	}

	// *****************************************************************************//
	public String listSearchMenu(SessionFactory factory) {
		session = factory.openSession();

		JSONObject record = new JSONObject();

		JSONArray array = new JSONArray();
		JSONObject jo = new JSONObject();

		try {
			// -----------------------------------------------------------------------------------//
			Query query = session.createQuery("from Gender order by gender");
			List<Gender> genderList = query.list();

			if (!genderList.isEmpty()) {

				JSONArray records = new JSONArray();
				for (Gender gender : genderList) {

					JSONObject jObj = new JSONObject();
					jObj.put("subMenu", gender.getGender());
					records.add(jObj);
				}

				record = new JSONObject();
				record.put("value", records);
				record.put("name", "Gender");
				record.put("identifier", "Gender");

				array.add(record);
			}

			// -------------------------------------------------------------Height
			JSONArray records = new JSONArray();

			JSONObject json = new JSONObject();
			json.put("subMenu", "60");
			records.add(json);

			json = new JSONObject();
			json.put("subMenu", "61");
			records.add(json);

			json = new JSONObject();
			json.put("subMenu", "62");
			records.add(json);

			record = new JSONObject();
			record.put("value", records);

			record.put("name", "Height");
			record.put("identifier", "Height");

			array.add(record);

			// ---------------------------------------------------------------------------------------//
			query = session.createQuery("from Age order by age");
			List<Age> ageList = query.list();

			if (!ageList.isEmpty()) {

				records = new JSONArray();
				for (Age age : ageList) {
					JSONObject jObj = new JSONObject();
					jObj.put("subMenu", age.getAge());
					records.add(jObj);
				}
				record = new JSONObject();
				record.put("value", records);
				record.put("name", "Age");
				record.put("identifier", "Age");

				array.add(record);
			}
			// -------------------------------------------------------------------------------------//
			query = session.createQuery("from Country order by country");
			List<Country> countryList = query.list();

			if (!countryList.isEmpty()) {

				records = new JSONArray();
				for (Country country : countryList) {
					JSONObject jObj = new JSONObject();
					jObj.put("subMenu", country.getCountry());
					records.add(jObj);
				}
				record = new JSONObject();
				record.put("value", records);
				record.put("name", "Country");
				record.put("identifier", "Country");

				array.add(record);
			}

			// -------------------------------------------------------------------------------------//
			query = session.createQuery("from City order by city");
			List<City> cityList = query.list();

			if (!cityList.isEmpty()) {

				records = new JSONArray();
				for (City city : cityList) {
					JSONObject jObj = new JSONObject();
					jObj.put("subMenu", city.getCity());
					records.add(jObj);
				}
				record = new JSONObject();
				record.put("value", records);
				record.put("name", "City");

				array.add(record);
			}

			// -------------------------------------------------------------MaritalStatus
			records = new JSONArray();

			json = new JSONObject();
			json.put("subMenu", "Married");
			records.add(json);

			json = new JSONObject();
			json.put("subMenu", "Unmarried");
			records.add(json);

			json = new JSONObject();
			json.put("subMenu", "Single");
			records.add(json);

			record = new JSONObject();
			record.put("value", records);

			record.put("name", "Marital Status");
			record.put("identifier", "MaritalStatus");

			array.add(record);

			// -------------------------------------------------------------------------------------//
			query = session.createQuery("from Religion order by religion");
			List<Religion> religionList = query.list();

			if (!religionList.isEmpty()) {

				records = new JSONArray();
				for (Religion religion : religionList) {
					JSONObject jObj = new JSONObject();
					jObj.put("subMenu", religion.getReligion());
					records.add(jObj);
				}
				record = new JSONObject();
				record.put("value", records);
				record.put("name", "Religion");
				record.put("identifier", "Religion");

				array.add(record);
			}

			// -------------------------------------------------------------Languages

			records = new JSONArray();

			json = new JSONObject();
			json.put("subMenu", "English");
			records.add(json);

			json = new JSONObject();
			json.put("subMenu", "Hindi");
			records.add(json);

			json = new JSONObject();
			json.put("subMenu", "Tamil");
			records.add(json);

			json = new JSONObject();
			json.put("subMenu", "Sinhala");
			records.add(json);

			record = new JSONObject();
			record.put("value", records);
			record.put("name", "Speaks");
			record.put("identifier", "Speaks");

			array.add(record);

			// -------------------------------------------------------------------------------------//
			query = session
					.createQuery("from Qualification order by qualification");
			List<Qualification> qualificationList = query.list();

			if (!qualificationList.isEmpty()) {

				records = new JSONArray();
				for (Qualification qualification : qualificationList) {
					JSONObject jObj = new JSONObject();
					jObj.put("subMenu", qualification.getQualification());
					records.add(jObj);
				}

				record = new JSONObject();
				record.put("value", records);
				record.put("name", "Education");
				record.put("identifier", "Education");

				array.add(record);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return array.toString();
	}

	// ***********************************************************************************//
	public String newSearchLists(JSONArray jsonArray, SessionFactory factory) {

		session = factory.openSession();

		try {

			for (Object o : jsonArray) {

				try {
					String values = (String) o;

					System.out.println(values);

					String[] lines = values.split(" ");

					String type = lines[0];
					String typeValue = lines[1];

					switch (type) {
					case "Gender":

						break;

					case "Height":

						break;

					case "Age":

						break;

					case "Country":

						break;

					case "City":

						break;

					case "MaritalStatus":

						break;

					case "Religion":

						break;

					case "Speaks":

						break;

					case "Education":

						break;

					default:
						break;
					}

				} catch (Exception ex) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// ********************************************************************************************//
	public String viewOtherImages(String emailId, SessionFactory factory) {

		session = factory.openSession();

		org.json.JSONObject jo = new org.json.JSONObject();
		
		try {
			Query query = session
					.createQuery("from ImageUpload where email=:email and imageType=:imageType order by imageId desc");
			query.setString("email", emailId);
			query.setString("imageType", "other");

			List<ImageUpload> imageList = query.list();

			System.out.println("--------------------------------" + imageList);

			JSONArray imageArray = new JSONArray();

			if (!imageList.isEmpty()) {
				for (ImageUpload im : imageList) {
					String imagePath = im.getImagePath();
					imageArray.add(im.toJSON());
				}
			}

			System.out.println("imageArray: " + imageArray);
			jo.put("otherImages", imageArray);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jo.toString();
	}

	//*******************************************************************************//
	public boolean activateUsers(String emailId, SessionFactory factory) {
		session=factory.openSession();
		
		boolean flag=false;
		
		try {
			
			Query query=session.createQuery("from Registration where email=:email");
			query.setString("email",emailId);
			
			Registration registration=(Registration) query.uniqueResult();
			
			registration.setStatus("activate");
			
			Transaction transaction = session.beginTransaction();
			session.update(registration);
			transaction.commit();

			flag = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}

}
