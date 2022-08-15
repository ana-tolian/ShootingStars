package program.game.shootingStars.entities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BuyablePlayerShip extends PlayerShip implements Buyable, Equippable {

    private final String description;
    private final String imgLink;
    private final int cost;

    private boolean isBought;
    private boolean isEquipped = false;


    public BuyablePlayerShip(int speed, int x, int y, int health, BufferedImage img, BufferedImage[] images,
                             int numberOfGuns, int gunPosX [], int gunPosY [],
                             String description, PlayerShipModuleStats stats,
                             int cost, boolean isBought, String link, int damage) {
        super(speed, x, y, health, img, images, numberOfGuns, gunPosX, gunPosY, stats, damage);
        this.description = description;
        this.cost = cost;
        this.isBought = isBought;
        this.imgLink = link;
    }


    @Override
    public String getName() {
        return stats.getName() ;
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
    public void buy () {
        isBought = true;
    }

    @Override
    public String getSpecial() {
        return stats.getSpecial();
    }

    @Override
    public ImageIcon getIcon (int w, int h) {
        return new ImageIcon(
                new ImageIcon(img).getImage().getScaledInstance(width*3, height*3, Image.SCALE_DEFAULT));
    }

    public ImageIcon getIcon () {
        return getIcon(3, 3);
    }

    public int getCostOfUpgrade (int level) {
        return (int) (30 * level * level + cost * 0.1);
    }

    @Override
    public boolean isEquipped() {
        return isEquipped;
    }

    @Override
    public void equip() {
        isEquipped = true;
    }

    @Override
    public void unequip() {
        isEquipped = false;
    }

    @Override
    public String toString () {
        return stats.getSpecial() + "#" + super.toString() + stats.getSpecial() + "#" + imgLink + "#" + description + "#" + cost + "#" + isBought +"\n";
    }
}
