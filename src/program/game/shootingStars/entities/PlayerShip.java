package program.game.shootingStars.entities;

import program.game.shootingStars.ImageLoader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PlayerShip extends StaticEntity {

	protected BufferedImage images [];

	private boolean isMove;
	private boolean isCollectedCoin;
	private boolean isCrushed;

	public PlayerShip(int speed, int x, int y, int health,
					  BufferedImage img, BufferedImage[] images) {
		super(speed, x, y, health, img);
		this.images = images;
	}

	public void move (int x, int y) {
		if (this.x != x || this.y != y)
			isMove = true;
		else
			isMove = false;

		this.x = x;
		this.y = y;
	}

	@Override
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
