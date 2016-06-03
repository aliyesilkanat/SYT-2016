package com.syt.projectmarker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

@ManagedBean(name = "students")
@ViewScoped
public class StudentsBean {

	private static final String STUDENTS = "Students";
	private static final String _ID = "_id";
	private static final String POINTS = "points";
	private static final String NAME = "name";
	private static final String STUDENT_ID = "studentId";
	private List<Group> groupList = new ArrayList<Group>();

	public StudentsBean() {
		Document filterAggr = new Document("$group", new Document(_ID,
				"$groupId").append("students", new Document("$push", "$$ROOT")));
		MongoCursor<Document> iterator = MongoConnector.getDatabase()
				.getCollection(STUDENTS).aggregate(Arrays.asList(filterAggr))
				.iterator();
		while (iterator.hasNext()) {
			Document groupDocument = (Document) iterator.next();
			List<Student> studentsList = new ArrayList<Student>();
			Iterator<Document> studentsIterator = ((List<Document>) groupDocument
					.get("students")).iterator();
			while (studentsIterator.hasNext()) {
				Document studentDocument = (Document) studentsIterator.next();
				studentsList.add(new Student(studentDocument
						.getString(STUDENT_ID),
						studentDocument.getString(NAME), studentDocument
								.getInteger(POINTS), studentDocument
								.getObjectId(_ID)));
			}

			getGroupList().add(
					new Group(groupDocument.getObjectId(_ID), studentsList));

		}
	}

	public void change() {
		for (Group group : groupList) {
			for (Student student : group.getStudentsList()) {

				MongoConnector
						.getDatabase()
						.getCollection(STUDENTS)
						.updateOne(
								new Document(_ID, student.getObjectId()),
								new Document("$set", new Document(NAME, student
										.getName()).append(STUDENT_ID,
										student.getId()).append(POINTS,
										student.getPoint())));
			}
		}
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
}