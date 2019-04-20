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
import com.mindbodyonline.clients.api._0_5_1.client_service.ArrayOfClientMembership;
import com.mindbodyonline.clients.api._0_5_1.client_service.ArrayOfVisit;
import com.mindbodyonline.clients.api._0_5_1.client_service.ClientMembership;
import com.mindbodyonline.clients.api._0_5_1.client_service.ClientX0020Service;
import com.mindbodyonline.clients.api._0_5_1.client_service.ClientX0020ServiceSoap;
import com.mindbodyonline.clients.api._0_5_1.client_service.GetActiveClientMembershipsRequest;
import com.mindbodyonline.clients.api._0_5_1.client_service.GetActiveClientMembershipsResult;
import com.mindbodyonline.clients.api._0_5_1.client_service.GetClientScheduleRequest;
import com.mindbodyonline.clients.api._0_5_1.client_service.GetClientScheduleResult;
import com.mindbodyonline.clients.api._0_5_1.client_service.Visit;

public class GymClasses {

	public FitnessDatabase fitnessDB = new FitnessDatabase();
	
	public GymClasses() {
		loadClasses();
		loadClients();
		loadClientMemberships();
	}

	private void loadClasses() {
		GetClassesRequest classRequest = new GetClassesRequest();
		classRequest.setCurrentPageIndex(0);
		classRequest.setPageSize(20);
		classRequest.setXMLDetail(XMLDetailLevel.FULL);
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
		classRequest.setSourceCredentials(credentials);
		classRequest.setUserCredentials(userCredentials);
	
		// Get Class Service
		ClassX0020Service classService = new ClassX0020Service();
		ClassX0020ServiceSoap classSoap = classService.getClassX0020ServiceSoap();
		GetClassesResult classResult = classSoap.getClasses(classRequest);
		ArrayOfClass classSales = classResult.getClasses();
	
		// Iterate through retrieved classes
		if (classSales != null && classSales.getClazz() != null) {
			List<Class> classes = classSales.getClazz();
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
	
	private void loadClients() {
		GetClientScheduleRequest clientRequest = new GetClientScheduleRequest();
		clientRequest.setCurrentPageIndex(0);
		clientRequest.setPageSize(20);
		clientRequest.setClientID("1212");
		com.mindbodyonline.clients.api._0_5_1.client_service.ArrayOfInt arrayOfInt = new com.mindbodyonline.clients.api._0_5_1.client_service.ArrayOfInt();
		arrayOfInt.getInt().add(-99);
	
		// Source Credentials
		com.mindbodyonline.clients.api._0_5_1.client_service.SourceCredentials credentials = new com.mindbodyonline.clients.api._0_5_1.client_service.SourceCredentials();
		credentials.setSourceName("PSUProject");
		credentials.setPassword("54e+YDXd5OFwDYOci1eMFgvhYto=");
		credentials.setSiteIDs(arrayOfInt);
	
		// User Credentials
		com.mindbodyonline.clients.api._0_5_1.client_service.UserCredentials userCredentials = new com.mindbodyonline.clients.api._0_5_1.client_service.UserCredentials();
		userCredentials.setUsername("Siteowner"); //PSUProject");
		userCredentials.setPassword("apitest1234"); //54e+YDXd5OFwDYOci1eMFgvhYto=");
		userCredentials.setSiteIDs(arrayOfInt);
		userCredentials.setLocationID(123);
	
		// Set Sources
		clientRequest.setSourceCredentials(credentials);
		clientRequest.setUserCredentials(userCredentials);
	
		// Get Client Service
		ClientX0020Service clientService = new ClientX0020Service();
		ClientX0020ServiceSoap clientSoap = clientService.getClientX0020ServiceSoap();
		GetClientScheduleResult clientResult = clientSoap.getClientSchedule(clientRequest);
		ArrayOfVisit clientSales = clientResult.getVisits();
		
		// Iterate through retrieved visits
		if (clientSales != null && clientSales.getClass() != null) {
			List<Visit> visits = clientSales.getVisit();
			for(Visit visitInstance: visits) {
				fitnessDB.addVisits(visitInstance);
			}
		} else {
			System.out.println("No visits returned");
		}
	}
	
	private void loadClientMemberships() {
		GetActiveClientMembershipsRequest clientRequest = new GetActiveClientMembershipsRequest();
		clientRequest.setCurrentPageIndex(0);
		clientRequest.setPageSize(20);
		clientRequest.setClientID("1212");
		com.mindbodyonline.clients.api._0_5_1.client_service.ArrayOfInt arrayOfInt = new com.mindbodyonline.clients.api._0_5_1.client_service.ArrayOfInt();
		arrayOfInt.getInt().add(-99);
	
		// Source Credentials
		com.mindbodyonline.clients.api._0_5_1.client_service.SourceCredentials credentials = new com.mindbodyonline.clients.api._0_5_1.client_service.SourceCredentials();
		credentials.setSourceName("PSUProject");
		credentials.setPassword("54e+YDXd5OFwDYOci1eMFgvhYto=");
		credentials.setSiteIDs(arrayOfInt);
	
		// User Credentials
		com.mindbodyonline.clients.api._0_5_1.client_service.UserCredentials userCredentials = new com.mindbodyonline.clients.api._0_5_1.client_service.UserCredentials();
		userCredentials.setUsername("Siteowner"); //PSUProject");
		userCredentials.setPassword("apitest1234"); //54e+YDXd5OFwDYOci1eMFgvhYto=");
		userCredentials.setSiteIDs(arrayOfInt);
		userCredentials.setLocationID(123);
	
		// Set Sources
		clientRequest.setSourceCredentials(credentials);
		clientRequest.setUserCredentials(userCredentials);
	
		// Get Client Service
		ClientX0020Service clientService = new ClientX0020Service();
		ClientX0020ServiceSoap clientSoap = clientService.getClientX0020ServiceSoap();
		GetActiveClientMembershipsResult clientResult = clientSoap.getActiveClientMemberships(clientRequest);
		ArrayOfClientMembership clientSales = clientResult.getClientMemberships();
		
		// Iterate through retrieved memberships
		if (clientSales != null && clientSales.getClass() != null) {
			List<ClientMembership> visits = clientSales.getClientMembership();
			for(ClientMembership membershipInstance: visits) {
				fitnessDB.addMemberships(membershipInstance);
			}
		} else {
			System.out.println("No memberships returned");
		}
	}
	
}
