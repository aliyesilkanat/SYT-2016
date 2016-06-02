package com.syt.project;
import static org.junit.Assert.*;

import org.junit.Test;

import com.syt.project.EmailAnalyzer;

public class TestEmailAnalyzer {
	@Test
	public void testName() throws Exception {
		System.out
				.println(new EmailAnalyzer()
						.getStudents("Merhaba Hocam SYT dersinin 2.proje grup üyeleri aþaðýdaki gibidir 5110000000-Adý Soyadý 5110000001-Adý Soyadý 5110000002-Adý Soyadý"));
	}
}
