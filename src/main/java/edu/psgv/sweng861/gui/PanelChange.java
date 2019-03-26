package edu.psgv.sweng861.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelChange extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.drawString("Class Schedule for: BRV Yoga", 10, 10);
		g.drawString(" Monday   Tuesday Wednesday Thursday   Friday", 10, 25);
		g.drawString("6pm-Yoga 7pm-Spin 5pm-Barre 6pm-Yoga 7pm-Spin", 10, 40);
	}
	
	public static void main(String[] args) {
		PanelChange panelChange = new PanelChange();
		panelChange.paint(panelChange.getGraphics());
	}
	
}