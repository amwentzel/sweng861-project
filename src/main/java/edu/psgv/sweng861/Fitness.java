package edu.psgv.sweng861;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Fitness {

	@SuppressWarnings("deprecation")
	public void mongoSetup() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB database = mongoClient.getDB("myMongoDb");
//		boolean auth = database.authenticate("username", "pwd".toCharArray());
		mongoClient.getDatabaseNames().forEach(System.out::println);
		
		addCollections(database);
	}
	
	private void addCollections(DB database) {
		database.createCollection("gyms", null);
		addGymsToCollection(database);
		database.createCollection("schedules", null);
		addSchedulesToCollection(database);
		database.createCollection("health", null);
		addHealthToCollection(database);
		database.getCollectionNames().forEach(System.out::println);
	}
	
	private void addGymsToCollection(DB database) {
		DBCollection collection = database.getCollection("gyms");
		BasicDBObject document = new BasicDBObject();
		document.put("gym", "Aerial Fitness LLC");
		collection.insert(document);
	}

	private void addSchedulesToCollection(DB database) {
		DBCollection collection = database.getCollection("schedules");
		BasicDBObject document = new BasicDBObject();
		document.put("gym", "Aerial Fitness LLC");
		document.put("monday", "5pm, 6pm, 7pm");
		document.put("tuesday", "4:30pm, 6pm");
		document.put("price", "$25");
		collection.insert(document);
	}

	private void addHealthToCollection(DB database) {
		DBCollection collection = database.getCollection("health");
		BasicDBObject document = new BasicDBObject();
		document.put("calories burned", "500");
		document.put("day", "monday 25th");
		document.put("tuesday", "4:30pm, 6pm");
		collection.insert(document);
	}
	
}
