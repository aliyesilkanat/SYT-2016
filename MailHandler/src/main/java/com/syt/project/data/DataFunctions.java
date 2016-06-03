package com.syt.project.data;

import java.util.ArrayList;

import javax.xml.crypto.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.syt.project.Main;
import com.syt.project.Student;

public class DataFunctions {
	private static final String GROUP_ID = "groupId";
	private static final String STUDENT_ID = "studentId";
	private static final String NAME = "name";
	private static final String STUDENTS = "Students";
	private static final String POINTS = "points";
	private static final String IS_CHECKED = "isChecked";
	private static final String GROUPS_COLLECTION = "Groups";

	private static final Logger logger = LogManager
			.getLogger(DataFunctions.class);

	public void addNewGroup(ArrayList<Student> students) {
		getLogger().trace("inserting new group");
		Document document = new Document(POINTS, 0).append(IS_CHECKED, false);
		MongoConnector.getDatabase().getCollection(GROUPS_COLLECTION)
				.insertOne(document);
		ObjectId groupId = document.getObjectId("_id");
		addStudents(students, groupId);
		getLogger().debug("inserted students and group");
	}

	private void addStudents(ArrayList<Student> students, ObjectId groupId) {
		getLogger().trace("inserting new students, size: {}, groupId: {}",
				students.size(), groupId);
		for (Student student : students) {
			Document document = new Document(NAME, student.getName())
					.append(STUDENT_ID, student.getId())
					.append(GROUP_ID, groupId).append(POINTS, 0)
					.append(IS_CHECKED, false);
			MongoConnector.getDatabase().getCollection(STUDENTS)
					.insertOne(document);
		}

	}

	public static Logger getLogger() {
		return logger;
	}
}
