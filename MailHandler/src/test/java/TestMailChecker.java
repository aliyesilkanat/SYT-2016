import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestMailChecker {

	@Test
	public void testName() throws Exception {
		// change accordingly
		// change accordingly
		// change accordingly

		ArrayList<Email> check = new MailChecker().check("pop.gmail.com", "pop3", "syt.izel.ali@gmail.com", "sytharika");
		
		for (Email email : check) {
			System.out.println(email.getFrom());
			System.out.println(email.getSubject());
			System.out.println(email.getText());
			
			new MailSender().sendEmail("izel.cavusoglu@gmail.com","asdasd");
	
			
		}
	}
}
