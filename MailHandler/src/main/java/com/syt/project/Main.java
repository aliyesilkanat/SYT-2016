package com.syt.project;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		ArrayList<Email> emailList = new MailChecker().check("pop.gmail.com",
				"pop3", "syt.izel.ali@gmail.com", "sytharika");

		for (Email email : emailList) {
			System.out
					.println(email.getFrom() + " diyor ki:" + email.getText());
			ArrayList<Student> students = new EmailAnalyzer().getStudents(email
					.getText());

			if (students.size() != 0) {
				System.out.println("grup bulundu");
				// new MailSender().sendEmail(email.getFrom(),
				// "Grubunuz basarili sekilde kayit edildi.");
				
				// Mail sender tek basina calsiiyor fakat mail checkerdan sonra
				// calismiyor. Ayni kaynaklari kullanmaya calsiiyorlar sanirim
				
				
				// BURADA ARRAYLIST GRUP OLARAK MONGOYA KAYIT EDILCEK ONDAN
				// SONRA DA MAIL SENDER GRUBA ONAY MAILI GONDEREBILIR
			} else {
				System.out.println(email.getFrom());
				// new
				// MailSender().sendEmail(email.getFrom(),"Email formatinizi duzeltip numara-ad soyad seklinde yaziniz.");
				// Mail sender tek basina calsiiyor fakat mail checkerdan sonra
				// calismiyor. Ayni kaynaklari kullanmaya calsiiyorlar sanirim
				System.out.println("grup bulunamadi");
			}

		}

	}

}
