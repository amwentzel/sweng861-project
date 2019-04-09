package edu.psgv.sweng861;

import com.mindbodyonline.clients.api._0_5_1.class_service.Class;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class FitnessDatabase {

	public DB database;
//	public MongoDatabase database;
	
	@SuppressWarnings("deprecation")
	public FitnessDatabase() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDB("myMongoDb");
//		database = mongoClient.getDatabase("myMongoDb");
		database.getCollection("Classes").drop();
		database.getCollection("Teachers").drop();
		database.getCollection("Locations").drop();
		database.getCollection("Dates").drop();
		database.getCollection("StartTimes").drop();
		database.getCollection("EndTimes").drop();
		
		database.createCollection("Classes", null);
		database.createCollection("Teachers", null);
		database.createCollection("Locations", null);
		database.createCollection("Dates", null);
		database.createCollection("StartTimes", null);
		database.createCollection("EndTimes", null);
	}
	
	public void addClasses(Class classInstance) {
		DBCollection collection = database.getCollection("Classes");
		BasicDBObject document = new BasicDBObject();
		document.put("Class", classInstance.getClassDescription().getName());
		collection.insert(document);
	}
	
	public void addLocations(Class classInstance) {
		DBCollection collection = database.getCollection("Locations");
		BasicDBObject document = new BasicDBObject();
		document.put("Location", classInstance.getLocation().getAddress());
		collection.insert(document);
	}
	
	public void addTeachers(Class classInstance) {
		DBCollection collection = database.getCollection("Teachers");
		BasicDBObject document = new BasicDBObject();
		String teacher = classInstance.getStaff().getFirstName() + " " + classInstance.getStaff().getLastName();
		document.put("Teacher", teacher);
		collection.insert(document);
	}

	public void addDates(Class classInstance) {
		DBCollection collection = database.getCollection("Dates");
		BasicDBObject document = new BasicDBObject();
		String date = classInstance.getStartDateTime().getValue().getMonth() + "-" +
				classInstance.getStartDateTime().getValue().getDay() + "-" +
				classInstance.getStartDateTime().getValue().getYear();
		document.put("Date", date);
		collection.insert(document);
	}
	
	public void addStartTimes(Class classInstance) {
		DBCollection collection = database.getCollection("StartTimes");
		BasicDBObject document = new BasicDBObject();
		String start = classInstance.getStartDateTime().getValue().getHour() + ":" +
				classInstance.getStartDateTime().getValue().getMinute();
		if (classInstance.getStartDateTime().getValue().getMinute() == 0) {
			start+="0";
		}
		document.put("Start", start);
		collection.insert(document);
	}
	
	public void addEndTimes(Class classInstance) {
		DBCollection collection = database.getCollection("EndTimes");
		BasicDBObject document = new BasicDBObject();
		String end = classInstance.getEndDateTime().getValue().getHour() + ":" +
				classInstance.getEndDateTime().getValue().getMinute();
		if (classInstance.getEndDateTime().getValue().getMinute() == 0) {
			end+="0";
		}
		document.put("End", end);
		collection.insert(document);
	}
	
}
