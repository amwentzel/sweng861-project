package edu.psgv.sweng861.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FrameChange extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
		
	public FrameChange(){
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    initMenu();
//	    panel1.setBackground(Color.BLUE);
//	    panel2.setBackground(Color.RED);
//	    panel3.setBackground(Color.YELLOW);
//	    panel4.setBackground(Color.GREEN);
	    setLayout(new BorderLayout());
	}

	private class MenuAction implements ActionListener {
	    private JPanel panel;
	    private MenuAction(JPanel pnl) {
	        this.panel = pnl;
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        changePanel("Classes");
	    }
	}
	
	private void initMenu() {
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menu1 = new JMenu("Gyms");
	    JMenu menu2 = new JMenu("Health");
	    JMenuItem menuItem1 = new JMenuItem("Classes");
	    JMenuItem menuItem2 = new JMenuItem("Pricing");
	    JMenuItem menuItem3 = new JMenuItem("Your Profile");
	    JMenuItem menuItem4 = new JMenuItem("Class History");
	    menuBar.add(menu1);
	    menuBar.add(menu2);
	    menu1.add(menuItem1);
	    menu1.add(menuItem2);
	    menu2.add(menuItem3);
	    menu2.add(menuItem4);
	    setJMenuBar(menuBar);
	    menuItem1.addActionListener(new MenuAction(panel1));
	    menuItem2.addActionListener(new MenuAction(panel2));
	    menuItem3.addActionListener(new MenuAction(panel3));
	    menuItem4.addActionListener(new MenuAction(panel4));
	}
	
	private void changePanel(String panel) {
	    if ("Classes".contentEquals(panel)) {
	    	getContentPane().removeAll();
	    	getContentPane().add(new ClassesPanelChange(), BorderLayout.CENTER);
	    	getContentPane().doLayout();
	    	update(getGraphics());
	    }
	}
	
	public static void main(String[] args) {
	    FrameChange frame = new FrameChange();
	    frame.setTitle("");
	    frame.setSize(800, 600);
	    frame.setVisible(true);
	}
}