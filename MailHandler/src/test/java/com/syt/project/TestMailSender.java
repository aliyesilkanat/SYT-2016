package com.syt.project;
import static org.junit.Assert.*;

import org.junit.Test;

import com.syt.project.MailSender;


public class TestMailSender {
	
	@Test
	public void sas() throws Exception {
		new MailSender().sendEmail("izel.cavusoglu@gmail.com", "asdsadasdsd");
	}

}
