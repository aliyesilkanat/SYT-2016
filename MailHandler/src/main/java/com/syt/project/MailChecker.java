package com.syt.project;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

public class MailChecker {
	private static final Logger logger = LogManager
			.getLogger(MailChecker.class);

	private String getTextFromMessage(Message message) throws Exception {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			String result = "";
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			int count = mimeMultipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart bodyPart = mimeMultipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					result = result + "\n" + bodyPart.getContent();
					break; // without break same text appears twice in my tests
				} else if (bodyPart.isMimeType("text/html")) {
					String html = (String) bodyPart.getContent();
					result = result + "\n" + Jsoup.parse(html).text();

				}
			}
			return result;
		}
		return "";
	}

	public ArrayList<Email> check(String host, String storeType, String user,
			String password) {
		try {

			// create properties field
			Properties properties = new Properties();

			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();

			if (getLogger().isTraceEnabled()) {
				getLogger().trace("messages.length---" + messages.length);
			}
			ArrayList<Email> emails = new ArrayList<Email>();

			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				emails.add(new Email(message.getSubject(), message.getFrom()[0]
						.toString().split("<")[1].replace(">", ""),
						getTextFromMessage(message)));

			}

			// close the store and folder objectsM
			emailFolder.close(false);
			store.close();

			return emails;

		} catch (Exception e) {
			getLogger().error("Cannot read mails", e);
		}
		return null;
	}

	public static Logger getLogger() {
		return logger;
	}

}