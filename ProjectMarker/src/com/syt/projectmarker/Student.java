package com.syt.projectmarker;

import org.bson.types.ObjectId;

public class Student {
	private String id;
	private String name;
	private ObjectId objectId;

	private int point;

	/**
	 * @param id
	 * @param name
	 * @param point
	 * @param objectId
	 */
	public Student(String id, String name, int point, ObjectId objectId) {
		this.id = id;
		this.name = name;
		this.setObjectId(objectId);
		this.point = point;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

}
