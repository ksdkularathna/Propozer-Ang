package com.bargadss.Propozal.Admin.mail;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class MailDao {

	public void doProcess(HttpServletRequest request,String email,String status,String name) throws ServletException, IOException {

		System.out.println("MailServlet called..");
		
		System.out.println("email: "+email);
		System.out.println("name: "+status);
		
		//email="shanta.mahato21@gmail.com";
		
		Date date = new Date();
		String date1 =date.toString();
		
		String actKey = new StringBuffer(String.valueOf(System
				.currentTimeMillis())).reverse().toString();
		try {

			
			String url="http://54.169.251.109:8080/Propozal/index.html";
			
			
			
			System.out.println("url: "+url);
			if(status.equals("deactivate")){
			String subject = "Registration @ propozer.lk";
			String message = "Dear User "
					+ name
					+ ",<br><br>"
					+ "Your account has been successfully activated with<br>"
					+ "User Name : "
					+ email
					+ "<br><br>Click the link below to login in your proposal  account.<br><br><a href=\""
					+ url + "\" target=blank>" + url + "</a>";
			MailSender.sendMail(subject, message, email);
			
		}
		else if(status.equals("activate")){
			String subject = "Registration @ propozer.lk";
			String message = "Dear User "
					+ name
					+ ",<br><br>"
					+ "Your account has been deactivated due to violance to our rules and regulations<br>"
					+ "User Name : "
					+ email+"";
			MailSender.sendMail(subject, message, email);
		}
		else{
			
			String subject = "Registration @ propozer.lk";
			String message = "Dear User "
					+ email
					+ ",<br><br>"
					+ "Your account has been deactivated due to violance to our rules and regulations<br>"
					+ "User Name : "
					+ email+"";
			MailSender.sendMail(subject, message, email);
		}
		
			}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void doMail(HttpServletRequest request,String email,String sub,String msg) throws ServletException, IOException {

		System.out.println("MailServlet called..");
		
		System.out.println("email: "+email);
		System.out.println("subject: "+sub);
		System.out.println("msg: "+msg);
		
		
		
		try {
			
			String subject =sub ;
			String message = msg;
			MailSender.sendMail(subject, message, email);
			
		
			}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


}
