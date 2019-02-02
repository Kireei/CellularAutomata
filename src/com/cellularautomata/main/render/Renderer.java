package com.cellularautomata.main.render;

import java.awt.Graphics;
import java.awt.image.DataBufferInt;

import com.cellularautomata.main.container.Container;


public class Renderer{
	private static int pW;
	private static int pH;
	private static int[] p;
	private Graphics g;
	private static Font font;
	public Renderer(Container c){
		
		pW = c.getWIDTH();
		pH = c.getHEIGHT();
		p = ((DataBufferInt) c.getWindow().getImage().getRaster().getDataBuffer()).getData();
		font = new Font("/standardFont.png");
	}
	
	public void clear() {
		for (int i = 0; i < p.length; i++) {
			p[i] = 0;
		}
	}
	public static void setPixel(int x, int y, int value) {
		if ((x < 0 || x >= pW || y < 0 || y >= pH)) {
			return;
		}

		p[x + y * pW] = value;
	}
	public static void fillRect(int offX, int offY, int width, int height, int color) {

		// Don't render code
		if (offX < -width)
			return;
		if (offY < -height)
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = width;
		int newHeight = height;

		// Clipping code
		if (offX < 0) {
			newX -= offX;
		}
		if (newY < 0) {
			newY -= offY;
		}
		if (newWidth + offX >= pW) {
			newWidth -= newWidth + offX - pW;
		}
		if (newHeight + offY >= pH) {
			newHeight -= newHeight + offY - pH;
		}

		for (int y = 0; y <= newHeight; y++) {
			for (int x = 0; x <= newWidth; x++) {
				setPixel(x + offX, y + offY, color);
			}
		}
	}
	
	
	public static void drawText(String text, int offX, int offY, int color) {

		text = text.toUpperCase();
		int offset = 0;

		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;

			for (int y = 0; y < font.getFontImage().getH(); y++) {
				for (int x = 0; x < font.getWidths()[unicode]; x++) {
					if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
							+ y * font.getFontImage().getW()] == 0xffffffff) {
						setPixel(x + offset + offX, y + offY, color);
					}
				}
			}
			offset += font.getWidths()[unicode];

		}

	}

	public int[] getP() {
		return p;
	}

	public void setP(int[] p) {
		this.p = p;
	}
}
