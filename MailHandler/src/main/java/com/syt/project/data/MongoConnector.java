package com.syt.project.data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoConnector {

	private static final String MONGOLAB_PORT = "31551";
	private static final String MONGOLAB_IP = "ds031551.mlab.com";
	private static final String MONGOLAB_PASSWORD = "syt2016";
	private static final String MONGOLAB_USER = "admin";
	private static MongoClient client = null;

	public static MongoDatabase getDatabase() {

		if (client == null) {
			client = new MongoClient(new MongoClientURI("mongodb://"
					+ MONGOLAB_USER + ":" + MONGOLAB_PASSWORD + "@"
					+ MONGOLAB_IP + ":" + MONGOLAB_PORT + "/syt2016"));
		}
		return client.getDatabase("syt2016");
	}
}
