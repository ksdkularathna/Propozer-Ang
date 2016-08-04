package com.bargadss.Propozal.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

	public static String sendMail(String Subject, String Content,
			String recipient) {
		int i = 0;
		try {
			Properties prop = MailProps.getProps();
			final String userName = MailProps.getUserName();
			final String password = MailProps.getPassword();
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					
					try {
						return new PasswordAuthentication(userName, password);
					} catch (Exception e) {
						
						System.out.println("inside exception");
						
						e.printStackTrace();
						return null;
					}					
					//return new PasswordAuthentication(userName, password);
				}
			};
			Session session = Session.getInstance(prop, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(userName));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					recipient));
			msg.setSubject(Subject);
			msg.setSentDate(new Date());
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(Content, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			Transport.send(msg);
			
			System.out.println("done");
			
		} catch (Exception e) {
			e.printStackTrace();
			//return e.toString();
		}
		return "SUCCESS";
	}

}
