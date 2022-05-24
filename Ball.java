package breakout;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import utilities.GDV5;

import java.awt.Graphics2D;

public class Ball extends Rectangle{
	
	public int dX = 6, dY = -6;
	private Color color = Color.white;
	private int brickResult;
	public boolean broken = false;
	static Brick[] bricks;

	public Ball() {
		this.setSize(20, 20);
		this.setLocation(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY() - 100);
		
	}
	
	public void move(Ball ball, Paddle paddle) {
		this.x += dX;
		this.y += dY;
		if (this.y >= Breakout.getMaxWindowY()) this.dY = -this.dY;
		if (this.x <= 0) this.dX = -this.dX;
		if (this.y <= 0) this.dY = -this.dY;
		if (this.x >= Breakout.getMaxWindowX()) this.dX = -this.dX;
		
		if(ball.intersects(paddle) ) {
			ball.dY = -ball.dY;
		}
	}
	
	public void intersectBrick(Brick[] bricks) {
		for (int i = 0; i < bricks.length; i++) {
			if (bricks[i].getVis() == true && this.intersects(bricks[i])) {
				bricks[i].setIsVis();
				brickResult = GDV5.collisionDirection(bricks[i], this, this.dX, this.dY);
				bounce(brickResult);
			}
		}
	}
	
	public void bounce(int result) {
		if (result % 2 == 1) {
			this.dY = -this.dY;
			broken = true;
		} else {
			this.dX = -this.dX;
			broken = true;
		} 
		
	}
	
	public void ballReset() {
		this.setLocation(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY() - 100);
		this.dX = 0;
		this.dY = 0;
	}
	
	public void draw(Graphics2D ball) {
		ball.setColor(color);
		ball.fill(this);
	}
}
