package program.game.shootingStars.ui;

import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;

public class GTextArea extends JTextArea {

    public GTextArea () {
        setup();
    }

    public GTextArea (int row, int col) {
        setRows(row);
        setColumns(col);
        setup();
    }

    public GTextArea (String text) {
        setText(text);
        setup();
    }

    public GTextArea (String text, int row, int col) {
        setRows(row);
        setColumns(col);
        setText(text);
        setup();
    }

    private void setup () {
        setFocusable(false);
        setBackground(GameConstant.BUTTON_COLOR);
        setForeground(GameConstant.FONT_COLOR);
        setFont(GameConstant.SYSTEM_FONT);
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }
}
