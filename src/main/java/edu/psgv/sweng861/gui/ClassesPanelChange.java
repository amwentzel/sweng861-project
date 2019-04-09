package edu.psgv.sweng861.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.psgv.sweng861.GymClasses;

public class ClassesPanelChange extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private GymClasses gymClasses = new GymClasses();
	
	public void paint(Graphics g) {
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
		
		g.setColor(Color.BLACK);
		int i = 0;
		int count = 0;
        try {
            while(classesCursor.hasNext()) {
//			while(count > 15) {
            	DBObject classObj = classesCursor.next();
            	DBObject locationObj = locationsCursor.next();
            	DBObject teacherObj = teachersCursor.next();
            	DBObject dateObj = datesCursor.next();
            	DBObject startTimeObj = startTimesCursor.next();
            	DBObject endTimeObj = endTimesCursor.next();
            	
        		g.drawString("Class: " + classObj.get("Class") + " at " + locationObj.get("Location") 
        			+ " with " + teacherObj.get("Teacher") + " on " + dateObj.get("Date") + " from " 
        			+ startTimeObj.get("Start") + " until " + endTimeObj.get("End"), 10, 10+i);
            		
            	i+=15;
            	count++;
            	if (count == classes.count()) {
            		break;
            	}
            }
        } finally {
            classesCursor.close();
        }		
	}
	
	public static void main(String[] args) {
		ClassesPanelChange panelChange = new ClassesPanelChange();
		panelChange.paint(panelChange.getGraphics());
	}
	
}