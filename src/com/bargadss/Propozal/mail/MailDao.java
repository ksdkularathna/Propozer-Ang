package com.bargadss.Propozal.mail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class MailDao {

	public void doProcess(HttpServletRequest request,String email,String name) throws ServletException, IOException {

		System.out.println("MailServlet called..");
		
		System.out.println("email: "+email);
		System.out.println("name: "+name);
		
		//email="shanta.mahato21@gmail.com";
		
		System.out.println("email: "+email);
		
		String actKey = new StringBuffer(String.valueOf(System
				.currentTimeMillis())).reverse().toString();
		try {

			//String url = request.getRequestURL() + "";
			//url = url.substring(0, url.lastIndexOf("/"));
			//url += "/activation.jsp?q=" + actKey;
			
			String url="http://54.169.251.109:8080/Propozal/index.html#/activation/";
			
			url += email;
			
			System.out.println("url: "+url);
			
			String subject = "Registration @ propozer.lk";
			String message = "Dear User "
					+ name
					+ ",<br><br>"
					+ "You have successfully created your account with us!<br>"
					+ "Your account details are as following:<br><br><br>"
					+ "User Name : "
					+ email

					+ "<br><br>Click the link below to activate your account.<br><br><a href=\""
					+ url + "\" target=blank>" + url + "</a>";
			MailSender.sendMail(subject, message, email);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
