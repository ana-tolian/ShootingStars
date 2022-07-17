package program.game.shootingStars.entities;

import program.game.shootingStars.ImageLoader;
import program.game.shootingStars.entities.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PlayerShip extends Entity {
	
	protected BufferedImage img;
	protected BufferedImage bulletImg = ImageLoader.bulletSprite;
	protected BufferedImage images [];
	protected ArrayList<Bullet> bullets;
	
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
		bullets = new ArrayList<> ();
		
	}
	
	public PlayerShip (int health, BufferedImage img, int speed, BufferedImage images []) {
		super(health, speed);
		this.img = img;
		this.images = images;
		
		width = img.getWidth();
		height = img.getHeight();
		bullets = new ArrayList<Bullet> ();
		
	}

	public void move () {
		if (up)    y -= SPEED;
		if (down)  y += SPEED;
		if (right) x += SPEED;
		if (left)  x -= SPEED;

	}

	public void move (int x, int y) {
		if (x - this.x > 0)
			x += SPEED;
		else
			x -= SPEED;

		if (y - this.x > 0)
			y += SPEED;
		else
			y -= SPEED;
	}

	public void moveBullet () {
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).move();
	}
	
	public void draw (Graphics g) {
		g.drawImage(img, x, y, width, height, null);

		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isBulletOnScreen()) {
				bullets.get(i).draw(g);
			} else
				bullets.remove(i);
		}
	}
	
	public void drawFire (Graphics g) {
		g.drawImage(images[(int) Math.abs(System.currentTimeMillis() % 14)], x, y + 31, 21, 28, null);
	}

	public void shoot () {
		bullets.add(new Bullet (100, this, bulletImg));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Bullet> getBullets () {
		return bullets;
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
