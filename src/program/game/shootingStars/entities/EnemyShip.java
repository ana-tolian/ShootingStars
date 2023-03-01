package program.game.shootingStars.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyShip extends StaticEntity {

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
    }

    public EnemyShip(int speed, int x, int y, int health, BufferedImage img,
                     int numberOfGuns, int [] gunPosX , int [] gunPosY, int damage) {
        super(speed, x, y, health, img);
        this.numberOfGuns = numberOfGuns;
        this.gunPosX = gunPosX;
        this.gunPosY = gunPosY;
        this.damage = damage;
    }

    public Point[] shoot () {
        Point [] shots = new Point[numberOfGuns];
        for (int i = 0; i < numberOfGuns; i++)
            shots[i] = new Point(gunPosX[i] + this.getX(), gunPosY[i] + this.getY());
        return shots;
    }

    public int getDamage () {
        return damage + 10;
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
