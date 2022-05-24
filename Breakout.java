package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Set;

import javax.swing.plaf.SliderUI;

import java.awt.Font;

import utilities.GDV5;
import utilities.SoundDriverHo;

public class Breakout extends GDV5{
	
	boolean start = false;
	boolean	freeze = true;
	int gamemode = 0;
	Ball b1 = new Ball();
	Paddle p1 = new Paddle(Breakout.getMaxWindowX()/2 - 60, Breakout.getMaxWindowY() - 60, 140, 20);
	Scoreboard s = new Scoreboard();
	static int P1Score = 0;
	private int lives = 9;
	static int count = 0;
	private int level = 1;
	static Brick[] bricks;
	private SoundDriverHo s1;
	private SoundDriverHo s2;
	private String[] filenames = new String[1];
	Images image = new Images();
	
	public Breakout() {
		filenames[0] = "crunch.wav";
		s1 = new SoundDriverHo(filenames, this);
		filenames[0] = "lofiintro.wav";
		s2 = new SoundDriverHo(filenames, this);
	}
	
	public static void main(String[] args) {

		Breakout b = new Breakout();
		b.start();
		Color[] colors = {Color.green, Color.cyan, Color.blue, Color.white, Color.magenta, Color.orange, Color.yellow, Color.pink, Color.red};
		bricks = new Brick[8];
		int x = 2, y = 2;
		int bricksPerRow = 8;
		int count = 1, colorIndex = 0;
		for (int i = 0; i < bricks.length; i++) {
			bricks[i] = new Brick(x, y, colors[colorIndex % colors.length]);
			x += bricks[i].getW();
			if (count % bricksPerRow == 0) {
				x = 2;
				y += bricks[i].getHeight() + 2;
				colorIndex++;
			}
			count++;
			System.out.println(i + " " + bricks[i].getX() + " " + bricks[i].getY());
		}

	}
	
 	public void levelChange() {
		if (P1Score == 8 && b1.intersects(p1)) {
			Color[] colors = {Color.green, Color.cyan, Color.blue, Color.white, Color.magenta, Color.orange, Color.yellow, Color.pink, Color.red};
			bricks = new Brick[16];
			int x = 2, y = 2;
			int bricksPerRow = 8;
			int count = 1, colorIndex = 0;
			for (int i = 0; i < bricks.length; i++) {
				bricks[i] = new Brick(x, y, colors[colorIndex % colors.length]);
				x += bricks[i].getW();
				if (count % bricksPerRow == 0) {
					x = 2;
					y += bricks[i].getHeight() + 2;
					colorIndex++;
				}
				count++;
				System.out.println(i + " " + bricks[i].getX() + " " + bricks[i].getY());
			}
			b1.setLocation(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY() - 100);
		}
		
		if (P1Score == 24 && b1.intersects(p1)) {
			Color[] colors = {Color.green, Color.cyan, Color.blue, Color.white, Color.magenta, Color.orange, Color.yellow, Color.pink, Color.red};
			bricks = new Brick[32];
			int x = 2, y = 2;
			int bricksPerRow = 8;
			int count = 1, colorIndex = 0;
			for (int i = 0; i < bricks.length; i++) {
				bricks[i] = new Brick(x, y, colors[colorIndex % colors.length]);
				x += bricks[i].getW();
				if (count % bricksPerRow == 0) {
					x = 2;
					y += bricks[i].getHeight() + 2;
					colorIndex++;
				}
				count++;
				System.out.println(i + " " + bricks[i].getX() + " " + bricks[i].getY());
			}
			b1.setLocation(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY() - 100);
		}
	}
	
	public void update() {
		p1.controlPaddle();
		b1.intersectBrick(bricks);
		if (!freeze) b1.move(b1, p1);
		s.P1Score = P1Score;
		for (int i = 0; i < bricks.length; i++) {
			if (bricks[i].getVis() == true && b1.intersects(bricks[i])) {
				P1Score ++;
				s1.play(0);
			}
		}
		if (!start) s2.play(0);
		if (s2.isPlaying(0) == false) s2.play(0);
		if (b1.y >= Breakout.getMaxWindowY()) lives--;
		if (P1Score == 8 && b1.intersects(p1)) level++;
		if (P1Score == 24 && b1.intersects(p1)) level++;
	}

