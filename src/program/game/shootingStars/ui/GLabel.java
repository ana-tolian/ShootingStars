package program.game.shootingStars.ui;

import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;

public class GLabel extends JLabel {

    public GLabel () {
        setFocusable(false);
        setAlignmentX(SwingConstants.CENTER);
        setAlignmentY(SwingConstants.CENTER);
        setBackground(GameConstant.BUTTON_COLOR);
        setForeground(GameConstant.FONT_COLOR);
        setFont(GameConstant.SYSTEM_FONT);
    }

    public GLabel (String text) {
        setText(text);
        setFocusable(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setBackground(GameConstant.BUTTON_COLOR);
        setForeground(GameConstant.FONT_COLOR);
        setFont(GameConstant.SYSTEM_FONT);
    }
}
