package program.game.shootingStars.menu;

import program.game.shootingStars.Init;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class InfoPanel extends GPanel {
	
	private static final long serialVersionUID = 1L;
	
	private GLabel creditLabel;
	private GButton backButton;
	
	private GPanel backButtonPanel;
	private GPanel creditPanel;
	private GPanel nullPanel;


	public InfoPanel () {
		setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
		
		nullPanel = new GPanel ();
			nullPanel.setPreferredSize(new Dimension (900, 200));
		
		backButtonPanel = new GPanel ();
			backButtonPanel.setPreferredSize(new Dimension (900, 400));
		
		creditPanel = new GPanel ();
			creditPanel.setPreferredSize(new Dimension (900, 400));
			
		creditLabel = new GLabel("Created and developed by ana-tolian");
			creditLabel.setPreferredSize(new Dimension (420, 80));

		backButton = new GButton("Back");
			backButton.addActionListener(new Listener());

			creditPanel.add("Center", creditLabel);
			backButtonPanel.add("North", backButton);
			
			add("North", nullPanel);
			add("Center", creditPanel);
			add("South", backButtonPanel);
			
			
	}

	private void setMainMenuPanel () {
		Init.setMainMenuPanel(this);
		
	}
	
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setMainMenuPanel();
			
		}
	
	}

}
