import static org.junit.Assert.*;

import org.junit.Test;


public class TestMailSender {
	
	@Test
	public void sas() throws Exception {
		new MailSender().sendEmail("izel.cavusoglu@gmail.com", "asdsadasdsd");
	}

}
