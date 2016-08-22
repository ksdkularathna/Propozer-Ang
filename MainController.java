package com.bargadss.Propozal.Admin.controller;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bargadss.Propozal.Admin.dao.AdminLoginDao;
import com.bargadss.Propozal.Admin.dao.RegistrationDao;
import com.bargadss.Propozal.Admin.dao.UserDao;
import com.bargadss.Propozal.Admin.mail.MailDao;
import com.bargadss.Propozal.Admin.util.HibernateUtil;
import com.bargadss.Propozal.Admin.util.JSONUtil;
import com.bargadss.Propozal.Admin.bean.Registration;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.bargadss.Propozal.Admin.mail.MailDao;
import com.bargadss.Propozal.Admin.mail.MailServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
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

			String userName = (String) jsonObject.get("username");
			String password = (String) jsonObject.get("password");

			System.out.println("userName: " + userName);
			System.out.println("password: " + password);

			AdminLoginDao dao = new AdminLoginDao();

			jsonObject = new JSONObject();
			jsonObject = dao.loginValidation(userName, password, factory);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(jsonObject);

		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/active/", method = RequestMethod.GET)
	public Response activeList() throws JsonParseException,
			JsonMappingException, IOException {

		UserDao dao = new UserDao();
		try {
			json = dao.listActiveUsers(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/inactive/", method = RequestMethod.GET)
	public Response inactiveList() throws JsonParseException,
			JsonMappingException, IOException {

		UserDao dao = new UserDao();
		try {
			json = dao.listInactiveUsers(factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/myprofile/", method = RequestMethod.POST)
	public Response myprofilelist(@RequestBody String inputObj)
			throws JsonParseException,

			JsonMappingException, IOException, ParseException {
		System.out.println("data received: " + inputObj);

		JSONObject jsonObject = null;
		jsonObject = (JSONObject) parser.parse(inputObj);
		String email = (String) jsonObject.get("email");
		RegistrationDao dao = new RegistrationDao();
		try {
			json = dao.viewProfile(email, factory);
			jsonObject = (JSONObject) parser.parse(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("jsonObject is" + jsonObject);
		return Response.status(200).entity(jsonObject).build();
	}

	// *******************************************************************************//
	@RequestMapping(value = "/verifyUser/", method = RequestMethod.POST)
	public Response verifyUsers(@RequestBody String inputObj)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		
		System.out.println("data received: " + inputObj);
		boolean flag=false;

		JSONObject jsonObject = null;
		jsonObject = (JSONObject) parser.parse(inputObj);
		
		RegistrationDao dao = new RegistrationDao();
		try {
			flag = dao.verifyUser(jsonObject, factory);
			
			if (flag) {
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

	
	
	
	@RequestMapping(value = "/updateStatus/", method = RequestMethod.POST)
	 public Response updateStatus(@RequestBody String inputObj, HttpServletRequest request)
	   throws JsonParseException, JsonMappingException, IOException,
	   ParseException, JSONException, java.text.ParseException {
	  
	  System.out.println("data received: " + inputObj);
	  boolean flag=false;

	  JSONObject jsonObject = null;
	  jsonObject = (JSONObject) parser.parse(inputObj);
	  String status = (String) jsonObject.get("params");
	  String name = (String) jsonObject.get("name");
	  Registration registration = new Registration(jsonObject);
	  
	  RegistrationDao dao = new RegistrationDao();
	  try {
			flag = dao.actionUser(jsonObject, factory);
			
			if (flag) {

				MailDao mailDao = new MailDao();
				
				mailDao.doProcess(request,registration.getEmail(),
						status,name);
				jsonObject.put("response", "success");
			} else {
				jsonObject.put("response", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	  return Response.status(200).entity(jsonObject).build();
	 }
	
//***************************delete User**************************
	@RequestMapping(value = "/deleteStatus/", method = RequestMethod.POST)
	public Response deleteStatus(@RequestBody String inputObj)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		
		System.out.println("data received: " + inputObj);
		boolean flag=false;

		JSONObject jsonObject = null;
		jsonObject = (JSONObject) parser.parse(inputObj);
		
		RegistrationDao dao = new RegistrationDao();
		try {
			flag = dao.deleteUser(jsonObject, factory);
			
			if (flag) {
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
//#########################MailSending##################################################
	@RequestMapping(value = "/SendMail/", method = RequestMethod.POST)
	 public Response SendMail(@RequestBody String inputObj, HttpServletRequest request)
	   throws JsonParseException, JsonMappingException, IOException,
	   ParseException, JSONException, java.text.ParseException {
	  
	  System.out.println("data received: " + inputObj);
	  boolean flag=false;

	  JSONObject jsonObject = null;
	  jsonObject = (JSONObject) parser.parse(inputObj);
	  String email = (String) jsonObject.get("email");
	  String sub = (String) jsonObject.get("sub");
	  String msg = (String) jsonObject.get("msg");
	
	  try {

				MailDao mailDao = new MailDao();
				
				mailDao.doMail(request,email,
						sub,msg);
				jsonObject.put("response", "success");
			
				jsonObject.put("response", "fail");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	  return Response.status(200).entity(jsonObject).build();
	 }
	
	
}
