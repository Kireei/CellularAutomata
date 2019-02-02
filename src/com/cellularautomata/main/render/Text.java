package com.cellularautomata.main.render;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Text extends Component{
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Serif", Font.PLAIN, 12));
		g2d.drawString("GEH", 0, 0);
		
	}
}
