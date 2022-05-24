package breakout;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class Paddle extends Rectangle {
	
	double dX;
	
	public Paddle(int x, int y, int w, int h) {
		super(x, y, w, h);
		dX = 12;
	}
	
	public void controlPaddle() {
		if (GDV5.KeysPressed[KeyEvent.VK_D]) {
			this.x += dX;
		} else if (GDV5.KeysPressed[KeyEvent.VK_A]) {
	 		this.x -= dX;
		} else {
			this.x = this.x;
		}
	}
}

