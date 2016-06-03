package com.syt.project;
import static org.junit.Assert.*;

import org.junit.Test;

import com.syt.project.email.MailSender;


public class TestMailSender {
	
	@Test
	public void sas() throws Exception {
		new MailSender().sendEmail("aliyesilkanat@gmail.com", "asdsadasdsd");
	}

}
