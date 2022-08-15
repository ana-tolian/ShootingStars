package program.game.shootingStars.entities;

import java.awt.image.BufferedImage;

public class EnemyShip extends StaticEntity {

    protected int accumulatedDamage;

    protected final int damage;
    protected final int numberOfGuns;
    protected final int [] gunPosX;
    protected final int [] gunPosY;


    public EnemyShip(int speed, int health, BufferedImage img,
                     int numberOfGuns, int [] gunPosX , int [] gunPosY, int damage) {
        super(speed, health, img);
        this.numberOfGuns = numberOfGuns;
        this.gunPosX = gunPosX;
        this.gunPosY = gunPosY;
        this.damage = damage;
        this.accumulatedDamage = 0;
    }

    public EnemyShip(int speed, int x, int y, int health, BufferedImage img,
                     int numberOfGuns, int [] gunPosX , int [] gunPosY, int damage) {
        super(speed, x, y, health, img);
        this.numberOfGuns = numberOfGuns;
        this.gunPosX = gunPosX;
        this.gunPosY = gunPosY;
        this.damage = damage;
        this.accumulatedDamage = 0;
    }


    public int getDamage () {
        return damage + 10;
    }

    public void setAccumulatedDamage (int d) {
        accumulatedDamage += d;
    }

    public int getAccumulatedDamage () {
        return accumulatedDamage;
    }

    public int getNumberOfGuns () {
        return numberOfGuns;
    }

    @Override
    public int getHealth () {
        return health + 10;
    }

    @Override
    public EnemyShip clone () {
        return new EnemyShip (speed, health, img, numberOfGuns, gunPosX, gunPosY, damage);
    }

}
