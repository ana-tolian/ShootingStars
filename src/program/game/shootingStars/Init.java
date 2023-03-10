package program.game.shootingStars;

import program.game.shootingStars.io.GameGeneralDataIO;
import program.game.shootingStars.io.GamePlayerDataIO;
import program.game.shootingStars.io.ImageLoader;
import program.game.shootingStars.menu.*;
import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;


public class Init {
	
	public static BackgroundPanel playMode;
	public static MainMenuPanel main;
	public static Settings settings;
	public static InfoPanel info;
	public static PausePanel pausePanel;
	public static ShopPanel shopPanel;
	protected static JFrame frame;

	
	Init () {
		createUI();
	}
	
	
	private void createUI () {
		frame = new JFrame ("Shooting Star");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(GameConstant.F_WIDTH, GameConstant.F_HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		new ImageLoader();

		main = new MainMenuPanel ();
		settings = new Settings ();
		info = new InfoPanel ();
		pausePanel = new PausePanel();
		shopPanel = new ShopPanel();

		new GamePlayerDataIO();
		new GameGeneralDataIO();
		
		frame.add(info);
		frame.add(settings);
		frame.add(main);
		main.setVisible(true);
		
		frame.setVisible(true);
		
	}
	
	public static void changeView (JPanel changeTo, JPanel changeFrom) {
		frame.add(changeTo);
		changeFrom.setVisible(false);
		changeTo.setVisible(true);
		
	}
	
	public static void setMainMenuPanel (JPanel changeFrom) {
		changeView(main, changeFrom);
		
		if (changeFrom == playMode || changeFrom == pausePanel) {
			playMode.save();
			playMode.stop();
			playMode = null;
		}
		
	}
	
	public static void setPlayPanel (boolean resumeGame) {
		if (!resumeGame) {
			playMode = new BackgroundPanel();
			changeView(playMode, main);

		} else {
			changeView(playMode, pausePanel);
		}
		playMode.changeDifficult();
		playMode.start();
		playMode.setFocusable(true);
		playMode.requestFocus();
	}

	public static void setPausePanel () {
		changeView(pausePanel, playMode);
		playMode.stop();
	}
	
	public static void setSettingPanel () {
		changeView(settings, main);

	}
	
	public static void setShopPanel () {
		changeView(shopPanel, main);
		shopPanel.refreshBalanceLabel();
	}
	
	public static void setInfoPanel () {
		changeView(info, main);
		
	}
         
	
	public static void main (String [] args) {
		SwingUtilities.invokeLater(() -> new Init());
	}

}
