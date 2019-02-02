package com.cellularautomata.main.render.ui;

import com.cellularautomata.main.cell.Cell;
import com.cellularautomata.main.cell.CellularAutomata;
import com.cellularautomata.main.container.Controlls;
import com.cellularautomata.main.render.Clickmodes;
import com.cellularautomata.main.render.Viewmodes;

public class ButtonFunctions {
	
	
	public static void getButtonFunction(int id){
		switch(id){
			case 0:
				System.out.println("MAX STRENGTH: " + CellularAutomata.maxStrength);
				break;
			case 1:
				for(Cell cell: CellularAutomata.cells){
					cell.kill(cell);
					cell.deathCount = 0;
					CellularAutomata.maxStrength = Cell.startStrength;
				}
				CellularAutomata.create();
				System.out.println("SIMULATION RESTARTED");
				break;
			case 2:
				Viewmodes.setAge();
				break;
			case 3:
				Viewmodes.setStrength();
				break;
			case 4:
				Controlls.cm = Clickmodes.KILLFACTION;
				break;
			case 5:
				Controlls.cm = Clickmodes.BLACKCELL;
				break;
		}
	}
}
