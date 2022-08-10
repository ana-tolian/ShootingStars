package program.game.shootingStars.menu;

import program.game.shootingStars.GameGeneralDataIO;
import program.game.shootingStars.GamePlayerDataIO;
import program.game.shootingStars.ImageLoader;
import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.PlayerStats;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.ui.GScaleLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class UpgradePanel extends GPanel {

    private PlayerStats stats;
    private BuyablePlayerShip equippedShip;
    private GameGeneralDataIO generalDataIO;
    private ImageLoader imageLoader;
    private GamePlayerDataIO playerDataIO;
    private ShopPanel shop;

    private GPanel shipPanel;
    private GPanel shipStatsPanel;

    private GLabel shipImageLabel;
    private GScaleLabel healthLevelImageLabel;
    private GScaleLabel weaponLevelImageLabel;


    public UpgradePanel (ShopPanel shop, ImageLoader imageLoader,
                         GamePlayerDataIO playerDataIO, GameGeneralDataIO generalDataIO) {

        setLayout(new FlowLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.shop = shop;
        this.imageLoader = imageLoader;
        this.playerDataIO = playerDataIO;
        this.generalDataIO = generalDataIO;
        this.equippedShip = getEquippedShip();
        this.stats = playerDataIO.getPlayerStats();

        shipPanel = new GPanel();
        shipStatsPanel = new GPanel();

        shipImageLabel = new GLabel();

        healthLevelImageLabel = new GScaleLabel(stats.getHullLevel());
        weaponLevelImageLabel = new GScaleLabel(stats.getWeaponLevel());
    }

    private void refreshIcon (GLabel label) {
//        label
    }

    private BuyablePlayerShip getEquippedShip () {
        for (BuyablePlayerShip p : generalDataIO.getShipsInStock())
            if (p.isEquipped())
                return p;
            return null;
    }
}
