package program.game.shootingStars.ui;

import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;
import java.awt.*;

public class GButton extends JButton {

    public GButton (String text) {
        setText(text);
        setFocusable(false);
        setAlignmentX(SwingConstants.CENTER);
        setAlignmentY(SwingConstants.CENTER);
        setBackground(GameConstant.BUTTON_COLOR);
        setForeground(GameConstant.FONT_COLOR);
        setPreferredSize(new Dimension (GameConstant.BUTTON_WIDTH, GameConstant.BUTTON_HEIGHT));
        setBorder(BorderFactory.createLineBorder(GameConstant.LINE_COLOR, GameConstant.LINE_THICKNESS));
        setFont(GameConstant.SYSTEM_FONT);

    }
}
