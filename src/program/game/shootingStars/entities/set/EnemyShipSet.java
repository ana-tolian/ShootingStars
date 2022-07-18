package program.game.shootingStars.entities.set;

import program.game.shootingStars.entities.EnemyShip;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.variables.changable.Changable;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyShipSet implements Set {

    private BufferedImage enemyImage;
    private BufferedImage bulletImage;
    private ArrayList<EnemyShip> enemies;

    public EnemyShipSet (BufferedImage ship, BufferedImage bullet) {
        this.enemyImage = ship;
        this.bulletImage = bullet;
        this.enemies = new ArrayList<>(Changable.asteroidCount);
        generateEntity();
    }

    public void draw (Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
    }

    public void move (PlayerShip p) {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip a = enemies.get(i);
            a.move();

            if (!a.isEntityOnScreen())
                enemies.remove(i);

            if (a.isIntersects(p)) {
                enemies.remove(i);
                p.setCrushed(true);
                return;
            }
        }
        generateEntity();
    }

    public void generateEntity () {
        for (int i = 0; i < Changable.enemyCount - enemies.size(); i++) {
            enemies.add(new EnemyShip(10, 100, enemyImage, bulletImage));
        }
    }
}