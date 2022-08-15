package program.game.shootingStars.entities;

import program.game.shootingStars.io.ImageLoader;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class StaticEntity implements Entity {

	public int speed;

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int health;
	protected boolean isDestroyed = false;
	protected BufferedImage img;
	protected BufferedImage explosionImg = ImageLoader.explosionImage;

	public StaticEntity(int speed, BufferedImage img) {
		this.speed = speed;
		this.health = 0;
		this.img = img;
		generateStartPosition();
		init();
	}

	public StaticEntity(int speed, int health, BufferedImage img) {
		this.speed = speed;
		this.health = health;
		this.img = img;
		generateStartPosition();
		init();
	}

	public StaticEntity(int speed, int x, int y, BufferedImage img) {
		this.speed = speed;
		this.health = 0;
		this.x = x;
		this.y = y;
		this.img = img;
		init();
	}

	public StaticEntity(int speed, int x, int y, int health, BufferedImage img) {
		this.speed = speed;
		this.health = health;
		this.x = x;
		this.y = y;
		this.img = img;
		init();
	}

	private void init () {
		this.width = img.getWidth();
		this.height = img.getHeight();
	}

	@Override
	public void draw(Graphics g) {
		if (!isDestroyed)
			g.drawImage(img, x, y, width, height, null);
		else
			g.drawImage(explosionImg, x, y,null);
	}

	@Override
	public void move () {
		y += speed;
	}

	@Override
	public void generateStartPosition () {
		x = (int) (Math.random() * GameConstant.F_WIDTH);
		y = (int) (Math.random() * (2 * -GameConstant.F_HEIGHT + (GameConstant.F_HEIGHT - 30)) - GameConstant.F_HEIGHT);
	}


	// This method check whether the every point
	// of each rectangle is inside the another rect
	@Override
	public boolean isIntersects (StaticEntity p) {
		return isPointInsideBox(this.x, this.y, p) ||                                         // x1, y1
				isPointInsideBox(this.x + this.width, this.y, p) ||                         // x2, y1
				isPointInsideBox(this.x, this.y + this.height, p) ||                        // x1, y2
				isPointInsideBox(this.x + this.width, this.y + this.height, p) ||         // x2, y2
				isPointInsideBox(p.x, p.y, this) ||                                         // px1, py1
				isPointInsideBox(p.x + p.width, p.y, this) ||                             // px2, py1
				isPointInsideBox(p.x, p.y + p.height, this) ||                            // px1, py2
				isPointInsideBox(p.x + p.width, p.y + p.height, this);                  // px2, py2
	}

	private boolean isPointInsideBox (int x, int y, StaticEntity e) {
		return (e.x <= x &&
				e.y <= y &&
				x <= e.x + e.width &&
				y < e.y + e.height);
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

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean destroyed) {
		isDestroyed = destroyed;
	}

	@Override
	public String toString() {
		return "StaticEntity{" +
				"SPEED=" + speed +
				", x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				", health=" + health +
				'}';
	}
}
