package program.game.shootingStars.ui;

import program.game.shootingStars.io.ImageLoader;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class GScaleLabel extends GLabel {

    private int i;
    private BufferedImage scale [];

    public GScaleLabel (int i) {
        this.i = i - 1;

        if (this.i < 0)
            this.i = 0;

        scale = ImageLoader.moduleLevelSprite;
        setIcon(new ImageIcon(scale[this.i]));
    }

    public void increment () {
        if (i < scale.length - 1) {
            i++;
            refreshIcon();
        }
    }

    public int getLevel () {
        return i + 1;
    }

    public boolean isMaxLevel () {
        return i >= scale.length - 1;
    }

    private void refreshIcon() {
        setIcon(new ImageIcon(scale[i]));
    }
}
