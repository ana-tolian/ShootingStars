package program.game.shootingStars.entities;

import java.awt.image.BufferedImage;

public class Asteroid extends StaticEntity {


    public Asteroid (int speed, int health, BufferedImage img) {
        super(speed, health, img);
    }

    public Asteroid(int speed, int x, int y, int health, BufferedImage img) {
        super(speed, x, y, health, img);
    }


}
