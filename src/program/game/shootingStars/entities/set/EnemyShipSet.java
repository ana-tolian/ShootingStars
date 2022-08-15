package program.game.shootingStars.entities.set;

import program.game.shootingStars.entities.EnemyShip;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.StaticEntity;
import program.game.shootingStars.variables.changable.Changable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyShipSet implements Set {

    private final BufferedImage enemyImage;
    private final BufferedImage bulletImage;
    private final ArrayList<EnemyShip> enemies;

    public EnemyShipSet (BufferedImage ship, BufferedImage bullet) {
        this.enemyImage = ship;
        this.bulletImage = bullet;
        this.enemies = new ArrayList<>(Changable.asteroidCount);
    }

    public void draw (Graphics g) {
        for (EnemyShip enemy : enemies) {
            enemy.draw(g);
        }
    }

    public void move (PlayerShip p) {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip a = enemies.get(i);
            a.move();

            if (!a.isEntityOnScreen())
                enemies.remove(i);

            if (a.isIntersects(p)) {
                a.setDestroyed(true);
                p.setDestroyed(true);
                return;
            }
        }
    }

    public void generateEntity () {
        for (int i = 0; i < Changable.enemyCount - enemies.size(); i++) {
            enemies.add(new EnemyShip(10, 100, enemyImage, bulletImage)); //TODO
        }
    }

    public ArrayList<EnemyShip> getEnemies () {
        return enemies;
    }

    public int getSize () {
        return enemies.size();
    }

    public StaticEntity getEntity (int i) {
        return enemies.get(i);
    }

    public void removeEntity (int i) {
        enemies.remove(i);
    }
}