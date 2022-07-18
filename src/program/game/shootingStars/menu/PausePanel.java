package program.game.shootingStars.menu;

import program.game.shootingStars.Init;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PausePanel extends GPanel {

    private GPanel panel;
    private GPanel logoPanel;

    private GLabel logoLabel;

    private GButton[] buttons = {new GButton ("Resume"), new GButton ("Back to menu"), new GButton ("Exit")};


    public PausePanel () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        logoLabel = new GLabel("/ Shooting Star /");
        logoLabel.setFont(GameConstant.LOGO_FONT);
        logoLabel.setPreferredSize(new Dimension (400, 110));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(90, 0, 100, 0));

        logoPanel = new GPanel ();
        logoPanel.add(logoLabel);

        panel = new GPanel ();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(new Dimension (400, GameConstant.F_HEIGHT));
        panel.setMaximumSize(new Dimension (400, GameConstant.F_HEIGHT ));
        panel.setMinimumSize(new Dimension (400, GameConstant.F_HEIGHT ));


        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(new ActionL());
            panel.add(buttons[i]);
        }

        add("North", logoPanel);
        add("Center", panel);

    }


    private class ActionL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button.getActionCommand().equals("Resume")) {
                Init.setPlayPanel(true);

            } else if (button.getActionCommand().equals("Back to menu")) {
                Init.setMainMenuPanel(Init.pausePanel);

            } else if (button.getActionCommand().equals("Exit")) {
                Init.playMode.save();
                System.exit(0);
            }
        }


    }
}
