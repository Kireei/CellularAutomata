package com.cellularautomata.main.render;

import java.util.ArrayList;

import javax.swing.JLabel;

import com.cellularautomata.main.cell.Cell;
import com.cellularautomata.main.cell.CellularAutomata;
import com.cellularautomata.main.container.Container;
import com.cellularautomata.main.utils.Maths;

public class InfoBox {
	public int x;
	public int y;
	public int w;
	public int h;
	public int i;
	private int id;
	private int faction;
	private int color;
	private int age;
	private double strength;
	private boolean living;
	private boolean sick;
	private boolean spreadable;
	private int spreadCooldown;
	private double strengthMutation;
	
	private ArrayList<String> titles;
	
	
	public InfoBox(){
		titles = new ArrayList<String>();
		titles.add("ID: ");
		titles.add("Strength: ");
		titles.add("Age: ");
		titles.add("Faction: ");
		titles.add("Color: ");
		titles.add("Sick: ");
		titles.add("Spreadable: ");
		titles.add("Cooldown: ");
		titles.add("Mutation: ");
	}
	
	public void openBox(){
		try {
			getStats(i);
			
			if(Container.WIDTH - this.w <= this.x) this.x -= 100;
			if(Container.HEIGHT - this.h <= this.y) this.y -= 100;
			Renderer.fillRect(this.x, this.y, this.w , titles.size() * 7, 0xff000000);
			
			Renderer.drawText(titles.get(0) + id, this.x, this.y, 0xffffffff);
			Renderer.drawText(titles.get(1) + Maths.roundOff(strength), this.x, this.y + (7 * 1), 0xffffffff);
			Renderer.drawText(titles.get(2) + age, this.x, this.y + (7 * 2), 0xffffffff);
			Renderer.drawText(titles.get(3) + faction, this.x, this.y + (7 * 3), 0xffffffff);
			Renderer.drawText(titles.get(4) + color, this.x, this.y + (7 * 4), 0xffffffff);
			Renderer.drawText(titles.get(5) + sick, this.x, this.y + (7 * 5), 0xffffffff);
			Renderer.drawText(titles.get(6) + spreadable, this.x, this.y + (7 * 6), 0xffffffff);
			Renderer.drawText(titles.get(7) + spreadCooldown, this.x, this.y + (7 * 7), 0xffffffff);
			Renderer.drawText(titles.get(8) + Maths.roundOff(strengthMutation), this.x, this.y + (7 * 8), 0xffffffff);
			
			
		} catch (Exception e){
				System.out.println(e);
			
		}
	}
	
	private void getStats(int i){
		Cell cell = CellularAutomata.cells[i];
		id = i;
		faction = cell.faction;
		color = cell.color;
		age = cell.age;
		strength = cell.strength;
		living = cell.living;
		sick = cell.sick;
		spreadable = cell.spreadable;
		spreadCooldown = cell.spreadCooldown;
		strengthMutation = cell.strengthMutation;
	}
	
	private void addToList(String title){
		titles.add(title);
	}
}
