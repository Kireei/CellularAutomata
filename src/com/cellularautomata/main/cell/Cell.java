package com.cellularautomata.main.cell;

import com.cellularautomata.main.container.Container;

public class Cell {
	public int faction;
	public int color;
	public int age;
	public double strength;
	public boolean living;
	public boolean sick;
	public boolean spreadable;
	public int spreadCooldown;
	public double strengthMutation;
	public int deathCount;
	
	public static double startStrength = 1 + ((Math.random() - 0.5) / 10);
	
	
	public Cell(){
		this.color = 0;
		this.faction = 0;
		this.age = 0;
		this.living = false;
		this.strength = startStrength;
		this.sick = false;
		this.spreadable = false;
		this.spreadCooldown = 0;
		this.strengthMutation = Math.random() + 0.5;
		this.deathCount = 0;
	}
	
	public void cellSpreadable(){
		if(this.age > 15 && spreadCooldown <= 0){
			this.spreadable = true;
		} else {
			this.spreadable = false;
		}
	}
	
	public void checkNeighbouríngCell(int i, int direction){
		int newDir = 0;
		switch(direction){
			case 0:
				//Check top
				if(i <= Container.WIDTH / Container.CELLSIZE) break;
				newDir = i - Container.WIDTH / Container.CELLSIZE;
				break;
			case 1:
				//Check right
				if(i % (Container.WIDTH / Container.CELLSIZE) == (Container.WIDTH / Container.CELLSIZE) - 1) break;
				newDir = i + 1;
				break;
			case 2:
				//Check down
				if(i >= ((Container.WIDTH / Container.CELLSIZE) * (Container.HEIGHT / Container.CELLSIZE) - Container.WIDTH / Container.CELLSIZE)) break;
				newDir = i + Container.WIDTH / Container.CELLSIZE;
				break;
			case 3: 
				//Check left
				if(i % (Container.WIDTH / Container.CELLSIZE) == 0) break;
				newDir = i - 1;
				break;
		}
		if(CellularAutomata.cells[newDir].faction == this.faction) return;
		if(CellularAutomata.cells[newDir].faction == -1) return;
		if(CellularAutomata.cells[newDir].faction == 0){
			spread(CellularAutomata.cells[newDir]);
		} else if(CellularAutomata.cells[newDir].faction != 0 && CellularAutomata.cells[newDir].faction != this.faction) {
			//fight(CellularAutomata.cells[newDir]);
			if(this.strength > CellularAutomata.cells[newDir].strength){
				fight(CellularAutomata.cells[newDir]);
			}
		}
	}
	
	public void spread(Cell newCell){
		newCell.faction = this.faction;
		newCell.color = this.color;
		newCell.age = 0;
		newCell.strength = generateStrength(this);
		newCell.strengthMutation = this.strengthMutation;
		if(Math.random() > 0.9)kill(this);
		this.spreadable = false;
		this.spreadCooldown = 200;
		if(Math.random() + this.age / 500 > 0.95){
			newCell.sick = true;
		}
	}
	
	public void fight(Cell newCell){
		//this.strength = generateStrength(this);
		kill(newCell);
		spread(newCell);
	}
	
	public void kill(Cell newCell){
		newCell.faction = 0;
		newCell.color = 0xffffffff;
		newCell.age = 0;
		newCell.strength = startStrength;
		newCell.sick = false;
		newCell.deathCount++;
	}
	
	public void naturalDeath(Cell cell){
		if(cell.age > (1000 + 1500 * (Math.random() - 0.5)) && cell.age * (Math.random() + 0.5) > cell.age){
			kill(this);
		}
		if(cell.sick && Math.random() > 0.75) kill(this);
	}
	
	public double generateStrength(Cell cell){
		double cStrength = cell.strength;
		if(Math.random() > 0.995){
			cStrength += (Math.random() - 0.5) * 4;
		}
		return cStrength;
		
	}
	
	public void black(){
		this.faction = -1;
		this.color = 0xff000000;
	}
	



	public int getFaction() {
		return faction;
	}


	public void setFaction(int faction) {
		this.faction = faction;
	}


	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
}
