package com.syt.project;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		ArrayList<Email> emailList = new MailChecker().check("pop.gmail.com",
				"pop3", "syt.izel.ali@gmail.com", "sytharika");

		for (Email email : emailList) {
			System.out
					.println(email.getFrom() + " diyor ki:" + email.getText());
			ArrayList<Student> students = new EmailAnalyzer().getStudents(email
					.getText());

			if (students.size() != 0) {
				getLogger().trace("grup bulundu");
				new MailSender().sendEmail(email.getFrom(),
						"Grubunuz basarili sekilde kayit edildi.");

				// BURADA ARRAYLIST GRUP OLARAK MONGOYA KAYIT EDILCEK ONDAN
				// SONRA DA MAIL SENDER GRUBA ONAY MAILI GONDEREBILIR
			} else {
				getLogger().trace(email.getFrom());
				new MailSender()
						.sendEmail(email.getFrom(),
								"Email formatinizi duzeltip numara-ad soyad seklinde yaziniz.");

				System.out.println("grup bulunamadi");
			}

		}

	}

	public static Logger getLogger() {
		return logger;
	}

}
