package edu.psgv.sweng861;

import com.mindbodyonline.clients.api._0_5_1.class_service.Class;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class FitnessDatabase {

	public DB database;
	
	@SuppressWarnings("deprecation")
	public FitnessDatabase() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDB("myMongoDb");
	}
	
	@SuppressWarnings("deprecation")
	public void mongoSetup() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDB("myMongoDb");
//		boolean auth = database.authenticate("username", "pwd".toCharArray());
		mongoClient.getDatabaseNames().forEach(System.out::println);
		
		addCollections();
	}
	
	public void addClasses(Class classInstance) {
		database.createCollection("Classes", null);
		DBCollection collection = database.getCollection("Classes");
		BasicDBObject document = new BasicDBObject();
		document.put("Class", classInstance.getClassDescription().getName());
		collection.insert(document);
	}
	
	private void addCollections() {
		database.createCollection("gyms", null);
		addGymsToCollection();
		database.createCollection("schedules", null);
		addSchedulesToCollection();
		database.createCollection("health", null);
		addHealthToCollection();
		database.getCollectionNames().forEach(System.out::println);
	}
	
	private void addGymsToCollection() {
		DBCollection collection = database.getCollection("gyms");
		BasicDBObject document = new BasicDBObject();
		document.put("gym", "Aerial Fitness LLC");
		collection.insert(document);
	}

	private void addSchedulesToCollection() {
		DBCollection collection = database.getCollection("schedules");
		BasicDBObject document = new BasicDBObject();
		document.put("gym", "Aerial Fitness LLC");
		document.put("monday", "5pm, 6pm, 7pm");
		document.put("tuesday", "4:30pm, 6pm");
		document.put("price", "$25");
		collection.insert(document);
	}

	private void addHealthToCollection() {
		DBCollection collection = database.getCollection("health");
		BasicDBObject document = new BasicDBObject();
		document.put("calories burned", "500");
		document.put("day", "monday 25th");
		document.put("tuesday", "4:30pm, 6pm");
		collection.insert(document);
	}
	
}
