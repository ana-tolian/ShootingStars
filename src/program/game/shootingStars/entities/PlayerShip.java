package program.game.shootingStars.entities;

import program.game.shootingStars.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PlayerShip extends StaticEntity {

	protected BufferedImage[] images;

	private int numberOfGuns;
	private int[] gunPosX;
	private int gunPosY [];

	private boolean isMove;
	private boolean isCollectedCoin;


	public PlayerShip(int speed, int x, int y, int health,
					  BufferedImage img, BufferedImage[] images, int numberOfGuns, int gunPosX [], int gunPosY []) {
		super(speed, x, y, health, img);
		this.images = images;
		this.numberOfGuns = numberOfGuns;
		this.gunPosX = gunPosX;
		this.gunPosY = gunPosY;
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

	public Point [] shoot () {
		Point shots [] = new Point[numberOfGuns];
		for (int i = 0; i < numberOfGuns; i++)
			shots[i] = new Point(gunPosX[i] + this.getX(), gunPosY[i] + this.getY());
		return shots;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumberOfGuns () {
		return numberOfGuns;
	}

	public void setCollectedCoin(boolean collectedCoin) {
		isCollectedCoin = collectedCoin;
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
