package com.cellularautomata.main.container;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JFrame;


public class Window {

	private static JFrame frame;
	private static BufferedImage image;
	private static Canvas canvas;
	private BufferStrategy bs;
	private Graphics g;

	public Window(Container c) {
		image = new BufferedImage(c.getWIDTH(), c.getHEIGHT(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension s = new Dimension(c.getWIDTH() , c.getHEIGHT());
		canvas.setSize(s);

		frame = new JFrame(c.getTitle());
		Controlls.mouseListener();
		Controlls.keyListener();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		
		
		
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.toFront();
		frame.setState(Frame.NORMAL);
		frame.requestFocus();
		frame.setLocationRelativeTo(null);

		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();

	}

	public void update() {
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bs.show();
	}

	public static BufferedImage getImage() {
		return image;
	}

	public static Canvas getCanvas() {
		return canvas;
	}

	public static JFrame getFrame() {
		return frame;
	}
}
