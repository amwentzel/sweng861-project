package edu.psgv.sweng861.gui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.psgv.sweng861.ClassObject;
import edu.psgv.sweng861.GymClasses;

public class ClassesPanelChange extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private GymClasses gymClasses = new GymClasses();
	private ProfilePanelChange profilePanelChange;
	private ClassObject[] classObjects = new ClassObject[13];
	
	public void createLabel(ProfilePanelChange profilePanelChange) {
		DB fitnessDB = gymClasses.fitnessDB.database;
		this.profilePanelChange = profilePanelChange;

		DBCollection classes = fitnessDB.getCollection("Classes");
		DBCollection locations = fitnessDB.getCollection("Locations");
		DBCollection teachers = fitnessDB.getCollection("Teachers");
		DBCollection dates = fitnessDB.getCollection("Dates");
		DBCollection startTimes = fitnessDB.getCollection("StartTimes");
		DBCollection endTimes = fitnessDB.getCollection("EndTimes");
		
		DBCursor classesCursor = classes.find();
		DBCursor locationsCursor = locations.find();
		DBCursor teachersCursor = teachers.find();
		DBCursor datesCursor = dates.find();
		DBCursor startTimesCursor = startTimes.find();
		DBCursor endTimesCursor = endTimes.find();
		
		this.setLayout(null);
		
		int i = 0;
		int count = 0;
		int index = 0;
        try {
            while(classesCursor.hasNext()) {
            	DBObject classObj = classesCursor.next();
            	DBObject locationObj = locationsCursor.next();
            	DBObject teacherObj = teachersCursor.next();
            	DBObject dateObj = datesCursor.next();
            	DBObject startTimeObj = startTimesCursor.next();
            	DBObject endTimeObj = endTimesCursor.next();

            	JButton button = new JButton();
            	ClassObject classObject;
            	if ("false".equals(classObj.get("Booked"))) {
            		button.setText("Book");
            		classObject = new ClassObject(classObj.get("Class").toString(), false);
            	} else {
            		button.setText("Cancel");
            		classObject = new ClassObject(classObj.get("Class").toString(), true);
            	}
            	button.setName(index+"");
            	classObject.setLocation(locationObj.get("Location").toString());
            	classObject.setDate(dateObj.get("Date").toString());
            	classObject.setStartTime(startTimeObj.get("Start").toString());
            	classObject.setEndTime(endTimeObj.get("End").toString());
            	classObjects[index++] = classObject;
            	button.setMargin(new Insets(0, 0, 0, 0));
            	button.setBounds(3, 2+i, 60, 15);
            	button.addActionListener(this);
            	button.setActionCommand("Book");
            	this.add(button);

        		JLabel label = new JLabel();
        		label.setText("Class: " + classObj.get("Class") + " at " + locationObj.get("Location") 
        			+ " with " + teacherObj.get("Teacher") + " on " + dateObj.get("Date") + " from " 
        			+ startTimeObj.get("Start") + " until " + endTimeObj.get("End"));
        		label.setBounds(70, 0+i, 1000, 20);
        		this.add(label);
        		
            	i+=17;
            	count++;
            	if (count == classes.count()) {
            		break;
            	}
            }
        } finally {
            classesCursor.close();
        }
        
	}

	public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        if (action.equals("Book")) {
        	JButton button = (JButton) event.getSource();
        	int index = Integer.valueOf(button.getName());
        	if (button.getText().equals("Book")) {
        		button.setText("Cancel");
        		classObjects[index].setBooked(true);
        	} else {
        		button.setText("Book");
        		classObjects[index].setBooked(false);
        	}
        	profilePanelChange.addUpcomingClasses(classObjects[index]);
//    		for (int j = 0; j < classObjects.length; j++) {
//    			profilePanelChange.addUpcomingClasses(classObjects[j]);
//    		}
        }
	}
	
}