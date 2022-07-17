package program.game.shootingStars.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Coin extends Asteroid {


	public Coin (int x, int y, int speed, BufferedImage img) {
		super(x, y, 1, speed, 0, img);
		
	}

	public Coin (int speed, BufferedImage img) {
		super(1, speed, 0, img);
		
		
	}
	
	@Override
	public void draw (Graphics g) {
		g.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);
		
	}

}
