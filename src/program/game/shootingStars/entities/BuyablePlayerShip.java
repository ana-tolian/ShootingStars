package program.game.shootingStars.entities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BuyablePlayerShip extends PlayerShip implements Buyable {

    private final String name;
    private final String description;
    private final String special;
    private final int cost;
    private boolean isBought;


    public BuyablePlayerShip(int speed, int x, int y, int health, BufferedImage img, BufferedImage[] images,
                             int numberOfGuns, int gunPosX [], int gunPosY [],
                             String name, String description, int cost, boolean isBought) {
        super(speed, x, y, health, img, images, numberOfGuns, gunPosX, gunPosY);
        this.name = name;
        this.description = description;
        this.special = "none";
        this.cost = cost;
        this.isBought = isBought;
    }

    public BuyablePlayerShip(int speed, int x, int y, int health, BufferedImage img, BufferedImage[] images,
                             int numberOfGuns, int gunPosX [], int gunPosY [],
                             String name, String description, String special, int cost, boolean isBought) {
        super(speed, x, y, health, img, images, numberOfGuns, gunPosX, gunPosY);
        this.name = name;
        this.description = description;
        this.special = special;
        this.cost = cost;
        this.isBought = isBought;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean isBought() {
        return isBought;
    }

    @Override
    public String getSpecial() {
        return special;
    }

    @Override
    public ImageIcon getIcon () {
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(img).getImage().getScaledInstance(width*3, height*3, Image.SCALE_DEFAULT));
        return imageIcon;
    }
}
