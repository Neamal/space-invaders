package breakout;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.FileInputStream;

public class Images {
	
	BufferedImage background; {
        try {
            File file = new File("C:/Users/naema/Pictures/wp6400016.jpg");
            FileInputStream fis = new FileInputStream(file);
            background = ImageIO.read(fis);
        } catch (IOException e) {
            System.err.println(e);
        }
      }
	
	BufferedImage gif; {
        try {
            File file = new File("C:/Users/naema/Pictures/tenor.gif");
            FileInputStream fis = new FileInputStream(file);
            gif = ImageIO.read(fis);
        } catch (IOException e) {
            System.err.println(e);
        }
        }
}

