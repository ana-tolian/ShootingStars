package program.game.shootingStars.menu;

import program.game.shootingStars.io.GameGeneralDataIO;
import program.game.shootingStars.io.ImageLoader;
import program.game.shootingStars.io.GamePlayerDataIO;
import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.PlayerShipModuleStats;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.ui.GTextArea;
import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ShopListPanel extends GPanel implements ActionListener {

    private final ArrayList<BuyablePlayerShip> shipsInStock;
    private final ShopPanel shop;

    private GPanel [] shipsPanel;
    private GPanel [] shipTextInfoPanel;

    private GLabel [] shipImageLabel;
    private GTextArea [] characteristicLabel;
    private GTextArea [] descriptionLabel;

    private GButton [] buyButton;


    public ShopListPanel (ShopPanel shop) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        this.shop = shop;
        shipsInStock = GameGeneralDataIO.shipsInStock;

        initializeArrays();
        initializeGraphicElements();

       for (int i = 0; i < shipsInStock.size(); i++)
           add(shipsPanel[i]);
    }

    private void initializeArrays () {
        shipsPanel = new GPanel[shipsInStock.size()];
        shipTextInfoPanel = new GPanel[shipsInStock.size()];
        shipImageLabel = new GLabel[shipsInStock.size()];
        characteristicLabel = new GTextArea[shipsInStock.size()];
        descriptionLabel = new GTextArea[shipsInStock.size()];
        buyButton = new GButton[shipsInStock.size()];
    }

    private void initializeGraphicElements () {
        BuyablePlayerShip stock;
        for (int i = 0; i < shipsInStock.size(); i++) {
            stock = shipsInStock.get(i);

            shipsPanel[i] = new GPanel();
            shipsPanel[i].setLayout(new FlowLayout());

            shipTextInfoPanel[i] = new GPanel();
            shipTextInfoPanel[i].setLayout(new BoxLayout(shipTextInfoPanel[i], BoxLayout.Y_AXIS));
            shipTextInfoPanel[i].setBorder(BorderFactory.createLineBorder(GameConstant.LINE_COLOR, GameConstant.LINE_THICKNESS));

            shipImageLabel[i] = new GLabel();
            shipImageLabel[i].setIconTextGap(20);
            shipImageLabel[i].setIcon(stock.getIcon());
            shipImageLabel[i].setPreferredSize(new Dimension(100, 200));
            shipImageLabel[i].setBorder(BorderFactory.createLineBorder(GameConstant.LINE_COLOR, GameConstant.LINE_THICKNESS));

            characteristicLabel[i] = new GTextArea();
            characteristicLabel[i].setText("Name: " + stock.getName() +
                    "\nTurrets: " + stock.getNumberOfGuns() +
                    "\nHealth: " + stock.getHealth() +
                    "\nSpecial abilities: " + stock.getSpecial());

            descriptionLabel[i] = new GTextArea(2, 22);
            descriptionLabel[i].setText("Description: " + stock.getDescription());


            buyButton[i] = new GButton();

            if (!stock.isBought()) {
                buyButton[i].setText(stock.getCost() + "");
                buyButton[i].setIconTextGap(5);
                buyButton[i].setIcon(new ImageIcon(ImageLoader.coinSprite));

            } else {
                if (!stock.isEquipped())
                    buyButton[i].setText("Bought");
                else {
                    buyButton[i].setText("Bought *");
                    buyButton[i].setEnabled(false);
                }
            }
            buyButton[i].setPreferredSize(new Dimension(110, 200));
            buyButton[i].setActionCommand(i + "");
            buyButton[i].addActionListener(this);

            shipTextInfoPanel[i].add(characteristicLabel[i]);
            shipTextInfoPanel[i].add(descriptionLabel[i]);

            shipsPanel[i].add(shipImageLabel[i]);
            shipsPanel[i].add(shipTextInfoPanel[i]);
            shipsPanel[i].add(buyButton[i]);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        for (int i = 0; i < shipsInStock.size(); i++) {
            if (button.getActionCommand().equals(i + "")) {
                BuyablePlayerShip ship = shipsInStock.get(i);

                if (ship.isBought())
                    equip(i);

                if (GamePlayerDataIO.loadMoney() >= ship.getCost() && !ship.isBought()) {
                    buy(ship, i);
                }
            }
        }
    }

    private void equip (int j) {
        for (int i = 0; i < shipsInStock.size(); i++) {
            BuyablePlayerShip ship = shipsInStock.get(i);
            if (ship.isEquipped()) {
                ship.unequip();
                buyButton[i].setText("Bought");
                buyButton[i].setEnabled(true);
            }

            if (i == j) {
                ship.equip();
                buyButton[i].setText("Bought *");
                buyButton[i].setEnabled(false);
                GamePlayerDataIO.saveEquipped(ship);
            }
        }
    }

    private void buy (BuyablePlayerShip ship, int i) {
        ship.buy();
        GamePlayerDataIO.changeBalanceAndSave(ship.getCost());
        buyButton[i].setIcon(null);
        buyButton[i].setText("Bought");
        shop.refreshBalanceLabel();
        GameGeneralDataIO.savePlayerGeneralData();
        GamePlayerDataIO.ownedShips.add(new PlayerShipModuleStats(ship.getName()));
    }

}
