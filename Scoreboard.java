package breakout;

import java.awt.Graphics2D;
import java.awt.Font;

import utilities.GDV5;

public class Scoreboard{
	
	int P1Score = 0;
	
	
	public void Scoreboard(Graphics2D win) {
		Font f1 = new Font("Arial", Font.PLAIN, 50);
		win.setFont(f1);
		win.drawString("Score:" + P1Score + " ", Breakout.getMaxWindowX()/2 - 100, 500);
	}
}
