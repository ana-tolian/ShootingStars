package program.game.shootingStars.entities;

import java.awt.image.BufferedImage;

public class EnemyShip extends StaticEntity {

    private BufferedImage bulletImage;

    public EnemyShip(int speed, int x, int y, int health, BufferedImage img) {
        super(speed, x, y, health, img);
        this.bulletImage = null;
    }

    public EnemyShip(int speed, int health, BufferedImage img, BufferedImage bulletImage) {
        super(speed, health, img);
        this.bulletImage = bulletImage;
    }


}
