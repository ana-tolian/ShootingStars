package program.game.shootingStars.ui;

import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;

public class GScrollPane extends JScrollPane {

    public GScrollPane (JComponent panel) {
        super(panel);
        setOpaque(true);
        getVerticalScrollBar().setUnitIncrement(16);
        getViewport().getView().setForeground(GameConstant.FONT_COLOR);
    }
}
