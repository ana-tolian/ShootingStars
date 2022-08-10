package program.game.shootingStars.entities;


import program.game.shootingStars.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Coin extends StaticEntity {

    public Coin(int speed, BufferedImage img) {
        super(speed, img);
        explosionImg = ImageLoader.coinCollectedImage;
    }


}
