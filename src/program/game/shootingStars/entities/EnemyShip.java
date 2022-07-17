package program.game.shootingStars.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class EnemyShip extends Asteroid {
	
	protected BufferedImage bulletImg;
	protected ArrayList<Bullet> bullets;
	protected int damage;


	public EnemyShip (int x, int y, int health, int speed, int collisionDamage, int damage, BufferedImage img, BufferedImage bulletImg) {
		super(health, x, y, speed, collisionDamage, img);
		this.bulletImg = bulletImg;
		this.damage = damage;
		
		bullets = new ArrayList<Bullet> ();

	}


	public EnemyShip (int health, int speed, int collisionDamage, int damage, BufferedImage img, BufferedImage bulletImg) {
		super(health, speed, collisionDamage, img);
		this.bulletImg = bulletImg;
		this.damage = damage;

		bullets = new ArrayList<Bullet> ();

	}
	
	
	
	
	public void draw (Graphics g) {
		g.drawImage(img, x, y, width, height, null);
		
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).draw(g);

	}
	
	public void drawBullets (Graphics g) {
//		for (int i = 0; i < bullets.size(); i++)
//			bullets.get(i).draw(g);
		
	}
	
	public void moveBullet () {
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).move();
		
	}
	
	
	public boolean isEnemyInFrontOfPlayer (PlayerShip player) {
		if (x + (width / 2) >= player.getX() 
				&& x + (width / 2) <= player.getX() + player.getWidth() 
					&& y + height < player.getY())
						return true;
		
		return false;
		
	}
	
	public int isHitted (PlayerShip player) {
		int count = 0;
		
		for (int i = 0; i < bullets.size(); i++)
			if (bullets.get(i).isCollided(player)) 
				count++;
		
		return count;
	}
	
	public void shoot () {
		bullets.add(new Bullet (damage, this, bulletImg));
		
	}

}
