package com.cellularautomata.main.cell;

import java.util.ArrayList;

import com.cellularautomata.main.container.Container;
import com.cellularautomata.main.container.Controlls;
import com.cellularautomata.main.render.Renderer;
import com.cellularautomata.main.render.Viewmodes;
import com.cellularautomata.main.render.ui.Menu;

public class CellularAutomata {
	public static Cell[] cells;
	public static Cell[] newCells;
	public static int nFactions;
	public static double maxStrength = Cell.startStrength;
	public static ArrayList<Menu> menues = new ArrayList<>();
	
	
	public CellularAutomata(Container c){
		cells = new Cell[(Container.getWIDTH() / Container.CELLSIZE) * (Container.HEIGHT / Container.CELLSIZE)];
		newCells = new Cell[(Container.getWIDTH() / Container.CELLSIZE) * (Container.HEIGHT / Container.CELLSIZE)];
		nFactions = 0;
	}
	
	public static void create(){
		for(int i = 0; i < cells.length; i++){
			cells[i] = new Cell();
			cells[i].setColor(0xffffffff);
			cells[i].setFaction(0);
		}
		for(int i = 0; i < Container.COLONIES; i++){
			int randCell = (int) Math.floor(Math.random() * cells.length);
			cells[randCell].color = (int) (Math.random() * 0xff000000);
			cells[randCell].faction = i + 1; // Plus one since 0 is white faction and neutral
			nFactions++;
			
		}
		for(int i = 0; i < cells.length; i++){
			if(i % (Container.WIDTH / 2 / Container.CELLSIZE) == 0 && i != 174000) cells[i].black();
			if(i % (Container.WIDTH / Container.CELLSIZE) == (Container.WIDTH / Container.CELLSIZE) - 1) cells[i].black();
		}
		
		newCells = cells;
	}
	
	public void update(){
		//r.fillRect(0, 0, c.WIDTH, c.HEIGHT, 0xffffffff);
		
		for(int i = 0; i < cells.length; i++){
			if(cells[i].color == 0xffffffff || cells[i].color == 0xff000000) continue; //White cells gets ignored
			if(cells[i].strength <= 0) cells[i].kill(cells[i]);
			if(cells[i].strength > maxStrength) maxStrength = cells[i].strength;
			//Check around every colored cell
			cells[i].cellSpreadable();
			//cells[i].spreadable = true;
			if(cells[i].spreadable && Math.random() > 0.75){
				cells[i].checkNeighbouríngCell(i, (int) Math.floor(Math.random() * 4));
				cells[i].spreadCooldown = 2;
				cells[i].spreadable = false;
			}
			
			cells[i].spreadCooldown -= 1;
			cells[i].naturalDeath(cells[i]);
			cells[i].age += 1;
		}
		newCells = cells;
		
	}
	
	
	public void render(){
		int y = 0;
		int x = 0;
		//cells = newCells;
		for(int i = 0; i < cells.length; i++){
			if(x >= Container.getWIDTH() ){
				y += Container.CELLSIZE;
				x = 0;
			}
			Renderer.fillRect(x, y, Container.CELLSIZE, Container.CELLSIZE, Viewmodes.renderMode(cells[i]));
			
			x += Container.CELLSIZE;
		}

		Renderer.drawText("FPS: " + String.valueOf(Container.fps), 0, 0, 0xff000000);
		
		if(menues.size() > 0){
			for(int i = 0; i < menues.size(); i++){
				menues.get(i).render();
			}
		}
		if(Controlls.isBoxLive) Controlls.renderBox();
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		CellularAutomata.cells = cells;
	}

	public Cell[] getNewCells() {
		return newCells;
	}

	public void setNewCells(Cell[] newCells) {
		CellularAutomata.newCells = newCells;
	}
	

}
