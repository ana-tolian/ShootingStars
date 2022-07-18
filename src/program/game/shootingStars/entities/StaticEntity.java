package program.game.shootingStars.entities;

import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class StaticEntity implements Entity {
	
	public int SPEED = 10;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int health;
	protected BufferedImage img;

	public StaticEntity(int speed, int health, BufferedImage img) {
		this.SPEED = speed;
		this.health = health;
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.img = img;
		generateStartPosition();
	}

	public StaticEntity(int speed, int x, int y, int health, BufferedImage img) {
		this.SPEED = speed;
		this.health = health;
		this.x = x;
		this.y = y;
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.img = img;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	@Override
	public void generateStartPosition () {
		x = (int) (Math.random() * GameConstant.F_WIDTH);
		y = (int) (Math.random() * (2 * -GameConstant.F_HEIGHT + (GameConstant.F_HEIGHT - 30)) - GameConstant.F_HEIGHT);
	}

	@Override
	public boolean isEntityOnScreen () {
		if (y < GameConstant.F_HEIGHT + 50)
			return true;
		else
			return false;
	}

	public int getHealth() {
		return health;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;	
	}

	@Override
	public String toString() {
		return "StaticEntity{" +
				"SPEED=" + SPEED +
				", x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				", health=" + health +
				'}';
	}
}
