package edu.psgv.sweng861.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.psgv.sweng861.GymClasses;

public class VisitsPanelChange extends JPanel {

	private static final long serialVersionUID = 1L;

	private GymClasses gymClasses = new GymClasses();
	
	public VisitsPanelChange createLabel(VisitsPanelChange panelChange) {
		DB fitnessDB = gymClasses.fitnessDB.database;

		DBCollection classes = fitnessDB.getCollection("Classes");
		DBCollection visits = fitnessDB.getCollection("Visits");
		DBCollection memberships = fitnessDB.getCollection("Memberships");
		
		DBCursor classesCursor = classes.find();
		DBCursor visitsCursor = visits.find();
		DBCursor membershipsCursor = memberships.find();
		
		int i = 15;
		int count = 0;
		
		try {
    		JLabel pastClassesLabel = new JLabel();
    		pastClassesLabel.setText("Upcoming classes:");
    		pastClassesLabel.setBounds(5, 0, 200, 20);
    		panelChange.add(pastClassesLabel);
    		
    		while(classesCursor.hasNext()) {
    			DBObject classObj = classesCursor.next();
    			
    			if ("true".equals(classObj.get("Booked"))) {
    				JLabel classesLabel = new JLabel();
        			classesLabel.setText(classObj.get("Class").toString());
        			classesLabel.setBounds(50, 0+i, 1000, 20);
        			panelChange.add(classesLabel);
        			
        			i+=15;
        			count++;
    			}
    			if (count == classes.count()) {
    				break;
    			}
        	}
    		if (count == 0) {
    			pastClassesLabel.setText("No upcoming classes.");
//    			panelChange.add(pastClassesLabel); 
    		}
        } finally {
        	classesCursor.close();
        }
		
        try {
        	if (visits.count() > 0) {
        		JLabel pastClassesLabel = new JLabel();
        		pastClassesLabel.setText("Past classes taken:");
        		pastClassesLabel.setBounds(5, 0+i, 200, 20);
        		panelChange.add(pastClassesLabel);
        		
        		while(visitsCursor.hasNext()) {
        			DBObject visitObj = visitsCursor.next();
        			
        			JLabel visitsLabel = new JLabel();
        			visitsLabel.setText(visitObj.get("Visit") + " at " + visitObj.get("Location")
        			+ " on " + visitObj.get("Date") + " from " + visitObj.get("Start") 
        			+ " until " + visitObj.get("End"));
        			visitsLabel.setBounds(50, 0+i, 1000, 20);
        			panelChange.add(visitsLabel);
        			
        			i+=15;
        			count++;
        			if (count == visits.count()) {
        				break;
        			}
        		}
        	} else {
				JLabel noPastClassesLabel = new JLabel();
				noPastClassesLabel.setText("No classes taken yet.");
				noPastClassesLabel.setBounds(5, 0+i, 200, 20);
				panelChange.add(noPastClassesLabel); 
        	}
        } finally {
            visitsCursor.close();
        }
        
		try { 
			if (memberships.count() > 0) { 
				JLabel membershipsLabel = new JLabel();
				membershipsLabel.setText("Memberships:"); 
				membershipsLabel.setBounds(5, 0+i+i, 1000, 20);
				panelChange.add(membershipsLabel); 
				i+=30;
			
				while(membershipsCursor.hasNext()) { 
					DBObject membershipObj = membershipsCursor.next();
			 
					JLabel membershipLabel = new JLabel();
					membershipLabel.setText(membershipObj.get("Name") + " from " +
							membershipObj.get("Start") + " until " + membershipObj.get("End"));
					membershipLabel.setBounds(50, 0+i, 1000, 20);
					panelChange.add(membershipLabel);
			  
					i+=15; 
					count++; 
					
					if (count == memberships.count()) { 
						break; 
					}
				}
			} else {
				JLabel noMembershipsLabel = new JLabel();
				noMembershipsLabel.setText("No current memberships.");
				noMembershipsLabel.setBounds(5, 0+i+i, 1000, 20);
				panelChange.add(noMembershipsLabel); 
			} 
		} finally { 
			visitsCursor.close(); 
		}
        
        return panelChange;
	}
	
}
