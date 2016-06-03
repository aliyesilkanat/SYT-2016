package com.syt.project;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.syt.project.data.DataFunctions;
import com.syt.project.email.Email;
import com.syt.project.email.EmailAnalyzer;
import com.syt.project.email.MailChecker;
import com.syt.project.email.MailSender;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		final ExecutorService threadPool = Executors.newFixedThreadPool(5);
		ScheduledExecutorService emailFetchingThread = Executors
				.newScheduledThreadPool(1);
		emailFetchingThread.scheduleAtFixedRate(new Runnable() {
			public void run() {
				ArrayList<Email> emailList = new MailChecker().check(
						"pop.gmail.com", "pop3", "syt.izel.ali@gmail.com",
						"sytharika");
				for (final Email email : emailList) {
					threadPool.execute(new Runnable() {
						public void run() {
							getLogger().trace(
									email.getFrom() + " diyor ki:"
											+ email.getText());
							ArrayList<Student> students = new EmailAnalyzer()
									.getStudents(email.getText());
							if (students.size() != 0) {
								getLogger().info("grup bulundu");
								new DataFunctions().addNewGroup(students);
								getLogger().trace(
										"successfully inserted new students");
								new MailSender().sendEmail(email.getFrom(),
										"Grubunuz basarili sekilde kayit edildi.");
							} else {
								getLogger().trace(email.getFrom());
								new MailSender().sendEmail(email.getFrom(),
										"Email formatinizi duzeltip numara-ad soyad seklinde yaziniz.");

								getLogger().debug("grup bulunamadi");
							}
						}
					});

				}
			}
		}, 0, 1, TimeUnit.DAYS);

	}

	public static Logger getLogger() {
		return logger;
	}

}
