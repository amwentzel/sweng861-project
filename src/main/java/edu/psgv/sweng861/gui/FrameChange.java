package edu.psgv.sweng861.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FrameChange extends JFrame {

	private static final long serialVersionUID = 1L;
//	private JPanel panel1 = new JPanel();
	private ClassesPanelChange panel1 = new ClassesPanelChange();
//	private JPanel panel2 = new JPanel();
	private ProfilePanelChange panel2 = new ProfilePanelChange();
		
	public FrameChange(){
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    initMenu();
	    setLayout(new BorderLayout());
	}

	private class MenuAction implements ActionListener {
	    @SuppressWarnings("unused")
		private JPanel panel;
	    private MenuAction(JPanel pnl) {
	        this.panel = pnl;
	    }
	    public void actionPerformed(ActionEvent e) {
	        changePanel(e.getActionCommand());
	    }
	}
	
	private void initMenu() {
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menu1 = new JMenu("Fitness");
	    JMenu menu2 = new JMenu("Profile");
	    JMenuItem menuItem1 = new JMenuItem("Classes");
	    JMenuItem menuItem2 = new JMenuItem("Class History");
	    menuBar.add(menu1);
	    menuBar.add(menu2);
	    menu1.add(menuItem1);
	    menu2.add(menuItem2);
	    setJMenuBar(menuBar);
	    panel1.createLabel(panel2);
	    panel2.createLabel();
	    menuItem1.addActionListener(new MenuAction(panel1));
	    menuItem2.addActionListener(new MenuAction(panel2));
	}
	
	private void changePanel(String panel) {
	    if ("Classes".contentEquals(panel)) {
	    	getContentPane().removeAll();
	    	getContentPane().add(panel1, BorderLayout.CENTER);
	    	getContentPane().doLayout();
	    	update(getGraphics());
	    } else if ("Class History".contentEquals(panel)) {
	    	getContentPane().removeAll();
	    	getContentPane().add(panel2, BorderLayout.CENTER);
	    	getContentPane().doLayout();
	    	update(getGraphics());
	    }
	}
	
	public static void main(String[] args) {
	    FrameChange frame = new FrameChange();
	    frame.setTitle("Gym Buddy");
	    frame.setSize(850, 400);
	    frame.setVisible(true);
	}
}