	public void draw(Graphics2D win) {
				
		if (!start) {
			win.drawImage(image.gif, Breakout.getMaxWindowX()/2 - 120, Breakout.getMaxWindowY() - 350, 240, 300, null);
			win.setColor(Color.PINK);
			win.setFont(new Font("Arial", Font.BOLD, 50));
			win.drawString("Breakout", Breakout.getMaxWindowX()/2 - 120, 100);
			win.setFont(new Font("Arial", Font.BOLD, 20));
			win.drawString("By: Junhyung Yoon", Breakout.getMaxWindowX()/2 - 110, 150);
			win.setFont(new Font("Arial", Font.BOLD, 30));
			win.drawString("Press Enter to Play", 160, 200);
			win.setFont(new Font("Times New Roman", Font.ITALIC, 20));
			win.drawString("Press 'a' to move your paddle left and 'd' to move it to the right", 50, 280);
			win.drawString("Try to pass the third level without expending your 5 lives!", 60, 330);
			win.drawString("Each time you can't return the ball, you lose a life", 100, 380);
			win.drawString("Press 'f' to make the ball start moving", 140, 420);
			lives = 5;
			P1Score = 0;
			b1.setLocation(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY() - 100);
			
		}
		
		if (GDV5.KeysTyped[KeyEvent.VK_ENTER]) {
			win.drawImage(image.background, 0, 0, Breakout.getMaxWindowX(), Breakout.getMaxWindowY(), null);
			start = true;
			win.setColor(Color.WHITE);
			win.draw(b1);
			win.fill(b1);
			win.setColor(Color.RED);
			win.draw(p1);
			win.fill(p1);
			Brick.drawBricks(win, bricks);
			s.Scoreboard(win);
			win.setColor(Color.WHITE);
			win.setFont(new Font("Arial", Font.BOLD, 20));
			win.drawString("Lives: " + lives, Breakout.getMaxWindowX()/2 - 240, Breakout.getMaxWindowY() - 80);
			win.drawString("Level: " + level, Breakout.getMaxWindowX() - 140, Breakout.getMaxWindowY() - 80);
			if (P1Score >= 8) p1.setSize(200,10); 
			if (P1Score >= 24) b1.setSize(40,40);
			levelChange();
		}

		if (GDV5.KeysTyped[KeyEvent.VK_F]) {
			freeze = false;
		}
		
		if (lives <= 0) {
			GDV5.KeysTyped[KeyEvent.VK_ENTER] = false;
			win.setColor(Color.RED);
			win.setFont(new Font("Arial", Font.BOLD, 50));
			win.drawString("Gameover", Breakout.getMaxWindowX()/2 - 120, 100);
			win.setFont(new Font("Arial", Font.ITALIC, 50));
			win.drawString("Press ESCAPE to go back to menu", Breakout.getMaxWindowX()/2 - 300, 200);
			p1.setSize(140,20);
			b1.setSize(20,20);
			level = 1;
		}
		
		if (GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
			start = false;
			freeze = true;
			GDV5.KeysTyped[KeyEvent.VK_ENTER] = false;
			GDV5.KeysTyped[KeyEvent.VK_F] = false;
			p1.setLocation(Breakout.getMaxWindowX()/2 - 60, Breakout.getMaxWindowY() - 60);
			Color[] colors = {Color.green, Color.cyan, Color.blue, Color.white, Color.magenta, Color.orange, Color.yellow, Color.pink, Color.red};
			bricks = new Brick[8];
			int x = 2, y = 2;
			int bricksPerRow = 8;
			int count = 1, colorIndex = 0;
			for (int i = 0; i < bricks.length; i++) {
				bricks[i] = new Brick(x, y, colors[colorIndex % colors.length]);
				x += bricks[i].getW();
				if (count % bricksPerRow == 0) {
					x = 2;
					y += bricks[i].getHeight() + 2;
					colorIndex++;
				}
				count++;
				System.out.println(i + " " + bricks[i].getX() + " " + bricks[i].getY());
			}
		}
		
		if (P1Score >= 56) {
			GDV5.KeysTyped[KeyEvent.VK_ENTER] = false;
			win.setColor(Color.GREEN);
			win.setFont(new Font("Arial", Font.BOLD, 50));
			win.drawString("You Won!", Breakout.getMaxWindowX()/2 - 120, 100);
			win.setFont(new Font("Arial", Font.ITALIC, 50));
			win.drawString("Press ESCAPE to go back to menu", Breakout.getMaxWindowX()/2 - 300, 200);
			p1.setSize(140,20);
			b1.setSize(20,20);
			level = 1;
		}
	}
	
}
