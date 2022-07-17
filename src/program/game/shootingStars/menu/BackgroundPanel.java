package program.game.shootingStars.menu;

import program.game.shootingStars.*;
import program.game.shootingStars.entities.Asteroid;
import program.game.shootingStars.entities.Coin;
import program.game.shootingStars.entities.EnemyShip;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.variables.changable.Changable;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;


public class BackgroundPanel extends GPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Asteroid> entities;
	private ArrayList<EnemyShip> enemies;
	private ArrayList<Coin> coins;
	
	private PlayerShip player;
	
	private BufferedImage backgroundImage;
	private BufferedImage rocketImage;
	private BufferedImage asteroidImage;
	private BufferedImage enemyImage;
	private BufferedImage bulletImage;
	private BufferedImage coinImage;
	private BufferedImage fireSprites [];

	private int y_img;
	private int score = 0;
	private int length = 0;
	
	public static int health;
	
	private static boolean isGameOn = false;
	
	private Thread thread;
	
	
	public BackgroundPanel () {
		setPreferredSize(new Dimension (GameConstant.F_WIDTH, GameConstant.F_HEIGHT));
		
		int x = GameConstant.F_WIDTH / 2 - 50;
		int y = GameConstant.F_HEIGHT / 2 - 50;

		y_img = 0;
		health = 100;
		
		new ImageLoader();
		backgroundImage = ImageLoader.spaceBackground;
		rocketImage = ImageLoader.rocketSprite;
		fireSprites = ImageLoader.fireAnimationSprites;
		asteroidImage = ImageLoader.asteroidSprite;
		enemyImage = ImageLoader.enemySprite;
		bulletImage = ImageLoader.bulletSprite;
		coinImage = ImageLoader.coinSprite;
		
		entities = new ArrayList<Asteroid> (Changable.asteroidCount);
		enemies = new ArrayList<EnemyShip> (Changable.enemyCount);
		coins = new ArrayList<Coin> (Changable.coinAmount);
		player = new PlayerShip (x, y, health, 10, rocketImage, fireSprites);
		
		generateEntity();


		setFocusable(true);
		requestFocus();
		
		addKeyListener (new KeyL ());
		
		
	}
	
	public void start () {
		isGameOn = true;
		thread = new Thread (this);
		thread.start();
		
	}
	
	public void stop () {
		isGameOn = false;
		
	}
	
	
	public void run () {
		while (isGameOn) {
			try {
				
				for (int i = 0; i < entities.size(); i++) {
					entities.get(i).move();
					
					if (!entities.get(i).isEntityOnScreen()) {
						entities.remove(i);
						generateEntity();
					}
					
					if (entities.get(i).isCollided(player)) {
						systemStop();
						Init.setMainMenuPanel(this);
						return;
						
					}
					
				}
				
				for (int i = 0; i < coins.size(); i++) {
					coins.get(i).move();
					
					if (!coins.get(i).isEntityOnScreen()) {
						coins.remove(i);
						generateEntity();
					}
					
					if (coins.get(i).isCollided(player)) {
						coins.remove(i);
						score++;	
					}
					
				}
				
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).move();
					
					if (!enemies.get(i).isEntityOnScreen()) {
						enemies.remove(i);
						generateEntity();
					}
					
					if (entities.get(i).isCollided(player)) {
						systemStop();
						Init.setMainMenuPanel(this);
						return;
						
					}
					
					if (enemies.get(i).isEnemyInFrontOfPlayer(player)) {
						enemies.get(i).shoot();

					}
					
					enemies.get(i).moveBullet();
					
					if (enemies.get(i).isHitted(player) > 0)
						System.out.println(enemies.get(i).isHitted(player));
						
				}
				
				repaint();
				Thread.sleep((int) 50 / Changable.gameSpeed);
						
				} catch (InterruptedException e) {
					e.printStackTrace();
			}
		
		}
		
	}
	
	
	/*
	 *  	paintComponent() 
	 */
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, y_img - GameConstant.F_HEIGHT, GameConstant.F_WIDTH, GameConstant.F_HEIGHT, null);
		g.drawImage(backgroundImage, 0, y_img, GameConstant.F_WIDTH, GameConstant.F_HEIGHT, null);
		
		y_img += GameConstant.ANIMATION_SPEED;
		length += GameConstant.ANIMATION_SPEED * Changable.gameSpeed / 3;
		
		
		if (y_img >= GameConstant.F_HEIGHT)
			y_img = 0;
		
		if (player.isMove()) 
			player.drawFire(g);
		
		player.draw(g);
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).draw(g);
		}
		
		for (int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(g);
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
			enemies.get(i).drawBullets(g);
		}
		
		
		
		g.setFont(new Font ("Arial", Font.ITALIC, 30));
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 10, 25);
		g.drawString("Distance: " + length, 150, 25);
			
	}
	
	/*
	 * 		Other
	 */
	private void generateEntity () {
		for (int i = 0; i < Changable.asteroidCount - entities.size(); i++) {
			entities.add(new Asteroid (50, 10, 50, asteroidImage));
			
		}
		
		for (int i = 0; i < Changable.coinAmount - coins.size(); i++) {
			coins.add(new Coin (GameConstant.ANIMATION_SPEED, coinImage));
			
		}
		
		for (int i = 0; i < Changable.enemyCount - enemies.size(); i++) {
			enemies.add(new EnemyShip (100, 6, 30, 15, enemyImage, bulletImage));
			
		}
		
	}
	
	private void systemStop () throws InterruptedException {
		for (int i = 0; i < 3; i++)
			Thread.sleep(1000);

	}
	
	public void changeDifficult () {
		Changable.asteroidCount = 5 * Changable.gameDifficult;
		Changable.enemyCount = 3 * Changable.gameDifficult;
		
	}
	
	
	/*
	 *   	Key Listener
	 */
	private class KeyL implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) 
				player.setDirectionUp(true);

			if (e.getKeyCode() == KeyEvent.VK_S) 
				player.setDirectionDown(true);
				
			if (e.getKeyCode() == KeyEvent.VK_D) 
				player.setDirectionRight(true);
			
			if (e.getKeyCode() == KeyEvent.VK_A) 
				player.setDirectionLeft(true);
				
			player.move();
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() ==KeyEvent.VK_PAGE_UP)
				player.setDirectionUp(false);

			if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() ==KeyEvent.VK_PAGE_DOWN)
				player.setDirectionDown(false);
				
			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() ==KeyEvent.VK_END)
				player.setDirectionRight(false);
			
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() ==KeyEvent.VK_HOME)
				player.setDirectionLeft(false);

		}

		@Override
		public void keyTyped(KeyEvent e) {}
		
	}
	

}
