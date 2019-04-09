package edu.psgv.sweng861;

import java.util.List;

import com.mindbodyonline.clients.api._0_5_1.class_service.ArrayOfClass;
import com.mindbodyonline.clients.api._0_5_1.class_service.ArrayOfInt;
import com.mindbodyonline.clients.api._0_5_1.class_service.Class;
import com.mindbodyonline.clients.api._0_5_1.class_service.ClassX0020Service;
import com.mindbodyonline.clients.api._0_5_1.class_service.ClassX0020ServiceSoap;
import com.mindbodyonline.clients.api._0_5_1.class_service.GetClassesRequest;
import com.mindbodyonline.clients.api._0_5_1.class_service.GetClassesResult;
import com.mindbodyonline.clients.api._0_5_1.class_service.SourceCredentials;
import com.mindbodyonline.clients.api._0_5_1.class_service.UserCredentials;
import com.mindbodyonline.clients.api._0_5_1.class_service.XMLDetailLevel;

public class GymClasses {

	public FitnessDatabase fitnessDB = new FitnessDatabase();
	
	public GymClasses() {
		GetClassesRequest request = new GetClassesRequest();
		request.setCurrentPageIndex(0);
		request.setPageSize(20);
		request.setXMLDetail(XMLDetailLevel.FULL);
		ArrayOfInt arrayOfInt = new ArrayOfInt();
		arrayOfInt.getInt().add(-99);
	
		// Source Credentials
		SourceCredentials credentials = new SourceCredentials();
		credentials.setSourceName("PSUProject");
		credentials.setPassword("54e+YDXd5OFwDYOci1eMFgvhYto=");
		credentials.setSiteIDs(arrayOfInt);
	
		// User Credentials
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUsername("Siteowner"); //PSUProject");
		userCredentials.setPassword("apitest1234"); //54e+YDXd5OFwDYOci1eMFgvhYto=");
		userCredentials.setSiteIDs(arrayOfInt);
	
		// Set Sources
		request.setSourceCredentials(credentials);
		request.setUserCredentials(userCredentials);
	
		// Get Class Service
		ClassX0020Service service = new ClassX0020Service();
		ClassX0020ServiceSoap soap = service.getClassX0020ServiceSoap();
		GetClassesResult result = soap.getClasses(request);
		ArrayOfClass sales = result.getClasses();
	
		// Iterate through retrieved classes
		if (sales != null && sales.getClazz() != null) {
			List<Class> classes = sales.getClazz();
			for(Class classInstance: classes) {
				fitnessDB.addClasses(classInstance);
				fitnessDB.addLocations(classInstance);
				fitnessDB.addTeachers(classInstance);
				fitnessDB.addDates(classInstance);
				fitnessDB.addStartTimes(classInstance);
				fitnessDB.addEndTimes(classInstance);
			}
		} else {
			System.out.println("No classes returned");
		}
	}
	
}
