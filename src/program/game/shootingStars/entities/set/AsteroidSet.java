package program.game.shootingStars.entities.set;

import program.game.shootingStars.entities.Asteroid;
import program.game.shootingStars.entities.Bullet;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.StaticEntity;
import program.game.shootingStars.variables.changable.Changable;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AsteroidSet implements Set {

    private BufferedImage asteroidImage;
    private ArrayList<Asteroid> asteroids;

    public AsteroidSet (BufferedImage bi) {
        this.asteroidImage = bi;
        this.asteroids = new ArrayList<>(Changable.asteroidCount);
    }

    public void draw (Graphics g) {
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).draw(g);
        }
    }

    public void move (PlayerShip p) {
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid a = asteroids.get(i);
            a.move();

            if (!a.isEntityOnScreen())
                asteroids.remove(i);

            if (a.isIntersects(p)) {
                p.setCrushed(true);
                return;
            }
        }
    }

    public void generateEntity () {
        for (int i = 0; i < Changable.asteroidCount - asteroids.size(); i++) {
            asteroids.add(new Asteroid (10, 30, asteroidImage));
        }
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public int getSize () {
        return asteroids.size();
    }

    public StaticEntity getEntity (int i) {
        return asteroids.get(i);
    }

    public void removeEntity (int i) {
        asteroids.remove(i);
    }
}
