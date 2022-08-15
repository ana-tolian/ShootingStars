package program.game.shootingStars.ui;

import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;

public class GLabel extends JLabel {

    public GLabel () {
        setup();
    }

    public GLabel (String text) {
        setText(text);
        setup();
    }

    private void setup () {
        setFocusable(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setBackground(GameConstant.BUTTON_COLOR);
        setForeground(GameConstant.FONT_COLOR);
        setFont(GameConstant.SYSTEM_FONT);
        setIconTextGap(5);
    }
}
