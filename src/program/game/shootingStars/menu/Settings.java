package program.game.shootingStars.menu;

import program.game.shootingStars.Init;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.ui.GSlider;
import program.game.shootingStars.variables.changable.Changable;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Settings extends GPanel {
	
	private static final long serialVersionUID = 1L;
	
	private GButton backButton;
	
	private GSlider difficultModeSlider;
	private GSlider speedSlider;
	
	private GLabel speedInfoLabel;
	private GLabel difficultModeInfoLabel;
	private GLabel settingsLabel;
	private GLabel nullLabel1;
	private GLabel nullLabel2;
	private GLabel nullLabel3;
	private GLabel nullLabel4;
	
	private GPanel mainPanel;
	private GPanel backButtonPanel;


	public Settings () {
		setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
		
		mainPanel = new GPanel ();
			mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			mainPanel.setPreferredSize(new Dimension (600, 400));
		
		backButtonPanel = new GPanel ();
			backButtonPanel.setPreferredSize(new Dimension (600, 100));
			
		backButton = new GButton ("Back");
			backButton.addActionListener(new Listener ());
			
			
		settingsLabel = new GLabel ("- Settings -");
			settingsLabel.setPreferredSize(new Dimension (765, 100));
			settingsLabel.setBorder(BorderFactory.createLineBorder(GameConstant.LINE_COLOR, GameConstant.LINE_THICKNESS));

			
		nullLabel1 = new GLabel ();
			nullLabel1.setPreferredSize(new Dimension (800, 40));
			
		nullLabel2 = new GLabel ();
			nullLabel2.setPreferredSize(new Dimension (250, 80));
			
		nullLabel3 = new GLabel ();
			nullLabel2.setPreferredSize(new Dimension (50, 80));
			
		nullLabel4 = new GLabel ();
			nullLabel2.setPreferredSize(new Dimension (50, 80));
			
		speedInfoLabel = new GLabel ("Game speed: ");
			speedInfoLabel.setPreferredSize(new Dimension (220, 80));
			
		difficultModeInfoLabel = new GLabel ("Difficult: ");
			difficultModeInfoLabel.setPreferredSize(new Dimension (220, 80));
			
			
		difficultModeSlider = new GSlider();
			difficultModeSlider.setMaximum(3);
			difficultModeSlider.setMinimum(0);
			difficultModeSlider.setValue(Changable.gameDifficult);
			difficultModeSlider.setMajorTickSpacing(1);
			difficultModeSlider.addChangeListener(new Listener ());
		
		speedSlider = new GSlider ();
			speedSlider.setMaximum(10);
			speedSlider.setMinimum(1);
			speedSlider.setValue(Changable.gameSpeed);
			speedSlider.setMajorTickSpacing(2);
			speedSlider.addChangeListener(new Listener ());
		
			
		backButtonPanel.add("South", backButton);
		mainPanel.add(settingsLabel);
		mainPanel.add(nullLabel1);
		mainPanel.add(speedInfoLabel);
		mainPanel.add(nullLabel3);
		mainPanel.add(speedSlider);
		mainPanel.add(nullLabel2);
		mainPanel.add(difficultModeInfoLabel);
		mainPanel.add(nullLabel4);
		mainPanel.add(difficultModeSlider);
		
		add("Center", mainPanel);
		add("South", backButtonPanel);
		
		setSpeedToLabel(Changable.gameSpeed);
		setGameDifficultToLabel(Changable.gameDifficult);
	}
	
	
	private void setMainMenuPanel () {
		Init.setMainMenuPanel(this);
	}
	
	
	private void setSpeedToLabel (int value) {
		speedInfoLabel.setText("Speed: " + value);
		
	}
	
	
	private void setGameDifficultToLabel (int value) {
		switch (value) {
		case 0: 
			difficultModeInfoLabel.setText("Difficult: Peaceful");
			break;
			
		case 1:
			difficultModeInfoLabel.setText("Difficult: Easy");
			break;
			
		case 2: 
			difficultModeInfoLabel.setText("Difficult: Medium");
			break;
			
		case 3: 
			difficultModeInfoLabel.setText("Difficult: Hard");
			break;
		
		}
	
		
	}
	
	
	private class Listener implements ChangeListener, ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setMainMenuPanel();
			
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();
			int value = slider.getValue();
			
			if (slider == speedSlider) {
				Changable.gameSpeed = value;
				setSpeedToLabel(value);
			}
			
			if (slider == difficultModeSlider) {
				Changable.gameDifficult = value;
				setGameDifficultToLabel(value);
			}
			

		}
		
		
	
	}
	

}
