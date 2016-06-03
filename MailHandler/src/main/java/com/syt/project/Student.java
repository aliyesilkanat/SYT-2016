package com.syt.project;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.MongoCursor;
import com.syt.project.data.MongoConnector;

public class Student {

	public Student(String id, String name) {
		this.name = name;
		this.id = id;
	}

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
