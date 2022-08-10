package program.game.shootingStars.ui;


import program.game.shootingStars.ImageLoader;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class GScaleLabel extends GLabel {

    private int i;
    private BufferedImage scale [];

    public GScaleLabel (int i) {
        this.i = i;

        scale = ImageLoader.moduleLevelSprite;
        setIcon(new ImageIcon(scale[i]));
    }

    public void increment () {
        i++;
        refreshIcon();
    }

    private void refreshIcon() {
        setIcon(new ImageIcon(scale[i]));
    }
}
