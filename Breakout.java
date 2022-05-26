package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class Breakout extends GDV5{
	
	BreakoutBall one = new BreakoutBall(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2, 20, 20);
	BreakoutBall two = new BreakoutBall(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2, 40, 40);
	BreakoutPaddle paddle1 = new BreakoutPaddle(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()-30, 90, 20);
	BreakoutScoreboard s = new BreakoutScoreboard();
	static int gameState = 0;
	static int level = 4;
	static int lives = 5;
	private boolean check = true;
	
	public static void main(String[] args) {
		BreakoutBrick.makeBricks(104);
		Breakout b1 = new Breakout();
		b1.start();
	}
	
	
	public void powerUp() {
		if (level == 2) {
			one.height = 40;
			one.width = 40;
		}
		if (level == 3) {
			paddle1.width = 140;
		}
		
	}
	
	public void runGame1() {
		
	
		one.move(one, paddle1);
		paddle1.controlPaddle();
		one.brickHit();
		powerUp();
		if (one.Score1 == 2080 && check) {
			level++;
			BreakoutBrick.makeBricks(128);
			check = false;
			one.setLocation(Breakout.getMaxWindowX()/2, Breakout.getMaxWindowY()/2);
			
		}
		if (one.Score1 == 2100) {
			check = true;
		}
		if (one.Score1 == 4640 && check) {
			level++;
			BreakoutBrick.makeBricks(152);
			one.setLocation(Breakout.getMaxWindowX()/2, Breakout.getMaxWindowY()/2);
			check = false;
		}
		if (one.Score1 == 4660) {
			check = true;
		}
		if (one.Score1 == 7680 && check) {
			level++;
			BreakoutBrick.makeBricks(168);
			one.setLocation(Breakout.getMaxWindowX()/2, Breakout.getMaxWindowY()/2);
			check = false;
		}
		if (one.Score1 == 9160) {
			gameState = 4;
		}
		if(one.dY == GDV5.getMaxWindowY()) {
			lives-=1;
		}
		if(level == 4) {
			two.move(two, paddle1);
			two.brickHit();
		}

		}
		
	public int level(double bx) {
		if(one.y == Breakout.getMaxWindowY()) {
			lives -= 1;
		}
		return lives;
	}
	
	public void setGameState() {
		if (GDV5.KeysPressed[KeyEvent.VK_1]) gameState = 1;
		if (GDV5.KeysPressed[KeyEvent.VK_2]) gameState = 2;
		if (GDV5.KeysPressed[KeyEvent.VK_3]) gameState = 3;
		if (GDV5.KeysPressed[KeyEvent.VK_4]) gameState = 4;
		if (GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) gameState = 0;
		if (GDV5.KeysPressed[KeyEvent.VK_R]) {
			BreakoutBrick.makeBricks(104);
			one.setLocation(Breakout.getMaxWindowX()/2, Breakout.getMaxWindowY()/2);
			one.width = 20;
			one.height = 20;
			gameState = 0;
			one.Score1 = 0;
			level = 1;
			lives = 5;
		}
	}
	
	
	
	public void update() {
		setGameState();
		if (gameState == 1) {
			runGame1();
			level(one.dY);
		}
		if(lives == 0) {
			gameState = 3;
		}
		//BreakoutBrick.bricks[index].setIsVis();
		//if (drawPart == 0) {
			//particle.makeParticles(BreakoutBrick.bricks[index]);
		//}
		//drawPart = 1;
		//particle.moveParticles();
		//count++;
		//if (count % 60 == 0) {
			//index += 1;
			//drawPart = 0;
		//}	
	}	
	public void draw(Graphics2D win) {
		if(gameState == 1) {	
		for (BreakoutBrick b: BreakoutBrick.getBricks()) {
		b.draw(win);
		win.setColor(Color.MAGENTA);
		win.draw(one);
		win.fill(one);
		win.setColor(Color.RED);
		win.draw(paddle1);
		win.fill(paddle1);
		one.Score(win);
		Font f1 = new Font("TimesNewRoman", Font.BOLD, 15);
		win.setFont(f1);
		win.drawString("Level " + level, 20, Breakout.getMaxWindowY()-20);
		win.drawString("Lives: " + lives, Breakout.getMaxWindowX()-75, Breakout.getMaxWindowY()-20);
		}
		if (level == 4) {
			win.setColor(Color.orange);
			win.draw(two);
			win.fill(two);
		}
		}
		else if (gameState == 0) {
		s.splash(win);
	}
		else if (gameState == 2) {
		s.howToPlay(win);
	}
		else if(gameState == 3) {
		s.lose(win);	
		}
		else if(gameState == 4) {
		s.winner(win);
		}
}
}
	

