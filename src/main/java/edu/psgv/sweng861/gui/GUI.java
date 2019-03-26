package edu.psgv.sweng861.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	
    public static void main(String args[]) {
        frame = new JFrame("Fitness Tracking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuOption1 = new JMenu("Gyms");
        JMenu menuOption2 = new JMenu("Health");
        menuBar.add(menuOption1);
        menuBar.add(menuOption2);
        JMenuItem menuItem1 = new JMenuItem("Class Schedules");
        JMenuItem menuItem2 = new JMenuItem("Card Prices");
        menuOption1.add(menuItem1);
        menuOption1.add(menuItem2);
        JMenuItem menuItem3 = new JMenuItem("Your Profile");
        JMenuItem menuItem4 = new JMenuItem("Class History");
        menuOption2.add(menuItem3);
        menuOption2.add(menuItem4);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts up to 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
    
}
