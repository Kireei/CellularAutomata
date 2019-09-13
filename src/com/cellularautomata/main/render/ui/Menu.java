package com.cellularautomata.main.render.ui;

import java.awt.Canvas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import com.cellularautomata.main.container.Window;
import com.cellularautomata.main.render.Renderer;

public class Menu {
	public ArrayList<String> titles;
	private int h;
	protected int x;
	protected int y;
	protected int w;
	private int textWidth;
	private int buttonWidth;
	private int totalWidth;
	private int lowest;
	private int color;
	public ArrayList<Button> buttons;
	
	public Menu(int x, int y, int w, int color){
		titles = new ArrayList<String>();
		buttons = new ArrayList<Button>();
		this.color = color;
		this.x = x;
		this.y = y;
		this.w = w;
		this.textWidth = 0;
		this.buttonWidth = 0;
		this.totalWidth = 0;
		this.lowest = 0;
	}
	
	public void render(){
		int height = lowest - this.y;
		/*for(int i = 0; i < buttons.size(); i++){
			height += buttons.get(i).getH();
		}*/
		Renderer.fillRect(this.x, this.y, this.totalWidth , height, this.color); // TODO: Fungerar bra nu med text, men knappar är itne klart
		for(int i = 0; i < titles.size(); i++){
			Renderer.drawText(titles.get(i), this.x + 4, this.y + i * 7, 0xffffffff);
		}
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).render();
		}
		
	}
	
	public void spawnMenu(double x, double y){
			Point pos = new Point();
			Canvas canvas = Window.getCanvas();
			Point frame = MouseInfo.getPointerInfo().getLocation();
			//double x = canvas.getLocationOnScreen().getX();
			//double y = canvas.getLocationOnScreen().getY();
			
			if((frame.x <= canvas.getWidth() + x) && (frame.x >= x) && (frame.y <= canvas.getHeight() + y) &&(frame.y >= y)){
				pos.x = (int) (frame.x - x);
				pos.y = (int) (frame.y - y);
			}
			
			//this.i = (int) ((pos.y / Container.CELLSIZE) * Container.CELLX  + (pos.x / Container.CELLSIZE));
			
			this.x = (int) x;
			this.y = (int) y;
			this.render();
			for(Button b: buttons){
				b.render();
			}
			
		}
	
	public void addTitle(String title){
		this.titles.add(title);
		this.h = titles.size() * 7;
		int width = title.length() * 4;
		
		for(int i = 0; i < titles.size(); i++){
			if(width > textWidth && width > buttonWidth) totalWidth = width + 12;
		}
	}
	
	public void addButton(int x, int y, int w, int h, int color, String text, boolean centered, int id){
		Button b = new Button(x, y, w, h, color, text, centered, id);
		this.buttons.add(b);
		for(int i = 0; i < buttons.size(); i++){
			if(b.getW() > totalWidth) totalWidth = b.getW() + 8 + b.x;
			if(b.y + b.getH() + 4 > lowest) lowest = b.y + b.getH() + 4;
		
		}
	
	}
}
