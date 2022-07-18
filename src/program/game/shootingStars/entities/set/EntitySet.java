package program.game.shootingStars.entities.set;

import program.game.shootingStars.ImageLoader;
import program.game.shootingStars.entities.Asteroid;
import program.game.shootingStars.entities.Bullet;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.StaticEntity;
import program.game.shootingStars.variables.changable.Changable;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class EntitySet {

    private AsteroidSet asteroids;
    private CoinSet coins;
    private EnemyShipSet enemies;
    private BulletSet bullets;

    private BufferedImage asteroidImage;
    private BufferedImage enemyImage;
    private BufferedImage bulletImage;
    private BufferedImage coinImage;


    public EntitySet (JPanel panel) {
        this.asteroidImage = ImageLoader.asteroidSprite;
        this.enemyImage = ImageLoader.enemySprite;
        this.bulletImage = ImageLoader.bulletSprite;
        this.coinImage = ImageLoader.coinSprite;
        panel.checkImage(asteroidImage, panel);
        panel.checkImage(enemyImage, panel);
        panel.checkImage(bulletImage, panel);
        panel.checkImage(coinImage, panel);

        this.coins = new CoinSet(coinImage);
        this.asteroids = new AsteroidSet(asteroidImage);
        this.enemies = new EnemyShipSet(enemyImage, bulletImage);
        this.bullets = new BulletSet(bulletImage);

        generateEntity();
    }

    public void draw (Graphics g) {
        asteroids.draw(g);
        coins.draw(g);
        enemies.draw(g);
        bullets.draw(g);

    }

    public void move (PlayerShip p) {
        asteroids.move(p);
        coins.move(p);
        enemies.move(p);
        bullets.move(p);

        int size = getSize();
        int sz = bullets.getSize();

        for (int i = 0; i < sz; i++)
            for (int j = 0; j < size; j++) {
                checkIntersection(asteroids, i, j);
                checkIntersection(enemies, i, j);
            }

        generateEntity();
    }

    private void checkIntersection (Set set, int i, int j) {
        if (i >= set.getSize()) {
            return;
        } else if (bullets.getEntity(i).isIntersects(set.getEntity(j))) {
            set.removeEntity(j);
        }
    }

    public void generateEntity () {
        asteroids.generateEntity();
        coins.generateEntity();
        enemies.generateEntity();
    }

    public int getSize () {
        return Math.max(Changable.coinAmount, Math.max(Changable.enemyCount, Changable.asteroidCount));
    }

}
