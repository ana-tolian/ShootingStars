package program.game.shootingStars.entities;

import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {
	
	protected int SPEED = 4;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int damage;
	
	protected BufferedImage img;


	public Bullet (int damage, EnemyShip enemy, BufferedImage img) {
		x = enemy.getX() + enemy.getWidth() / 2;
		y = enemy.getY() + enemy.getHeight();
		width = img.getWidth();
		height = img.getHeight();
		
	}

	public Bullet (int damage, PlayerShip player, BufferedImage img) {
		x = player.getX() + player.getWidth() / 2;
		y = player.getY() + player.getHeight();
		width = img.getWidth();
		height = img.getHeight();
		
	}
	
	
	
	public void draw (Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public void move () {
		y += SPEED;
		
	}
	
	public boolean isCollided (PlayerShip player) {
		if (x >= player.getX() && x < player.getX() + player.getWidth()
			&& y >= player.getY() && y < player.getY() + player.getHeight())
				return true;

		else if (x + width > player.getX() && x < player.getX() + player.getWidth() 
			&& y + height > player.getY() && y + height < player.getX() + player.getWidth())
				return true;

		else if (player.getX() + player.getWidth() > x && player.getX() + player.getWidth() < x + width 
			&& player.getY() + player.getHeight() > y && player.getY() + player.getHeight() < y + height)
				return true;

		return false;
		
	}
	
	
	public boolean isBulletOnScreen () {
		if (y < GameConstant.F_HEIGHT + 50)
			return true;
		else
			return false;
	}
	
	public int getDamage() {
		return damage;
	}
	
	
	public int getWidth () {
		return width;
	}
	

	public int getHeight () {
		return height;
	}
	
	
	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;
	}
	
}
