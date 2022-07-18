package program.game.shootingStars.entities.set;

import program.game.shootingStars.ImageLoader;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.variables.changable.Changable;
import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class EntitySet {

    private AsteroidSet asteroids;
    private CoinSet coins;
    private EnemyShipSet enemies;
    private BulletSet bullets;
    private PlayerShip player;

    private BufferedImage asteroidImage;
    private BufferedImage enemyImage;
    private BufferedImage bulletImage;
    private BufferedImage coinImage;
    private BufferedImage fireSprites [];
    private BufferedImage rocketImage;

    private int px;
    private int py;


    public EntitySet (JPanel panel) {
        loadImages(panel);

        this.coins = new CoinSet(coinImage);
        this.asteroids = new AsteroidSet(asteroidImage);
        this.enemies = new EnemyShipSet(enemyImage, bulletImage);
        this.bullets = new BulletSet(bulletImage);

        px = GameConstant.F_WIDTH / 2 - 50;
        py = GameConstant.F_HEIGHT / 2 - 50;
        this.player = new PlayerShip (10, px, py, 100, rocketImage, fireSprites);

        generateEntity();
    }

    private void loadImages (JPanel panel) {
        this.rocketImage = ImageLoader.rocketSprite;
        this.fireSprites = ImageLoader.fireAnimationSprites;
        this.bulletImage = ImageLoader.bulletSprite;
        this.asteroidImage = ImageLoader.asteroidSprite;
        this.enemyImage = ImageLoader.enemySprite;
        this.bulletImage = ImageLoader.bulletSprite;
        this.coinImage = ImageLoader.coinSprite;
        panel.checkImage(asteroidImage, panel);
        panel.checkImage(enemyImage, panel);
        panel.checkImage(bulletImage, panel);
        panel.checkImage(coinImage, panel);
        panel.checkImage(rocketImage, panel);
    }

    public void draw (Graphics g) {
        asteroids.draw(g);
        coins.draw(g);
        enemies.draw(g);
        bullets.draw(g);
        player.draw(g);
        if (player.isMove())
            player.drawFire(g);

    }

    public void move (int x, int y) {
        player.move(x, y);
        move();
    }

    private void move () {
        asteroids.move(player);
        coins.move(player);
        enemies.move(player);
        bullets.move(player);

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
        if (j >= set.getSize()) {
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

    public PlayerShip getPlayer() {
        return player;
    }

    public void playerShoot () {
        bullets.generateEntity(player.getX() + (player.getWidth() >> 1) - 1, player.getY(), true);
    }
}
