package program.game.shootingStars.menu;

import program.game.shootingStars.io.GameGeneralDataIO;
import program.game.shootingStars.io.GamePlayerDataIO;
import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.PlayerShipModuleStats;
import program.game.shootingStars.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UpgradePanel extends GPanel implements ActionListener {

    private final PlayerShipModuleStats stats;
    private final BuyablePlayerShip equippedShip;
    private final ShopPanel shop;

    private final GPanel shipPanel;
    private final GPanel shipStatsPanel;

    private final ModuleUpgrader healthUpgrader;
    private final ModuleUpgrader weaponUpgrader;

    private final GLabel shipImageLabel;


    public UpgradePanel (ShopPanel shop) {

        setLayout(new FlowLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.shop = shop;
        this.equippedShip = getEquippedShip();
        this.stats = GamePlayerDataIO.loadPlayerStats();

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
        for (BuyablePlayerShip p : GameGeneralDataIO.shipsInStock)
            if (p.isEquipped())
                return p;
            return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getActionCommand().equals("health_upgrade")) {
            int cost = equippedShip.getCostOfUpgrade(stats.getHullLevel());

            if (GamePlayerDataIO.loadMoney() >= cost && !healthUpgrader.isMaxLevel()) {
                healthUpgrader.upgrade();
                stats.setHullLevel(stats.getHullLevel() + 1);
                buy(cost);
            }

        } else if (button.getActionCommand().equals("weapon_upgrade")) {
            int cost = equippedShip.getCostOfUpgrade(stats.getWeaponLevel());

            if (GamePlayerDataIO.loadMoney() >= cost && !weaponUpgrader.isMaxLevel()) {
                weaponUpgrader.upgrade();
                stats.setWeaponLevel(stats.getWeaponLevel() + 1);
                buy(cost);
            }
        }
    }

    private void buy (int cost) {
        GamePlayerDataIO.changeBalanceAndSave(cost);
        shop.refreshBalanceLabel();
        GamePlayerDataIO.savePlayerStats();
    }
}
