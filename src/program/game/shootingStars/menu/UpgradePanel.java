package program.game.shootingStars.menu;

import program.game.shootingStars.GameGeneralDataIO;
import program.game.shootingStars.GamePlayerDataIO;
import program.game.shootingStars.ImageLoader;
import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.PlayerShipModuleStats;
import program.game.shootingStars.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UpgradePanel extends GPanel implements ActionListener {

    private PlayerShipModuleStats stats;
    private BuyablePlayerShip equippedShip;
    private GameGeneralDataIO generalDataIO;
    private GamePlayerDataIO playerDataIO;
    private ShopPanel shop;

    private GPanel shipPanel;
    private GPanel shipStatsPanel;

    private ModuleUpgrader healthUpgrader;
    private ModuleUpgrader weaponUpgrader;

    private GLabel shipImageLabel;


    public UpgradePanel (ShopPanel shop, ImageLoader imageLoader,
                         GamePlayerDataIO playerDataIO, GameGeneralDataIO generalDataIO) {

        setLayout(new FlowLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.shop = shop;
        this.playerDataIO = playerDataIO;
        this.generalDataIO = generalDataIO;
        this.equippedShip = getEquippedShip();
        this.stats = playerDataIO.getPlayerStats();

        shipPanel = new GPanel();

        shipImageLabel = new GLabel();
        shipImageLabel.setIconTextGap(20);
        shipImageLabel.setIcon(equippedShip.getIcon(5, 5));
        shipImageLabel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        shipStatsPanel = new GPanel();
        shipStatsPanel.setLayout(new BoxLayout(shipStatsPanel, BoxLayout.Y_AXIS));

        healthUpgrader = new ModuleUpgrader(equippedShip, stats.getHullLevel(), "Hull: ");
        healthUpgrader.addActionListener(this);
        healthUpgrader.setActionCommand("health_upgrade");

        weaponUpgrader = new ModuleUpgrader(equippedShip, stats.getWeaponLevel(), "Gun: ");
        weaponUpgrader.addActionListener(this);
        weaponUpgrader.setActionCommand("weapon_upgrade");

        shipStatsPanel.add(healthUpgrader);
        shipStatsPanel.add(weaponUpgrader);

        shipPanel.add(shipImageLabel);
        shipPanel.add(shipStatsPanel);
        add(shipPanel);
    }

    private BuyablePlayerShip getEquippedShip () {
        for (BuyablePlayerShip p : generalDataIO.getShipsInStock())
            if (p.isEquipped())
                return p;
            return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getActionCommand().equals("health_upgrade")) {
            int cost = equippedShip.getCostOfUpgrade(stats.getHullLevel());

            if (playerDataIO.loadMoney() >= cost && !healthUpgrader.isMaxLevel()) {
                healthUpgrader.upgrade();
                stats.setHullLevel(stats.getHullLevel() + 1);
                buy(cost);
            }

        } else if (button.getActionCommand().equals("weapon_upgrade")) {
            int cost = equippedShip.getCostOfUpgrade(stats.getWeaponLevel());

            if (playerDataIO.loadMoney() >= cost && !weaponUpgrader.isMaxLevel()) {
                weaponUpgrader.upgrade();
                stats.setWeaponLevel(stats.getWeaponLevel() + 1);
                buy(cost);
            }
        }
    }

    private void buy (int cost) {
        playerDataIO.changeBalanceAndSave(cost);
        shop.refreshBalanceLabel();
        playerDataIO.savePlayerStats(stats);
    }
}
