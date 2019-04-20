package edu.psgv.sweng861.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.psgv.sweng861.ClassObject;
import edu.psgv.sweng861.GymClasses;

public class ProfilePanelChange extends JPanel {

	private static final long serialVersionUID = 1L;

	private GymClasses gymClasses = new GymClasses();
	private DB fitnessDB = gymClasses.fitnessDB.database;
	private int i = 15;
	private int count = 0;
	private int start = 0;
	
	public void createLabel() {
		DBCollection visits = fitnessDB.getCollection("Visits");
		DBCollection memberships = fitnessDB.getCollection("Memberships");

		DBCursor visitsCursor = visits.find();
		DBCursor membershipsCursor = memberships.find();
		
        try {
        	count = 0;
        	
        	if (visits.count() > 0) {
        		JLabel pastClassesLabel = new JLabel();
        		pastClassesLabel.setText("Past classes taken:");
        		pastClassesLabel.setBounds(5, 0, 200, 20);
        		this.add(pastClassesLabel);
        		
        		while(visitsCursor.hasNext()) {
        			DBObject visitObj = visitsCursor.next();
        			
        			JLabel visitsLabel = new JLabel();
        			visitsLabel.setText("Class: " + visitObj.get("Visit") + " at " + visitObj.get("Location")
        			+ " on " + visitObj.get("Date") + " from " + visitObj.get("Start") 
        			+ " until " + visitObj.get("End"));
        			visitsLabel.setBounds(50, 0+i, 1000, 20);
        			this.add(visitsLabel);
        			
        			i+=15;
        			count++;
        			if (count == visits.count()) {
        				break;
        			}
        		}
        	} else {
				JLabel noPastClassesLabel = new JLabel();
				noPastClassesLabel.setText("No classes taken yet.");
				noPastClassesLabel.setBounds(5, 0, 200, 20);
				this.add(noPastClassesLabel); 
        	}
        } finally {
            visitsCursor.close();
        }
        
		try { 
			if (memberships.count() > 0) { 
				JLabel membershipsLabel = new JLabel();
				membershipsLabel.setText("Memberships:"); 
				membershipsLabel.setBounds(5, 15+i, 1000, 20);
				this.add(membershipsLabel); 
				i+=30;
			
				while(membershipsCursor.hasNext()) { 
					DBObject membershipObj = membershipsCursor.next();
			 
					JLabel membershipLabel = new JLabel();
					membershipLabel.setText(membershipObj.get("Name") + " from " +
							membershipObj.get("Start") + " until " + membershipObj.get("End"));
					membershipLabel.setBounds(50, 0+i, 1000, 20);
					this.add(membershipLabel);
			  
					i+=15; 
					count++; 
					
					if (count == memberships.count()) { 
						break; 
					}
				}
			} else {
				JLabel noMembershipsLabel = new JLabel();
				noMembershipsLabel.setText("No current memberships.");
				noMembershipsLabel.setBounds(5, 15+i, 1000, 20);
				this.add(noMembershipsLabel); 
			} 
		} finally { 
			visitsCursor.close(); 
		}
		
    	JLabel upcomingClassesLabel = new JLabel();
    	upcomingClassesLabel.setBounds(5, 15+i+i, 200, 20);
    	upcomingClassesLabel.setText("No upcoming classes.");
    	this.add(upcomingClassesLabel);
    	i+=60;
    	count = 0;
    	start = i;
	}
	
	public void addUpcomingClasses(ClassObject classObject) {
		String text = classObject.getName() + " at " + classObject.getLocation() +
				" on " + classObject.getDate() + " from " + classObject.getStartTime() +
				" until " + classObject.getEndTime();
		if (classObject.isBooked()) {
			JLabel classesLabel = new JLabel();
			classesLabel.setName(classObject.getName());
			classesLabel.setText(text);
			classesLabel.setBounds(50, 0+i, 1000, 20);
			this.add(classesLabel);
			
			i+=15;
			count++;
    	} else {
    		for (Component jc : this.getComponents()) {
    		    if (jc instanceof JLabel) {
    		    	JLabel classToRemoveLabel = (JLabel) jc;
    		    	if (classToRemoveLabel.getText().equals(text)) {
    		    		this.remove(classToRemoveLabel);
    		    		count--;
    		    	}
    		    }
    		}
    	}
		if (count > 0) {
    		for (Component jc : this.getComponents()) {
    		    if (jc instanceof JLabel) {
    		    	JLabel upcomingClassesLabel = (JLabel) jc;
    		    	if (upcomingClassesLabel.getText().equals("No upcoming classes.")) {
    		    		upcomingClassesLabel.setText("Upcoming classes:");
    		    	}
    		    }
    		}
		} else {
    		for (Component jc : this.getComponents()) {
    		    if (jc instanceof JLabel) {
    		    	JLabel upcomingClassesLabel = (JLabel) jc;
    		    	if (upcomingClassesLabel.getText().equals("Upcoming classes:")) {
    		    		upcomingClassesLabel.setText("No upcoming classes.");
    		    		i = start;
    		    	}
    		    }
    		}
		}
	}
	
}
