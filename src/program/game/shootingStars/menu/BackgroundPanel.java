package program.game.shootingStars.menu;

import program.game.shootingStars.*;
import program.game.shootingStars.entities.EnemyShip;
import program.game.shootingStars.entities.set.AsteroidSet;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.set.CoinSet;
import program.game.shootingStars.entities.set.EnemyShipSet;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.variables.changable.Changable;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class BackgroundPanel extends GPanel implements Runnable {
	
	private PlayerShip player;
	private AsteroidSet asteroids;
	private CoinSet coins;
	private EnemyShipSet enemies;
	
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
	private boolean state = false;

	private int px;
	private int py;
	
	public static int health;
	
	private static boolean isGameOn = false;
	
	private Thread thread;
	
	
	public BackgroundPanel () {
		setPreferredSize(new Dimension (GameConstant.F_WIDTH, GameConstant.F_HEIGHT));
		px = GameConstant.F_WIDTH / 2 - 50;
		py = GameConstant.F_HEIGHT / 2 - 50;
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
		checkImage(backgroundImage, this);
		checkImage(rocketImage, this);
		checkImage(asteroidImage, this);
		checkImage(enemyImage, this);
		checkImage(bulletImage, this);
		checkImage(coinImage, this);

		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageLoader.cursorImage, new Point(15,15), "CustomCursor"));

		player = new PlayerShip (10, px, py, health, rocketImage, bulletImage, fireSprites);
		asteroids = new AsteroidSet(asteroidImage);
		coins = new CoinSet(coinImage);
		enemies = new EnemyShipSet(enemyImage, bulletImage);

		setFocusable(true);
		requestFocus();
		addKeyListener (new Listener ());
		addMouseMotionListener(new Listener());
		addMouseListener(new Listener());
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
				moveBackground();
				player.move(px, py);
				player.moveBullet();
				coins.move(player);
				asteroids.move(player);
				enemies.move(player);

				repaint();

				checkIfPlayerCollectedCoin();
				checkIfPlayerDestroyed();

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
		
		if (player.isMove()) 
			player.drawFire(g);
		
		player.draw(g);
		asteroids.draw(g);
		coins.draw(g);
		enemies.draw(g);

		g.setFont(GameConstant.SYSTEM_FONT);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 10, 25);
		g.drawString("Distance: " + length, 150, 25);
	}
	
	/*
	 * 		Other
	 */
	private void moveBackground () {
		y_img += GameConstant.ANIMATION_SPEED;
		length += GameConstant.ANIMATION_SPEED * Changable.gameSpeed / 3;
		if (y_img >= GameConstant.F_HEIGHT)
			y_img = 0;
	}

	private void incrementScore() {
		score++;
	}

	private void checkIfPlayerCollectedCoin () {
		if (player.isCollectedCoin())
			incrementScore();
	}

	private void checkIfPlayerDestroyed () {
		try {
			if (player.isCrushed())
				systemStop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void systemStop () throws InterruptedException {
		for (int i = 0; i < 3; i++)
			Thread.sleep(700);
		Init.setMainMenuPanel(this);

	}

	public void save () {
		new SaveResults(score, length);
	}
	
	public void changeDifficult () {
		Changable.asteroidCount = 5 * Changable.gameDifficult;
		Changable.enemyCount = 3 * Changable.gameDifficult;
		
	}
	
	
	/*
	 *   	Key Listener
	 */
	private class Listener implements KeyListener, MouseMotionListener, MouseListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				Init.setPausePanel();

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				player.shoot();
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) { }

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) { }

		@Override
		public void mousePressed(MouseEvent e) {
			player.shoot();
		}

		@Override
		public void mouseReleased(MouseEvent e) { }

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }

		@Override
		public void mouseDragged(MouseEvent e) {
			px = e.getX();
			py = e.getY() + 200;
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			px = e.getX();
			py = e.getY() + 200;
		}
	}
	

}
