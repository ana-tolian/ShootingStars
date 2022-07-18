package program.game.shootingStars.entities.set;

import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.StaticEntity;
import program.game.shootingStars.variables.changable.Changable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class EntitySet<T extends StaticEntity> implements Set {

    private BufferedImage asteroidImage;
    private ArrayList<T> asteroids;

    public EntitySet (BufferedImage bi) {
        this.asteroidImage = bi;
        this.asteroids = new ArrayList<>(Changable.asteroidCount);
        generateEntity();
    }

    public void draw (Graphics g) {
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).draw(g);
        }
    }

    public void move (PlayerShip p) {
        for (int i = 0; i < asteroids.size(); i++) {
            T a = asteroids.get(i);
            a.move();

            if (!a.isEntityOnScreen())
                asteroids.remove(i);

            if (a.isIntersects(p)) {

            }
        }
        generateEntity();
    }

    public void generateEntity () {
        for (int i = 0; i < Changable.asteroidCount - asteroids.size(); i++) {
//            asteroids.add(new T (10, 50, asteroidImage));
        }
    }
}
