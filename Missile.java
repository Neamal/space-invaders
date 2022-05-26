package breakout;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import utilities.GDV5;
import java.awt.Graphics2D;

public class Missile extends Rectangle{
	public int dY = 10;
	private boolean isVis = false;
	private Color color = Color.white;
	private int brickResult;
	public boolean broken = false;
	static Brick[] bricks;
	private Paddle p1 = new Paddle(Breakout.getMaxWindowX()/2 - 60, Breakout.getMaxWindowY() - 60, 140, 20);
	
	public Missile() {
		this.setSize(6, 15);
		this.setLocation((int)p1.getX(),(int) p1.getY());
	}
	
	public void fire(Missile missile, Paddle paddle) {
		if(GDV5.KeysPressed[KeyEvent.VK_V] && missile.getVis() == false) {
			missile.isVis = true;
		}
		if(missile.getVis() == true) {
			this.y -= dY;
		}
		if (this.y <= 0) {
			missile.setIsVis();
		}
	}
	public void intersectBrick(Brick[] bricks) {
		for (int i = 0; i < bricks.length; i++) {
			if(bricks[i].getVis() == true && this.intersects(bricks[i])) {
				bricks[i].setIsVis();
				this.setIsVis();
			}
		}
	}
	public boolean getVis() {
		return isVis;
	}
	
	public void setIsVis() {
		isVis = false;
	}
}
