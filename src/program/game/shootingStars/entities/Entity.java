package program.game.shootingStars.entities;

import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.Graphics;


public abstract class Entity {
	
	public int SPEED = 10;
	
	protected int x;
	protected int y;
	protected int health;


	public Entity (int x, int y, int health, int speed) {
		this.x = x;
		this.y = y;
		this.health = health;
		SPEED = speed;
		
	}

	public Entity (int health, int speed) {
		this.health = health;
		SPEED = speed;
		
		generateStartPosition();
		
	}
	
	
	
	public void draw(Graphics g) {
	
	}
	
	
	public void move() {
		for (int i = 0; i < SPEED; i++)
			y++;
	}
	
	
	public void generateStartPosition () {
		x = (int) (Math.random() * GameConstant.F_WIDTH);
		y = (int) (Math.random() * (2 * -GameConstant.F_HEIGHT + (GameConstant.F_HEIGHT - 30)) - GameConstant.F_HEIGHT);
	}
	
	
	public boolean isEntityOnScreen () {
		if (y < GameConstant.F_HEIGHT + 50)
			return true;
		else
			return false;
	}
	
	public int getHealth() {
		return health;
	}
	
	
	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;	
	}

	
	
}
