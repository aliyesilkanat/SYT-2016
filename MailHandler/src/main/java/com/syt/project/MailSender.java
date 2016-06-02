package com.syt.project;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailSender {
	private static final Logger logger = LogManager.getLogger(MailSender.class);
	private static String USER_NAME = "syt.izel.ali@gmail.com"; // GMail user
																// name (just
																// the part
																// before
																// "@gmail.com")
	private static String PASSWORD = "sytharika"; // GMail password

	// private static String RECIPIENT = "izel.cavusoglu@gmail.com";

	// public static void main(String[] args) {
	// String from = USER_NAME;
	// String pass = PASSWORD;
	// String[] to = { RECIPIENT }; // list of recipient email addresses
	// String subject = "Java send mail example";
	// String body = "Welcome to JavaMail!";
	//
	// sendFromGMail(from, pass, to, subject, body);
	// }

	public void sendEmail(String recipient, String body) {
		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { recipient };
		String subject = "SYT Projesi";
		sendFromGMail(from, pass, to, subject, body);
	}

	private void sendFromGMail(String from, String pass, String[] to,
			String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			getLogger().error("wrong address", ae);
		} catch (MessagingException me) {
			getLogger().error("exception while messaging", me);
		}
	}

	public static Logger getLogger() {
		return logger;
	}
}