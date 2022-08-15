package program.game.shootingStars.entities;

import java.awt.*;
import java.awt.image.BufferedImage;


public class PlayerShip extends EnemyShip {

	protected BufferedImage[] images;
	protected final PlayerShipModuleStats stats;

	private int accumulatedDamage;

	protected final int damage;
	protected final int numberOfGuns;
	protected final int [] gunPosX;
	protected final int [] gunPosY;

	private boolean isMove;
	private boolean isCollectedCoin;


	public PlayerShip(int speed, int x, int y, int health,
					  BufferedImage img, BufferedImage[] images, int numberOfGuns,
					  int [] gunPosX , int [] gunPosY, PlayerShipModuleStats stats, int damage) {
		super(speed, x, y, health, img);
		this.images = images;
		this.numberOfGuns = numberOfGuns;
		this.gunPosX = gunPosX;
		this.gunPosY = gunPosY;
		this.stats = stats;
		this.damage = damage;

		this.accumulatedDamage = 0;
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

	public PlayerShipModuleStats getStats () {
		return stats;
	}

	public int getDamage () {
		return stats.getWeaponLevel() * stats.getWeaponLevel() * damage + 10;
	}

	public void setAccumulatedDamage (int d) {
		accumulatedDamage += d;
	}

	public int getAccumulatedDamage () {
		return accumulatedDamage;
	}

	@Override
	public int getHealth () {
		return health * stats.getHullLevel() * stats.getHullLevel() + 10;
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
