package com.bargadss.Propozal.Admin.mail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MailServlet() {
		super();
	}

	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("MailServlet called..");
		
//		System.out.println("email: "+email);
//		System.out.println("name: "+name);
//		
//		email="shanta@bargadss.com";
		
		//System.out.println("email: "+email);

		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String actKey = new StringBuffer(String.valueOf(System
				.currentTimeMillis())).reverse().toString();
		try {

			String url = request.getRequestURL() + "";
			url = url.substring(0, url.lastIndexOf("/"));
			url += "/activation.jsp?q=" + actKey;
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
			response.sendRedirect("index.jsp");
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
