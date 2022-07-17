package program.game.shootingStars;

import program.game.shootingStars.menu.BackgroundPanel;
import program.game.shootingStars.menu.InfoPanel;
import program.game.shootingStars.menu.MainMenuPanel;
import program.game.shootingStars.menu.Settings;
import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Init {
	
	public static BackgroundPanel playMode;
	public static MainMenuPanel main;
	public static Settings settings;
	public static InfoPanel info;
	
	protected static JFrame frame;
	
	
	Init () {
		createUI();
	}
	
	
	private void createUI () {
		frame = new JFrame ("Shooting stars");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(GameConstant.F_WIDTH, GameConstant.F_HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		main = new MainMenuPanel ();
		settings = new Settings ();
		info = new InfoPanel ();
		
		frame.add(info);
		frame.add(settings);
		frame.add(main);
		main.setVisible(true);
		
		frame.setVisible(true);
		
	}
	
	public static void changeView (JPanel pane) {
		frame.add(pane);
		
	}
	
	public static void setMainMenuPanel (JPanel pane) {
		changeView(main);
		pane.setVisible(false);
		main.setVisible(true);
		
		if (pane == playMode) {
			playMode.stop();
			playMode = null;
		}
		
	}
	
	public static void setPlayPanel () {
		playMode = new BackgroundPanel ();
		playMode.changeDifficult();
		changeView(playMode);
		main.setVisible(false);
		playMode.setVisible(true);
		playMode.start();
		playMode.setFocusable(true);
		playMode.requestFocus();
	}
	
	public static void setSettingPanel () {
		changeView(settings);
		main.setVisible(false);
		settings.setVisible(true);
	}
	
	public static void setShopPanel () {
		
		
	}
	
	public static void setInfoPanel () {
		changeView(info);
		main.setVisible(false);
		info.setVisible(true);
		
	}
         
	
	public static void main (String args []) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				new Init();
			}
		});
	}

}
