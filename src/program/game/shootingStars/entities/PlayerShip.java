package program.game.shootingStars.entities;

import program.game.shootingStars.entities.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class PlayerShip extends Entity {
	
	protected BufferedImage img;
	protected BufferedImage images [];
	
	protected int width;
	protected int height;
	
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	
	
	public PlayerShip(int x, int y, int health, int speed, BufferedImage img, BufferedImage[] images) {
		super(x, y, health, speed);
		this.img = img;
		this.images = images;
		
		width = img.getWidth();
		height = img.getHeight();
		
	}
	
	public PlayerShip (int health, BufferedImage img, int speed, BufferedImage images []) {
		super(health, speed);
		this.img = img;
		this.images = images;
		
		width = img.getWidth();
		height = img.getHeight();
		
	}

	public void move () {
		if (up)    y -= SPEED;
		if (down)  y += SPEED;
		if (right) x += SPEED;
		if (left)  x -= SPEED;

	}
	
	public void draw (Graphics g) {
		g.drawImage(img, x, y, width, height, null);
		
	}
	
	public void drawFire (Graphics g) {
		g.drawImage(images[(int) Math.abs(System.currentTimeMillis() % 14)], x, y + 31, 21, 28, null);
		
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}
	
	
	
	public void setDirectionUp (boolean flag) {
		up = flag;
		
	}
	
	public void setDirectionDown (boolean flag) {
		down = flag;
		
	}
	
	public void setDirectionRight (boolean flag) {
		right = flag;
		
	}
	public void setDirectionLeft (boolean flag) {
		left = flag;
		
	}
	
	
	public boolean isMove () {
		return up || down || left || right;
	}
	


}
