package com.cellularautomata.main.render.ui;

public class AdminCommands extends Menu{

	public AdminCommands(int x, int y, int w, int color) {
		super(x, y, w, color);
		String title = "ADMIN CONTROLLS";
		addTitle(title);
		addButton(x + 4, y + 10, 0, 0, 0xff00ff00, "MAX STRENGTH", false, 0);
		addButton(x + 4, y + 20, 0, 10, 0xffff0000, "RESTART", true, 1); // +4 ger en snygga svart kant runt kanppen
		addButton(x + 4, y + 40, 0, 0, 0xff0000ff, "AGE", false, 2);
		addButton(x + 30, y + 40, 0, 0, 0xffff0000, "STRENGTH", false, 3);
		addButton(x + 4, y + 50, 0, 0, 0xffffffff, "KILL", false, 4);
		addButton(x + 4, y + 60, 0, 0, 0xffffffff, "BLACK", false, 5);
		//Button b = new Button(0,0, w, w, 0xffffffff, "Test", false);
		//b.render();
		 
	}
	
}
