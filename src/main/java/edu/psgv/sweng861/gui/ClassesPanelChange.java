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
		g.setColor(Color.BLACK);
		DB fitnessDB = gymClasses.fitnessDB.database;
		DBCollection classes = fitnessDB.getCollection("Classes");
		DBCursor cursor = classes.find();
		int i = 0;
        try {
            while(cursor.hasNext()) {
            	DBObject dbObject = cursor.next();
            	if (null != dbObject.get("Location")) {
            		g.drawString("Class: " + dbObject.get("Class") + " Location: " + dbObject.get("Location"), 10, 10+i);
            	} else if (null != dbObject.get("Start")) {
            		g.drawString("Class: " + dbObject.get("Class") + " Start Time: " + dbObject.get("Start"), 10, 10+i);
            	} else {
            		g.drawString("Clas: " + dbObject.get("Class"), 10, 10+i);
            	}
            	i+=15;
            }
        } finally {
            cursor.close();
        }		
	}
	
	public static void main(String[] args) {
		ClassesPanelChange panelChange = new ClassesPanelChange();
		panelChange.paint(panelChange.getGraphics());
	}
	
}