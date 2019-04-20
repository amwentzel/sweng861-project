package edu.psgv.sweng861;

import com.mindbodyonline.clients.api._0_5_1.appointment_service.Option;
import com.mindbodyonline.clients.api._0_5_1.class_service.Class;
import com.mindbodyonline.clients.api._0_5_1.client_service.ClientMembership;
import com.mindbodyonline.clients.api._0_5_1.client_service.Visit;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class FitnessDatabase {

	public DB database;
	
	@SuppressWarnings("deprecation")
	public FitnessDatabase() {
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDB("myMongoDb");
		database.getCollection("Classes").drop();
		database.getCollection("Teachers").drop();
		database.getCollection("Locations").drop();
		database.getCollection("Dates").drop();
		database.getCollection("StartTimes").drop();
		database.getCollection("EndTimes").drop();
		database.getCollection("Visits").drop();
		database.getCollection("Memberships").drop();
		
		database.createCollection("Classes", null);
		database.createCollection("Teachers", null);
		database.createCollection("Locations", null);
		database.createCollection("Dates", null);
		database.createCollection("StartTimes", null);
		database.createCollection("EndTimes", null);
		database.createCollection("Visits", null);
		database.createCollection("Memberships", null);
	}
	
	public void addClasses(Class classInstance) {
		DBCollection collection = database.getCollection("Classes");
		BasicDBObject document = new BasicDBObject();
		document.put("Class", classInstance.getClassDescription().getName());
		document.put("Booked", "false");
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
		int hour = classInstance.getStartDateTime().getValue().getHour();
		if (hour > 12) {
			hour-=12;
		}
		String start = hour + ":" + classInstance.getStartDateTime().getValue().getMinute();
		if (classInstance.getStartDateTime().getValue().getMinute() == 0) {
			start+="0";
		}
		if (classInstance.getStartDateTime().getValue().getHour() < 12) {
			start+="am";
		} else {
			start+="pm";
		}
		document.put("Start", start);
		collection.insert(document);
	}
	
	public void addEndTimes(Class classInstance) {
		DBCollection collection = database.getCollection("EndTimes");
		BasicDBObject document = new BasicDBObject();
		int hour = classInstance.getEndDateTime().getValue().getHour();
		if (hour > 12) {
			hour-=12;
		}
		String end = hour + ":" + classInstance.getEndDateTime().getValue().getMinute();
		if (classInstance.getEndDateTime().getValue().getMinute() == 0) {
			end+="0";
		}
		if (classInstance.getEndDateTime().getValue().getHour() < 12) {
			end+="am";
		} else {
			end+="pm";
		}
		document.put("End", end);
		collection.insert(document);
	}

	public void addVisits(Visit visitInstance) {
		DBCollection collection = database.getCollection("Visits");
		BasicDBObject document = new BasicDBObject();
		document.put("Visit", visitInstance.getName());
		document.put("Location", visitInstance.getLocation().getAddress());
		
		String date = visitInstance.getStartDateTime().getMonth() + "-" +
				visitInstance.getStartDateTime().getDay() + "-" +
				visitInstance.getStartDateTime().getYear();
		document.put("Date", date);
		
		int startHour = visitInstance.getStartDateTime().getHour();
		if (startHour > 12) {
			startHour-=12;
		}
		String start = startHour + ":" + visitInstance.getStartDateTime().getMinute();
		if (visitInstance.getStartDateTime().getMinute() == 0) {
			start+="0";
		}
		if (visitInstance.getStartDateTime().getHour() < 12) {
			start+="am";
		} else {
			start+="pm";
		}
		document.put("Start", start);
		
		int endHour = visitInstance.getEndDateTime().getHour();
		if (endHour > 12) {
			endHour-=12;
		}
		String end = endHour + ":" + visitInstance.getEndDateTime().getMinute();
		if (visitInstance.getEndDateTime().getMinute() == 0) {
			end+="0";
		}
		if (visitInstance.getEndDateTime().getHour() < 12) {
			end+="am";
		} else {
			end+="pm";
		}
		document.put("End", end);
		
		collection.insert(document);
	}

	public void addMemberships(ClientMembership membershipInstance) {
		DBCollection collection = database.getCollection("Memberships");
		BasicDBObject document = new BasicDBObject();
		document.put("Name", membershipInstance.getName());
		
		String start = membershipInstance.getActiveDate().getValue().getMonth() + "-" +
				membershipInstance.getActiveDate().getValue().getDay() + "-" +
				membershipInstance.getActiveDate().getValue().getYear();
		document.put("Start", start);
		
		String end = membershipInstance.getExpirationDate().getValue().getMonth() + "-" +
				membershipInstance.getExpirationDate().getValue().getDay() + "-" +
				membershipInstance.getExpirationDate().getValue().getYear();
		document.put("End", end);
		
		collection.insert(document);
	}

	public void addAppointments(Option optionInstance) {
		optionInstance.getName();
	}
	
}
