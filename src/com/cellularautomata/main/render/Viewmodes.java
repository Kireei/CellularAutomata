package com.cellularautomata.main.render;

import com.cellularautomata.main.cell.Cell;
import com.cellularautomata.main.cell.CellularAutomata;

public class Viewmodes {
	
	public static Viewmode mode = Viewmode.NORMAL;
	
	public Viewmodes(){
	}
	
	public static void setNormal(){
		mode = Viewmode.NORMAL;
	}
	public static void setAge(){
		mode = Viewmode.AGE;
	}
	public static void setStrength(){
		mode = Viewmode.STRENGTH;
	}
	public static void setDeathCount(){
		mode = Viewmode.DEATHCOUNT;
	}
	
	public static int getIntFromColor(int Red, int Green, int Blue, int Alpha){
		Alpha = (Alpha << 24) & 0xFF000000;
	    Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
	    Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
	    Blue = Blue & 0x000000FF; //Mask out anything not blue.

	    return 0xFF000000 | Alpha | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}
	
	
	public static int renderMode(Cell cell){
		switch(mode){
		case NORMAL:
			//Do nothing, keep the standard one
			return cell.color;
		case AGE:
			//Render not by faction color, but by age
			if(cell.faction == -1) return 0xff000000;
			return getIntFromColor(255 - cell.age, 255 - cell.age, 255, 255);
			
			//return cell.age - 0xff000000;
			
		case STRENGTH:
			//Render by strength
			if(cell.faction == 0) return 0xfffffff;
			if(cell.faction == -1) return 0xff000000;
			return getIntFromColor(255, 255 - (int) (cell.strength / CellularAutomata.maxStrength * 255), 255 - (int) (cell.strength / CellularAutomata.maxStrength * 255), 255);
		
		case DEATHCOUNT:
			if(cell.faction == -1) return 0xff000000;
			if(cell.deathCount <= 255 / 5) return getIntFromColor(255, 255 - cell.deathCount * 5, 255 - cell.deathCount * 5, 255);
			if(cell.deathCount > 255 / 5 && cell.deathCount <= 510 / 5) return getIntFromColor(510 - cell.deathCount * 5, cell.deathCount * 5 - 255, 0, 255);
			if(cell.deathCount > 510 / 5 && cell.deathCount <= 765 / 5) return getIntFromColor(0, 765 - cell.deathCount * 5, cell.deathCount * 5 - 510, 255);
			if(cell.deathCount > 765 / 5 && cell.deathCount <= 1020 / 5) return getIntFromColor(0, 0, 1020 - cell.deathCount * 5, 255);
			if(cell.deathCount > 1020 / 5) return getIntFromColor(0, 0, 0, 255);
		}
		return cell.color;
	}
	
	public static Viewmode getMode(){
		return mode;
	}
}
