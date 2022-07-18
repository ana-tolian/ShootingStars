package program.game.shootingStars.entities;

import java.awt.image.BufferedImage;

public class Asteroid extends StaticEntity {

    public Asteroid (int speed, int health, BufferedImage img) {
        super(speed, health, img);
    }

    public Asteroid(int speed, int x, int y, int health, BufferedImage img) {
        super(speed, x, y, health, img);
    }

    public void move () {
        y += 3;
    }

    // This method check whether the every point
    // of each rectangle is inside the another rect
    public boolean isIntersects (StaticEntity p) {
        return isPointInsideBox(this.x, this.y, p) ||                                         // x1, y1
                isPointInsideBox(this.x + this.width, this.y, p) ||                         // x2, y1
                isPointInsideBox(this.x, this.y + this.height, p) ||                        // x1, y2
                isPointInsideBox(this.x + this.width, this.y + this.height, p) ||         // x2, y2
                isPointInsideBox(p.x, p.y, this) ||                                         // px1, py1
                isPointInsideBox(p.x + p.width, p.y, this) ||                             // px2, py1
                isPointInsideBox(p.x, p.y + p.height, this) ||                            // px1, py2
                isPointInsideBox(p.x + p.width, p.y + p.height, this);                  // px2, py2
    }

    private boolean isPointInsideBox (int x, int y, StaticEntity e) {
        return (e.x <= x &&
                e.y <= y &&
                x <= e.x + e.width &&
                y < e.y + e.height);
    }
}
