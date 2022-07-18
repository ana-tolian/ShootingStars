package program.game.shootingStars.entities.set;

import program.game.shootingStars.entities.Bullet;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.StaticEntity;
import program.game.shootingStars.variables.changable.Changable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class BulletSet implements Set {

    private BufferedImage bulletImage;
    private ArrayList<Bullet> bullets;

    public BulletSet (BufferedImage bullet) {
        this.bulletImage = bullet;
        this.bullets = new ArrayList<>(Changable.asteroidCount);
    }

    public void draw (Graphics g) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }
    }

    public void move (PlayerShip p) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet a = bullets.get(i);
            a.move();

            if (!a.isEntityOnScreen())
                bullets.remove(i);

            if (a.isIntersects(p) && !a.isDirectionUp()) {
                bullets.remove(i);
                p.setCrushed(true);
                return;
            }
        }
    }

    public void generateEntity (int x, int y, boolean dir) {
        bullets.add(new Bullet(10, x, y, dir, bulletImage));
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public int getSize () {
        return bullets.size();
    }

    public StaticEntity getEntity (int i) {
        return bullets.get(i);
    }

    public void removeEntity (int i) {
        bullets.remove(i);
    }
}
