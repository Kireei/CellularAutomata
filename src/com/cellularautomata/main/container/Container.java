package com.cellularautomata.main.container;

import com.cellularautomata.main.cell.CellularAutomata;
import com.cellularautomata.main.render.Renderer;

public class Container implements Runnable{
	public boolean running = false;
	private final double UPDATE_CAP = 1.0 / 240.0;
	public static final int WIDTH = 1600, HEIGHT = 900;
	private float scale = 1f;
	public static final int CELLSIZE = 16; // Must work in the provided aspect ratio
	public static final int COLONIES = 5000;
	public static final int CELLX = WIDTH / CELLSIZE;
	public static final int CELLY = HEIGHT / CELLSIZE;
	private String title = "Cellular Automata v0.8";
	
	public static int fps;
	
	private Thread thread;
	private Window window;
	private Renderer renderer;
	private CellularAutomata ca;
	
	public Container(){
		
	}
	
	
	public void start(){
		window = new Window(this);
		renderer = new Renderer(this);
		ca = new CellularAutomata(this);
		
		thread = new Thread(this);
		thread.run();
	}
	
	
	public void stop(){
		
	}
	
	
	public void dispose(){
		
	}
	
	
	public void run() {
		running = true;

		boolean render = false;
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;

		double frameTime = 0;
		int frames = 0;
		fps = 0;
		
		CellularAutomata.create();

		while (running) {
				render = true;
				firstTime = System.nanoTime() / 1000000000.0;
				passedTime = firstTime - lastTime;
				lastTime = firstTime;
	
				unprocessedTime += passedTime;
				frameTime += passedTime;
	
				while (unprocessedTime >= UPDATE_CAP) {
					unprocessedTime -= UPDATE_CAP;
					render = true;
	
					// TODO: Update game
					
					
					if (frameTime >= 1.0) {
						frameTime = 0;
						fps = frames;
						frames = 0;
						System.out.println("FPS: " + fps + ", MOUSE COORDINATES: " + Controlls.getMouseCoords());
						
						//ca.update();
					}
					
					
				}
				if (render) {
					renderer.clear();
					
					ca.update();
					
					ca.render();
					// TODO: Render game
					window.update();
					frames++;
	
				} else {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		dispose();
	}


	public float getScale() {
		return scale;
	}


	public void setScale(float scale) {
		this.scale = scale;
	}


	public double getUPDATE_CAP() {
		return UPDATE_CAP;
	}


	public static int getWIDTH() {
		return WIDTH;
	}


	public static int getHEIGHT() {
		return HEIGHT;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Window getWindow() {
		return window;
	}


	public void setWindow(Window window) {
		this.window = window;
	}


	public Renderer getRenderer() {
		return renderer;
	}


	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
}
