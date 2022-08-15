package program.game.shootingStars.entities;


import program.game.shootingStars.io.ImageLoader;

import java.awt.image.BufferedImage;

public class Coin extends StaticEntity {

    public Coin(int speed, BufferedImage img) {
        super(speed, img);
        explosionImg = ImageLoader.coinCollectedImage;
    }


}
