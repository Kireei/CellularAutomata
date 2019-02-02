package com.cellularautomata.main.container;

import java.awt.Canvas;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.cellularautomata.main.cell.Cell;
import com.cellularautomata.main.cell.CellularAutomata;
import com.cellularautomata.main.render.Clickmodes;
import com.cellularautomata.main.render.InfoBox;
import com.cellularautomata.main.render.Viewmodes;
import com.cellularautomata.main.render.ui.AdminCommands;
import com.cellularautomata.main.render.ui.Button;

public class Controlls {
	public Controlls(){
		
		
	}
	public static AdminCommands adminInfoBox = new AdminCommands(0, 0, 10, 0xff000000);
	public static boolean isBoxLive = false;
	public static boolean adminBox = false;
	public static Clickmodes cm = Clickmodes.NORMAL;
	public static int id = 0;
	
	public static void mouseListener(){
		Window.getCanvas().addMouseListener(new MouseListener(){

			//@Override
			public void mouseClicked(MouseEvent me) {
			
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				
			}

			@Override
			public void mouseExited(MouseEvent me) {
				
			}

			@Override
			public void mousePressed(MouseEvent me) {
				
			}

			//@Override
			public void mouseReleased(MouseEvent me) {
				
				if(me.getButton() == MouseEvent.BUTTON1){
					System.out.println("LEFTCLICK");
					for(int i = 0; i < CellularAutomata.menues.size(); i++){
						for(int j = 0; j < CellularAutomata.menues.get(i).buttons.size(); j++){
							Button b = CellularAutomata.menues.get(i).buttons.get(j);
							if(getMousePoint().x >= b.x && getMousePoint().x <= ( b.x + b.getW()) && getMousePoint().y >= b.y && getMousePoint().y <= (b.y + b.getH())){
								b.onClick();
								return;
							} else {
								
							}
						}
					}
					isBoxLive = !isBoxLive;
					spawnInfoBox();
					
				}
				if(me.getButton() == MouseEvent.BUTTON2){
					System.out.println("MIDDLECLICK");
					
					//System.exit(0);
				}
				if(me.getButton() == MouseEvent.BUTTON3){
					System.out.println("RIGHTCLICK");
					switch(cm){
						case KILLFACTION:
							int faction = CellularAutomata.cells[(int) ((getMousePoint().y / Container.CELLSIZE) * Container.CELLX  + (getMousePoint().x / Container.CELLSIZE))].faction;
							for(Cell cell: CellularAutomata.cells) if(faction == cell.faction)cell.kill(cell);
							break;
						case BLACKCELL:
							CellularAutomata.cells[(int) ((getMousePoint().y / Container.CELLSIZE) * Container.CELLX  + (getMousePoint().x / Container.CELLSIZE))].black();
							break;
						default:
							break;
					}
					
					
				}
				
			
			}
			
		});
	}
	
	public static void keyListener(){
		Window.getCanvas().addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent ke) {
				
			}

			@Override
			public void keyReleased(KeyEvent ke) {
				switch(ke.getKeyCode()){
					case(KeyEvent.VK_ESCAPE):
						System.exit(0);
						break;
					case(KeyEvent.VK_1):
						Viewmodes.setNormal();
						break;
					case(KeyEvent.VK_2):
						Viewmodes.setAge();
						break;
					case(KeyEvent.VK_3):
						Viewmodes.setStrength();
						break;
					case(KeyEvent.VK_4):
						Viewmodes.setDeathCount();
						break;
					case(KeyEvent.VK_0):
						CellularAutomata.menues.clear();
						break;
					case(KeyEvent.VK_SHIFT):
						if(!adminBox){
							adminInfoBox.spawnMenu(0, 0);
							CellularAutomata.menues.add(adminInfoBox);
							adminBox = true;
							break;
						} 
						if(adminBox){
							CellularAutomata.menues.remove(adminInfoBox);
							adminBox = false;
							break;
						}
						break;
					case(KeyEvent.VK_9):
						for(Cell cell: CellularAutomata.cells){
							cell.kill(cell);
							cell.deathCount = 0;
							CellularAutomata.maxStrength = Cell.startStrength;
						}
						CellularAutomata.create();
				}
				
			}

			@Override
			public void keyTyped(KeyEvent ke) {
				
			}
			
		});
	}
	public static Point pos = new Point();
	public static InfoBox box = new InfoBox();
	public static void spawnInfoBox(){
		
		Canvas canvas = Window.getCanvas();
		Point frame = MouseInfo.getPointerInfo().getLocation();
		double x = canvas.getLocationOnScreen().getX();
		double y = canvas.getLocationOnScreen().getY();
		
		if((frame.x <= canvas.getWidth() + x) && (frame.x >= x) && (frame.y <= canvas.getHeight() + y) &&(frame.y >= y)){
			pos.x = (int) (frame.x - x);
			pos.y = (int) (frame.y - y);
		}
		
		box.i = (int) ((pos.y / Container.CELLSIZE) * Container.CELLX  + (pos.x / Container.CELLSIZE));
		
		box.x = pos.x;
		box.y = pos.y;
		box.w = 100;
		box.h = 100;
		box.openBox();
		
	}
	
	public static void renderBox(){
		box.openBox();
		
	}
	
	
	public static String getMouseCoords(){
		String error = "Mouse outside of window";
		Canvas canvas = Window.getCanvas();
		Point frame = MouseInfo.getPointerInfo().getLocation();
		double x = canvas.getLocationOnScreen().x;
		double y = canvas.getLocationOnScreen().y;
		if((frame.x <= canvas.getWidth() + x) && (frame.x >= x) && (frame.y <= canvas.getHeight() + y) &&(frame.y >= y)){
			double posx =  frame.x - x;
			double posy = frame.y - y;
			String coords = posx + ", " + posy;
			return coords;
		}
		//System.out.println(frame.x);
		//System.out.println(frame.y);
		return error;
		
		
	}
	public static Point getMousePoint(){
		Canvas canvas = Window.getCanvas();
		Point frame = MouseInfo.getPointerInfo().getLocation();
		Point p = new Point();
		double x = canvas.getLocationOnScreen().x;
		double y = canvas.getLocationOnScreen().y;
		if((frame.x <= canvas.getWidth() + x) && (frame.x >= x) && (frame.y <= canvas.getHeight() + y) &&(frame.y >= y)){
			p.x =  (int) (frame.x - x);
			p.y = (int) (frame.y - y);
			return p;
		}
		//System.out.println(frame.x);
		//System.out.println(frame.y);
		return null;
		
		
	}
}
