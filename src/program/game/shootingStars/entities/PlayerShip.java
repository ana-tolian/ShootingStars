package program.game.shootingStars.entities;

import java.awt.*;
import java.awt.image.BufferedImage;


public class PlayerShip extends EnemyShip {

	protected BufferedImage[] images;
	protected final PlayerShipModuleStats stats;

	private boolean isMove;
	private boolean isCollectedCoin;


	public PlayerShip(int speed, int x, int y, int health,
					  BufferedImage img, BufferedImage[] images, int numberOfGuns,
					  int [] gunPosX , int [] gunPosY, PlayerShipModuleStats stats, int damage) {
		super(speed, x, y, health, img, numberOfGuns, gunPosX, gunPosY, damage);
		this.images = images;
		this.stats = stats;
	}

	public void move (int x, int y) {
		isMove = this.x != x || this.y != y;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw (Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public void drawFire (Graphics g) {
		g.drawImage(images[(int) Math.abs(System.currentTimeMillis() % 14)],
				x + ((width - images[0].getWidth()) >> 1), y + height, null);
	}

	public Point [] shoot () {
		Point [] shots = new Point[numberOfGuns];
		for (int i = 0; i < numberOfGuns; i++)
			shots[i] = new Point(gunPosX[i] + this.getX(), gunPosY[i] + this.getY());
		return shots;
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

	public PlayerShipModuleStats getStats () {
		return stats;
	}

	@Override
	public int getDamage () {
		return damage * stats.getWeaponLevel() * stats.getWeaponLevel() + 10;
	}

	@Override
	public int getHealth () {
		return health * stats.getHullLevel() * stats.getHullLevel() + 10;
	}

	public void setCollectedCoin(boolean collectedCoin) {
		isCollectedCoin = collectedCoin;
	}
	
	@Override
	public String toString () {
		String s = health + "#" + numberOfGuns + "#";
		for (int i = 0; i < numberOfGuns; i++) {
			s += gunPosX[i] + "#";
			s += gunPosY[i] + "#";
		}
		return s;
	}

}
