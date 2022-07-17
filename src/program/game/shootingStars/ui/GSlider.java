package program.game.shootingStars.ui;

import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;
import java.awt.*;

public class GSlider extends JSlider {

    public GSlider () {
        setBackground(GameConstant.BUTTON_COLOR);
        setForeground(GameConstant.FONT_COLOR);
        setForeground(GameConstant.FONT_COLOR);
        setPreferredSize(new Dimension(GameConstant.SLIDER_WIDTH, GameConstant.SLIDER_HEIGHT));
        setPaintTicks(true);
    }
}
