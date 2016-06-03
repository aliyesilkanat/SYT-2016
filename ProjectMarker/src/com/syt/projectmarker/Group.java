package com.syt.projectmarker;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

public class Group {
	private ObjectId id;
	private List<Student> studentsList = new ArrayList<Student>();

	/**
	 * @param id
	 * @param studentsList
	 */
	public Group(ObjectId id, List<Student> studentsList) {
		super();
		this.setId(id);
		this.setStudentsList(studentsList);
	}

	public ObjectId getId() {
		return id;
	}

	public List<Student> getStudentsList() {
		return studentsList;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setStudentsList(List<Student> studentsList) {
		this.studentsList = studentsList;
	}
}
