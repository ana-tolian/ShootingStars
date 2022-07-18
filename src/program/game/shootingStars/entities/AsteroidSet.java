package program.game.shootingStars.entities;

import program.game.shootingStars.variables.changable.Changable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AsteroidSet {

    private BufferedImage asteroidImage;
    private ArrayList<Asteroid> asteroids;

    public AsteroidSet (BufferedImage bi) {
        this.asteroidImage = bi;
        this.asteroids = new ArrayList<>(Changable.asteroidCount);
        generateEntity();
    }

    public void draw (Graphics g) {
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).draw(g);
        }
    }

    public int move (PlayerShip p) {
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid a = asteroids.get(i);
            a.move();

            if (!a.isEntityOnScreen())
                asteroids.remove(i);

            if (a.isIntersects(p)) {
                return 1;
            }
        }
        generateEntity();
        return 0;
    }

    public void generateEntity () {
        for (int i = 0; i < Changable.asteroidCount - asteroids.size(); i++) {
            asteroids.add(new Asteroid (10, 50, asteroidImage));
        }
    }
}
