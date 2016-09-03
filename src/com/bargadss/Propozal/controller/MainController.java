package com.bargadss.Propozal.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bargadss.Propozal.Dao.CountryStateCityDao;
import com.bargadss.Propozal.Dao.ImageProcessingDao;
import com.bargadss.Propozal.Dao.RegistrationDao;
import com.bargadss.Propozal.Dao.SalaryDao;
import com.bargadss.Propozal.Dao.UserLoginDao;
import com.bargadss.Propozal.Dao.YearsDao;
import com.bargadss.Propozal.bean.Country;
import com.bargadss.Propozal.bean.ImageProcessing;
import com.bargadss.Propozal.bean.Registration;
import com.bargadss.Propozal.mail.MailDao;
import com.bargadss.Propozal.mail.MailServlet;
import com.bargadss.Propozal.util.HibernateUtil;
import com.bargadss.Propozal.util.JSONUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class MainController {

	SessionFactory factory = new HibernateUtil().createSessionFactory();

	Session session;

	JSONUtil jsonUtil = new JSONUtil();
	JSONParser parser = new JSONParser();
	JSONObject jsonObject = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	String json = null;

	HttpSession httpSession;

	// *****************************************************************************************
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public Response userLogin(@RequestBody String inputObj) {

		System.out.println("data received: " + inputObj);

		try {
			jsonObject = (JSONObject) parser.parse(inputObj);

			String userName = (String) jsonObject.get("usernames");
			String password = (String) jsonObject.get("passwords");

			System.out.println("userName: " + userName);
			System.out.println("password: " + password);

			UserLoginDao dao = new UserLoginDao();

			jsonObject = new JSONObject();
			jsonObject = dao.loginValidation(userName, password, factory);

			// if(flag){
			// jsonObject.put("response", "success");
			// }else{
			// jsonObject.put("response", "fail");
			// }
			//

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(jsonObject).build();
	}

	// *****************************************************************************************
	@RequestMapping(value = "/registration/", method = RequestMethod.POST)
	public Response userRegistration(HttpServletRequest request,
			HttpServletResponse response,@RequestBody String inputObj) {

		System.out.println("data received: " + inputObj);

		try {
			jsonObject = (JSONObject) parser.parse(inputObj);

			Registration registration = new Registration(jsonObject);

			RegistrationDao dao = new RegistrationDao();			

			
			//___________________________________________________________________________________CommentedOut	
			
			
			boolean flag =false;
			flag=dao.registerUser(registration, factory);
			
			if (flag) {
				
				MailDao mailDao=new MailDao();
				mailDao.doProcess(request,registration.getEmail(),registration.getFirstName());
				
				jsonObject.put("response", "success");
			} else {
				jsonObject.put("response", "fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(jsonObject).build();
	}

	// *****************************************************************************************
	@RequestMapping(value = "/emailCheck/", method = RequestMethod.POST)
	public JSONObject userEmailCheckn(@RequestBody String inputObj) {

		System.out.println("data received: " + inputObj);

		boolean flag = false;

		try {
			jsonObject = (JSONObject) parser.parse(inputObj);

			String email = (String) jsonObject.get("emailId");

			RegistrationDao dao = new RegistrationDao();

			flag = dao.checkEmail(email, factory);

			System.out.println(flag);

			jsonObject = new JSONObject();

			if (flag) {
				jsonObject.put("response", "valid");

			} else {
				jsonObject.put("response", "notValid");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println(jsonObject);
		return jsonObject;
	}

	// *******************************************************************************//
	@RequestMapping(value = "/country/", method = RequestMethod.GET)
	public Response listCountry() throws JsonParseException,
			JsonMappingException, IOException {

		CountryStateCityDao dao = new CountryStateCityDao();
		try {
			json = dao.listCountry(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/state/", method = RequestMethod.GET)
	public Response listState() throws JsonParseException,
			JsonMappingException, IOException {

		CountryStateCityDao dao = new CountryStateCityDao();
		try {
			json = dao.listState(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/states/", method = RequestMethod.POST)
	public Response listCountryState(@RequestBody String inputObj) {

		CountryStateCityDao dao = new CountryStateCityDao();
		try {

			System.out.println("Data received: " + inputObj);

			jsonObject = (JSONObject) parser.parse(inputObj);

			long countryId = 0;

			try {
				countryId = (long) jsonObject.get("countryId");
			} catch (Exception e) {
				try {
					countryId = Long.valueOf((String) jsonObject
							.get("countryId"));
				} catch (Exception e2) {
					countryId = 0;
				}
			}

			json = dao.listCountrysState(countryId, factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/city/", method = RequestMethod.GET)
	public Response listCity() throws JsonParseException, JsonMappingException,
			IOException {

		CountryStateCityDao dao = new CountryStateCityDao();
		try {
			json = dao.listCity(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/cityy/", method = RequestMethod.POST)
	public Response listStateCity(@RequestBody String inputObj) {

		CountryStateCityDao dao = new CountryStateCityDao();
		try {

			System.out.println("Data received: " + inputObj);

			jsonObject = (JSONObject) parser.parse(inputObj);

			long stateId = 0;

			try {
				stateId = (long) jsonObject.get("stateId");
			} catch (Exception e) {
				try {
					stateId = Long.valueOf((String) jsonObject.get("stateId"));
				} catch (Exception e2) {
					stateId = 0;
				}
			}

			System.out.println(stateId);

			json = dao.listStatesCity(stateId, factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/salary/", method = RequestMethod.GET)
	public Response getSalaryList() throws JsonParseException,
			JsonMappingException, IOException {

		SalaryDao dao = new SalaryDao();
		try {
			json = dao.salaryList(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/getAll/", method = RequestMethod.GET)
	public Response getAllList() throws JsonParseException,
			JsonMappingException, IOException {

		CountryStateCityDao dao = new CountryStateCityDao();
		try {
			json = dao.getAll(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/profile/", method = RequestMethod.POST)
	public Response profileView(@RequestBody String inputObj) {

		RegistrationDao dao = new RegistrationDao();
		try {

			System.out.println("Data received: " + inputObj);

			jsonObject = (JSONObject) parser.parse(inputObj);

			String emailId = (String) jsonObject.get("userId");

			json = dao.viewProfile(emailId, factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/pendingProfile/", method = RequestMethod.POST)
	public Response pendingProfileView(@RequestBody String inputObj) {

		RegistrationDao dao = new RegistrationDao();
		try {

			System.out.println("Data received: " + inputObj);

			jsonObject = (JSONObject) parser.parse(inputObj);

			String emailId = (String) jsonObject.get("userId");

			json = dao.pendingProfile(emailId, factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/profileForEdit/", method = RequestMethod.POST)
	public Response profileEditView(@RequestBody String inputObj) {

		RegistrationDao dao = new RegistrationDao();
		try {

			System.out.println("Data received: " + inputObj);

			jsonObject = (JSONObject) parser.parse(inputObj);

			String emailId = (String) jsonObject.get("userId");

			json = dao.viewProfileForEdit(emailId, factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/updatePending/", method = RequestMethod.POST)
	public JSONObject updatePendingVal(@RequestBody String inputObj) {

		boolean flag = false;

		RegistrationDao dao = new RegistrationDao();
		try {

			System.out.println("Data received: " + inputObj);

			jsonObject = (JSONObject) parser.parse(inputObj);
			String emailId = (String) jsonObject.get("userId");

			flag = dao.updatePendingValue(jsonObject, factory);
			// jsonObject = (JSONObject) parser.parse(json);

			jsonObject = new JSONObject();
			if (flag) {
				jsonObject.put("response", "success");
			} else {
				jsonObject.put("response", "fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("response", "fail");
		}
		// System.out.println(jsonObject);
		return jsonObject;
	}

	// *******************************************************************************//
	@RequestMapping(value = "/year/", method = RequestMethod.GET)
	public Response listYears() throws JsonParseException,
			JsonMappingException, IOException {

		YearsDao dao = new YearsDao();
		try {
			json = dao.listYear(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/image/", method = RequestMethod.POST)
	public Response imageUpload(@RequestBody byte[] inputObj)
			throws JsonParseException, JsonMappingException, IOException {

		ImageProcessingDao dao = new ImageProcessingDao();
		try {

			System.out.println("data received: " + inputObj);

			// String encoded = Base64.getEncoder().encodeToString(inputObj);

			System.out.println("****************************************");
			// System.out.println(encoded);
			System.out.println("****************************************");

			// dao.saveImage(inputObj, factory);

			// jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/search/", method = RequestMethod.POST)
	public Response searchList(@RequestBody String inputObj)
			throws JsonParseException, JsonMappingException, IOException {

		RegistrationDao dao = new RegistrationDao();

		System.out.println("Data received: " + inputObj);

		try {
			jsonObject = (JSONObject) parser.parse(inputObj);
			json = dao.searchLists(jsonObject, factory);

			jsonObject = (JSONObject) parser.parse(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}
	
	// *******************************************************************************//
		@RequestMapping(value = "/newSearch/", method = RequestMethod.POST)
		public Response newSearchList(@RequestBody String inputObj)
				throws JsonParseException, JsonMappingException, IOException {

			RegistrationDao dao = new RegistrationDao();

			System.out.println("Data received: " + inputObj);

			try {
				jsonArray = (JSONArray) parser.parse(inputObj);
				
				System.out.println(jsonArray);
				
				json = dao.newSearchLists(jsonArray, factory);

				//jsonObject = (JSONObject) parser.parse(json);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(jsonObject);
			return Response.status(200).entity(jsonObject).build();
		}

	// *******************************************************************************//
	@RequestMapping(value = "/editProfile/", method = RequestMethod.POST)
	public JSONObject editProfiles(@RequestBody String inputObj)
			throws JsonParseException, JsonMappingException, IOException {

		System.out.println("Data received: " + inputObj);

		boolean temp = false;

		try {
			RegistrationDao dao = new RegistrationDao();
			jsonObject = (JSONObject) parser.parse(inputObj);

			session = factory.openSession();

			String country = null, city = null, state = null;

			try {
				if (jsonObject.containsKey("country")) {

					System.out.println("****************");
					country = (String) jsonObject.get("country");
				}
				if (jsonObject.containsKey("city")) {
					city = (String) jsonObject.get("city");
				}
				if (jsonObject.containsKey("state")) {
					state = (String) jsonObject.get("state");
				}
			} catch (Exception e) {

			}

			System.out.println(country);
			System.out.println(city);
			System.out.println(state);

			long countryId = 0;
			long cityId = 0;
			long stateId = 0;

			long c, s, cy;

			boolean flag = false;

			try {

				try {
					countryId = Long.valueOf(country);
				} catch (Exception e) {
					flag = true;
				}

				try {
					stateId = Long.valueOf(state);
				} catch (Exception e) {
					flag = true;
				}

				try {
					cityId = Long.valueOf(city);
				} catch (Exception e) {
					flag = true;
				}

			} catch (Exception e) {
				flag = true;
			}

			if (flag) {
				try {
					Query q = session
							.createQuery("select id from Country where country=:country");
					q.setString("country", country);

					countryId = (long) q.uniqueResult();
				} catch (Exception e) {
				}

				try {
					Query q = session
							.createQuery("select Id from State where state=:state");
					q.setString("state", state);

					stateId = (long) q.uniqueResult();
				} catch (Exception e) {
				}

				try {
					Query q = session
							.createQuery("select Id from City where city=:city");
					q.setString("city", city);

					cityId = (long) q.uniqueResult();
				} catch (Exception e) {
				}
			}

			jsonObject.put("country", countryId);
			jsonObject.put("state", stateId);
			jsonObject.put("city", cityId);

			Registration registration = new Registration(jsonObject);

			temp = dao.editProfile(registration, factory);

			jsonObject = new JSONObject();
			if (temp) {
				jsonObject.put("response", "success");
			} else {
				jsonObject.put("response", "fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("response", "fail");
		}

		return jsonObject;
	}

	// *****************************************************************************//

	@RequestMapping(value = "/doUploadss", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out1 = response.getWriter()) {

			HttpSession session = request.getSession();

			// String name = request.getParameter("unname");
			Part filePart = request.getPart("filecover");

			String photo = "";
			String path = "E:\\ProjectWorkspace\\UploadedPhoto";

			File file = new File(path);
			file.mkdir();

			System.out.println(filePart);

			String fileName = null;

			// String fileName = getFileName(filePart);

			final String partHeader = filePart.getHeader("content-disposition");

			for (String content : filePart.getHeader("content-disposition")
					.split(";")) {
				if (content.trim().startsWith("filename")) {
					fileName = content.substring(content.indexOf('=') + 1)
							.trim().replace("\"", "");
				}
			}

			System.out.println("fileName" + fileName);

			// fileName = "abc.jpg";

			OutputStream out = null;

			InputStream filecontent = null;

			PrintWriter writer = response.getWriter();
			try {
				out = new FileOutputStream(new File(path + File.separator
						+ fileName));

				filecontent = filePart.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);

					photo = path + "/" + fileName;
				}

				// Class.forName("com.mysql.jdbc.Driver");
				// Connection con = DriverManager.getConnection("URL",
				// "USERNAME",
				// "PASSWORD");
				// Statement stmt = con.createStatement();
				//
				// stmt.executeUpdate("insert into name(Name,photourl) values('"
				// + name + "','" + photo + "')");

			} catch (Exception e) {

			}
			// out1.println("<html><body><script>alert('Sucessfully Saved! Check the database and the path!');</script></body></html>");
		}

		return "Success";
	}

	// *******************************************************************************//
	@RequestMapping(value = "/initialSearch/", method = RequestMethod.GET)
	public Response initialSearchList() throws JsonParseException,
			JsonMappingException, IOException {

		RegistrationDao dao = new RegistrationDao();
		try {
			json = dao.listSearchOption(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/searchMenu/", method = RequestMethod.GET)
	public Response searchMenuList() throws JsonParseException,
			JsonMappingException, IOException {

		RegistrationDao dao = new RegistrationDao();
		try {
			json = dao.listSearchMenu(factory);
			jsonArray = (JSONArray) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonArray);
		return Response.status(200).entity(jsonArray).build();
	}
	
	// *******************************************************************************//
	@RequestMapping(value = "/showAllImage/", method = RequestMethod.POST)
	public Response viewImage(@RequestBody String inputObj) {

		RegistrationDao dao = new RegistrationDao();
		try {

			System.out.println("Data received: " + inputObj);

			jsonObject = (JSONObject) parser.parse(inputObj);

			String emailId = (String) jsonObject.get("userId");

			json = dao.viewOtherImages(emailId, factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}
	
	// *******************************************************************************//
		@RequestMapping(value = "/activateUser/", method = RequestMethod.POST)
		public Response activateUserss(@RequestBody String inputObj) {

			RegistrationDao dao = new RegistrationDao();
			try {

				System.out.println("Data received: " + inputObj);

				jsonObject = (JSONObject) parser.parse(inputObj);

				String emailId = (String) jsonObject.get("userId");

				boolean temp = dao.activateUsers(emailId, factory);
				if (temp) {
					jsonObject.put("response", "success");
				} else {
					jsonObject.put("response", "fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(jsonObject);
			return Response.status(200).entity(jsonObject).build();
		}
}
