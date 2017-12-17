package com.syt.project.data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoConnector {

	private static final String MONGOLAB_PORT = ;
	private static final String MONGOLAB_IP = "
	private static final String MONGOLAB_PASSWORD = ;
	private static final String MONGOLAB_USER = ;
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
