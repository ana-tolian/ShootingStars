package program.game.shootingStars.menu;

import program.game.shootingStars.*;
import program.game.shootingStars.entities.EnemyShip;
import program.game.shootingStars.entities.Entity;
import program.game.shootingStars.entities.set.AsteroidSet;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.set.CoinSet;
import program.game.shootingStars.entities.set.EnemyShipSet;
import program.game.shootingStars.entities.set.EntitySet;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.variables.changable.Changable;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class BackgroundPanel extends GPanel implements Runnable {

	private EntitySet entitySet;
	private BufferedImage backgroundImage;

	private int y_img;
	private int score = 0;
	private int length = 0;

	private int px;
	private int py;
	
	private static boolean isGameOn = false;
	
	private Thread thread;
	
	
	public BackgroundPanel () {
		setPreferredSize(new Dimension (GameConstant.F_WIDTH, GameConstant.F_HEIGHT));
		y_img = 0;

		backgroundImage = ImageLoader.spaceBackground;

		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageLoader.cursorImage, new Point(15,15), "CustomCursor"));

		entitySet = new EntitySet(this);

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
				entitySet.move(px, py);

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
		g.drawImage(backgroundImage, 0, y_img - GameConstant.F_HEIGHT, GameConstant.F_WIDTH, GameConstant.F_HEIGHT, this);
		g.drawImage(backgroundImage, 0, y_img, GameConstant.F_WIDTH, GameConstant.F_HEIGHT, this);

		entitySet.draw(g);

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
		if (y_img >= GameConstant.F_HEIGHT) {
			y_img = 0;
		}
	}

	private void incrementScore() {
		score++;
	}

	private void checkIfPlayerCollectedCoin () {
		if (entitySet.getPlayer().isCollectedCoin())
			incrementScore();
	}

	private void checkIfPlayerDestroyed () {
		try {
			if (entitySet.getPlayer().isDestroyed())
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
		new SaveResults(score, length).save();
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
				entitySet.playerShoot();
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
			entitySet.playerShoot();
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
