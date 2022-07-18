package program.game.shootingStars.entities;

import program.game.shootingStars.ImageLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PlayerShip extends StaticEntity {

	protected BufferedImage bulletImg = ImageLoader.bulletSprite;
	protected BufferedImage images [];
	protected ArrayList<Bullet> bullets;

	private boolean isMove;
	private boolean isCollectedCoin;
	private boolean isCrushed;

	public PlayerShip(int speed, int x, int y, int health,
					  BufferedImage img, BufferedImage bulletImg, BufferedImage[] images) {
		super(speed, x, y, health, img);
		this.bulletImg = bulletImg;
		this.images = images;
		this.bullets = new ArrayList<>();
	}

	public void move (int x, int y) {
		if (this.x != x || this.y != y)
			isMove = true;
		else
			isMove = false;

		this.x = x;
		this.y = y;
	}

	public void moveBullet () {
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).move();
	}

	@Override
	public void draw (Graphics g) {
		g.drawImage(img, x, y, width, height, null);

		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).isEntityOnScreen()) {
				bullets.get(i).draw(g);
			} else
				bullets.remove(i);
		}
	}
	
	public void drawFire (Graphics g) {
		g.drawImage(images[(int) Math.abs(System.currentTimeMillis() % 14)], x, y + 31, 21, 28, null);
	}

	public void shoot () {
		bullets.add(new Bullet (100, this.x + (width >> 1) - 1, this.y, 0, true, bulletImg));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setCrushed (boolean c) {
		isCrushed = c;
	}

	public void setCollectedCoin(boolean collectedCoin) {
		isCollectedCoin = collectedCoin;
	}

	public boolean isCrushed () {
		return isCrushed;
	}

	public boolean isCollectedCoin() {
		boolean state = isCollectedCoin;
		if (isCollectedCoin)
			isCollectedCoin = !isCollectedCoin;
		return state;
	}

	public boolean isMove () {
		return isMove;
	}
	


}
