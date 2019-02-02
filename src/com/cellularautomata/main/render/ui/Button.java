package com.cellularautomata.main.render.ui;

import com.cellularautomata.main.render.Renderer;


public class Button {
	public int x;
	public int y;
	public int w;
	public int h;
	private int color;
	private int darkerColor;
	public String text;
	private boolean centered;
	public int id;
	
	
	
	
	public Button(int x, int y, int w, int h, int color, String text, boolean centered, int id){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
		this.text = text;
		this.centered = centered;
		this.id = id;
	}
	
	public void render(){
		Renderer.fillRect(this.x, this.y, (int)(this.text.length() * 4.3 + this.w),  6 + this.h, this.color);
		if(centered){
			Renderer.drawText(this.text, this.x + getW() / 2 - (this.text.length() * 5 / 2), this.y + this.h / 2, 0xff000000);
		} else {
			Renderer.drawText(this.text, this.x, this.y, 0xff000000);
		}
	}
	
	public void onClick(){
		ButtonFunctions.getButtonFunction(id);
	}

	public int getW() {
		return (int)(this.text.length() * 4.3 + this.w);
	}

	public int getH() {
		return (5 + this.h);
	}
}
