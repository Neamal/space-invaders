
package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class Brick extends Rectangle {
	
	private Color col;
	private int w;
	private boolean isVis = true;
	static private int brickNumber;
	public static Object bricks;
	public boolean broken = false;
	
	public Brick(int x, int y, Color c) {
		super(x, y, GDV5.getMaxWindowX()/8 - 4, 23);
		col = c;
		w = GDV5.getMaxWindowX()/8;
	}
	public int getW() {
		return w;
	}
	
	public void setCol(Color c) {
		col = c;
	}
	
	public Color getCol() {
		return col;
	}
	
	public void draw(Graphics2D brick, Brick[] bricks) {
		if (isVis) {
			brick.setColor(col);
			brick.fill(this);
		}
	}
	
	public boolean getVis() {
		return isVis;
	}
	
	public void setIsVis() {
		isVis = false;
	}
	
	public static void drawBricks(Graphics2D brick, Brick[] bricks) {
		for(Brick b : bricks) {
			b.draw(brick, bricks);
		}
	}
}
