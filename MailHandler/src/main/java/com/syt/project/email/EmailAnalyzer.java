package com.syt.project.email;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.syt.project.Student;

public class EmailAnalyzer {

	private static final Logger logger = LogManager
			.getLogger(EmailAnalyzer.class);

	public ArrayList<Student> getStudents(String Text) {

		ArrayList<Student> group = new ArrayList<Student>();

		Pattern pattern = Pattern.compile("\\d{10}-(\\p{L}|\\s)*");
		Matcher matcher = pattern.matcher(Text);
		while (matcher.find()) {
			if (getLogger().isTraceEnabled()) {
				getLogger().trace(matcher.group());
			}
			String[] parts = matcher.group().split("-");

			group.add(new Student(parts[0], parts[1]));
		}

		return group;

	}

	public static Logger getLogger() {
		return logger;
	}

}
