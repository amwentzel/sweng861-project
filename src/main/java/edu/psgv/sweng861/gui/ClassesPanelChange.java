package edu.psgv.sweng861.gui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.psgv.sweng861.GymClasses;

public class ClassesPanelChange extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private GymClasses gymClasses = new GymClasses();
	private BasicDBObject document = new BasicDBObject();
	
	public ClassesPanelChange createLabel(ClassesPanelChange panelChange) {
		DB fitnessDB = gymClasses.fitnessDB.database;

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
		
		panelChange.setLayout(null);
		
		int i = 0;
		int count = 0;
        try {
            while(classesCursor.hasNext()) {
            	DBObject classObj = classesCursor.next();
            	DBObject locationObj = locationsCursor.next();
            	DBObject teacherObj = teachersCursor.next();
            	DBObject dateObj = datesCursor.next();
            	DBObject startTimeObj = startTimesCursor.next();
            	DBObject endTimeObj = endTimesCursor.next();
            	
            	JButton button = new JButton();
            	if ("false".equals(classObj.get("Booked"))) {
            		button.setText("Book");
                	document.append("$set", new BasicDBObject().append("Booked", "false"));
                	BasicDBObject searchQuery = new BasicDBObject().append("Class", classObj.get("Class"));
                	classes.update(searchQuery, document);
            	} else {
            		button.setText("Cancel");
                	document.append("$set", new BasicDBObject().append("Booked", "true"));
                	BasicDBObject searchQuery = new BasicDBObject().append("Class", classObj.get("Class"));
                	classes.update(searchQuery, document);
            	}
            	button.setMargin(new Insets(0, 0, 0, 0));
            	button.setBounds(3, 2+i, 60, 15);
            	button.addActionListener(this);
            	button.setActionCommand("Book");
            	panelChange.add(button);

        		JLabel label = new JLabel();
        		label.setText("Class: " + classObj.get("Class") + " at " + locationObj.get("Location") 
        			+ " with " + teacherObj.get("Teacher") + " on " + dateObj.get("Date") + " from " 
        			+ startTimeObj.get("Start") + " until " + endTimeObj.get("End"));
        		label.setBounds(70, 0+i, 1000, 20);
        		panelChange.add(label);
        		
            	i+=17;
            	count++;
            	if (count == classes.count()) {
            		break;
            	}
            }
        } finally {
            classesCursor.close();
        }
        
        return panelChange;
	}

	public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        if (action.equals("Book")) {
        	JButton button = (JButton) event.getSource();
        	if (button.getText().equals("Book")) {
        		button.setText("Cancel");
            	document.put("Booked", "true");
            	//update visits panel
        	} else {
        		button.setText("Book");
        		document.put("Booked", "false");
        		//update visits panel
        	}
        }
	}
	
}