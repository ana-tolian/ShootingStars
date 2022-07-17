package program.game.shootingStars.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Asteroid extends Entity {
	
	protected BufferedImage img;
	
	protected int collisionDamage;
	protected int width;
	protected int height;
	

	public Asteroid (int x, int y, int health, int speed, int collisionDamage, BufferedImage img) {
		super(x, y, health, speed);
		this.img = img;
		this.collisionDamage = collisionDamage;
		
		width = img.getWidth();
		height = img.getHeight();
		
	}

	public Asteroid (int health, int speed, int collisionDamage, BufferedImage img) {
		super(health, speed);
		this.img = img;
		this.collisionDamage = collisionDamage;
		
		width = img.getWidth();
		height = img.getHeight();
		
		generateStartPosition();
		
	}
	
	
	@Override
	public void draw (Graphics g) {
		g.drawImage(img, x, y, width, height, null);
		
	}
	
	public boolean isCollided (PlayerShip player) {
		if (x >= player.getX() && x < player.getX() + player.getWidth()
					&& y >= player.getY() && y < player.getY() + player.getHeight())
						return true;
		
		else if (x + width > player.getX() && x < player.getX() + player.getWidth() 
					&& y + height > player.getY() && y + height < player.getX() + player.getWidth())
						return true;
		
		else if (player.getX() + player.getWidth() > x && player.getX() + player.getWidth() < x + width 
					&& player.getY() + player.getHeight() > y && player.getY() + player.getHeight() < y + height)
						return true;
		
		return false;
		
	}
	
	
	public int getWidth () {
		return width;	
	}
	
	public int getHeight () {
		return height;
	}
	
	public int getCollisionDamage () {
		return collisionDamage;
	}
	


}
