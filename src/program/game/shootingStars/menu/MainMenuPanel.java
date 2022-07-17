package program.game.shootingStars.menu;

import program.game.shootingStars.Init;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;


public class MainMenuPanel extends GPanel {

	private GPanel panel;
	private GPanel nullPanel;

	private GButton [] buttons = {new GButton ("Play"), new GButton("Shop"),
									new GButton ("Settings"), new GButton ("Info"), new GButton ("Exit")};


	public MainMenuPanel () {
		setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
		
		nullPanel = new GPanel ();
		  nullPanel.setPreferredSize(new Dimension (50, 50));
		  nullPanel.setMaximumSize(new Dimension (50, 50));
		  nullPanel.setMinimumSize(new Dimension (50, 50));
		
		panel = new GPanel ();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel.setPreferredSize(new Dimension (200, GameConstant.F_HEIGHT));
			panel.setMaximumSize(new Dimension (200, GameConstant.F_HEIGHT ));
			panel.setMinimumSize(new Dimension (200, GameConstant.F_HEIGHT ));
		
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(new ActionL ());
			panel.add(buttons[i]);
		}
		
		add("North", nullPanel);
		add("Center", panel);

	}
	
	
	private class ActionL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
	
			if (button.getActionCommand().equals("Play")) {
				Init.setPlayPanel(false);
			
			} else if (button.getActionCommand().equals("Settings")) {
				Init.setSettingPanel();
			
			} else if (button.getActionCommand().equals("Shop")) {
				Init.setShopPanel();
			
			} else if (button.getActionCommand().equals("Info")) {
				Init.setInfoPanel();
				
			} else if (button.getActionCommand().equals("Exit"))
				System.exit(0);

			
		}
		
		
	}

}
