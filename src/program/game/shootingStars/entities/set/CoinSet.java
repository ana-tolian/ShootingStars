package program.game.shootingStars.entities.set;

import program.game.shootingStars.entities.Bullet;
import program.game.shootingStars.entities.Coin;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.StaticEntity;
import program.game.shootingStars.variables.changable.Changable;
import program.game.shootingStars.variables.constant.GameConstant;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CoinSet implements Set {

    private BufferedImage coinImage;
    private ArrayList<Coin> coins;

    public CoinSet (BufferedImage bi) {
        this.coinImage = bi;
        this.coins = new ArrayList<>(Changable.asteroidCount);
    }

    public void draw (Graphics g) {
        for (int i = 0; i < coins.size(); i++) {
            coins.get(i).draw(g);
        }
    }

    public void move (PlayerShip p) {
        for (int i = 0; i < coins.size(); i++) {
            Coin a = coins.get(i);
            a.move();

            if (!a.isEntityOnScreen())
                coins.remove(i);

            if (a.isIntersects(p)) {
                coins.remove(i);
                p.setCollectedCoin(true);
            }
        }
    }

    public void generateEntity () {
        for (int i = 0; i < Changable.coinAmount - coins.size(); i++) {
            coins.add(new Coin(GameConstant.ANIMATION_SPEED, coinImage));
        }
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public int getSize () {
        return coins.size();
    }

    public StaticEntity getEntity (int i) {
        return coins.get(i);
    }

    public void removeEntity (int i) {
        coins.remove(i);
    }
}
