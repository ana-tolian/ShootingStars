package program.game.shootingStars.entities;

import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.image.BufferedImage;

public class Bullet extends StaticEntity {

    private boolean directionUp;

    public Bullet(int speed, int x, int y, boolean dir, BufferedImage img) {
        super(speed, x, y, img);
        this.directionUp = dir;
    }

    @Override
    public void move () {
        if (directionUp)
            y -= 4;
        else
            y += 4;
    }

    @Override
    public boolean isEntityOnScreen () {
        if (y < GameConstant.F_HEIGHT + 50 && y > -50)
            return true;
        else
            return false;
    }

    public boolean isDirectionUp() {
        return directionUp;
    }
}
