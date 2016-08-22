package com.bargadss.Propozal.Admin.mail;

import java.util.Properties;

public class MailProps {

	public static final String UserName = "propzer";
	public static final String Password = "propoz@l";

	public static Properties getProps() {
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", "smtp.gmail.com");
		prop.setProperty("mail.smtp.port", "587");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.auth", "true");
		return prop;
	}

	public static String getPassword() {
		return Password;
	}

	public static String getUserName() {
		return UserName;
	}

}
