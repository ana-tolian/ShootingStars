package program.game.shootingStars.menu;

import program.game.shootingStars.GameInfoLoader;
import program.game.shootingStars.ImageLoader;
import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.ui.GTextArea;
import program.game.shootingStars.variables.constant.GameConstant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ShopListPanel extends GPanel {

    private ArrayList<BuyablePlayerShip> shipsInStock;
    private ImageLoader imageLoader;

    private GPanel upDownButtonPanel;
    private GPanel shipsPanel [];
    private GPanel shipTextInfoPanel[];

    private GLabel shipImageLabel [];
    private GTextArea characteristicLabel [];
    private GTextArea descriptionLabel [];

    private GButton buyButton [];
    private GButton upButton;
    private GButton downButton;


    public ShopListPanel () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
//        setPreferredSize(new Dimension(765, 450));

        imageLoader = new ImageLoader();
        shipsInStock = new GameInfoLoader().getShipsInStock();;

        initializeArrays();
        initializeGraphicElements();

        upDownButtonPanel = new GPanel();
        upDownButtonPanel.setLayout(new BoxLayout(upDownButtonPanel, BoxLayout.Y_AXIS));

        upButton = new GButton();
        upButton.setPreferredSize(new Dimension(48, 48));
        upButton.setIcon(imageLoader.getUpArrowIcon());
        downButton = new GButton();
        downButton.setPreferredSize(new Dimension(48, 48));
        downButton.setIcon(imageLoader.getDownArrowIcon());

//        upDownButtonPanel.add(upButton);
//        upDownButtonPanel.add(downButton);


        add(shipsPanel[0]);
//        add(upDownButtonPanel);
        add(shipsPanel[1]);
        add(shipsPanel[2]);
        add(shipsPanel[3]);
        add(shipsPanel[4]);
        add(shipsPanel[5]);
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

            buyButton[i] = new GButton(stock.getCost() + "");
            buyButton[i].setIconTextGap(5);
            buyButton[i].setIcon(imageLoader.getCoinIcon());
            buyButton[i].setPreferredSize(new Dimension(110, 200));

            shipTextInfoPanel[i].add(characteristicLabel[i]);
            shipTextInfoPanel[i].add(descriptionLabel[i]);

            shipsPanel[i].add(shipImageLabel[i]);
            shipsPanel[i].add(shipTextInfoPanel[i]);
            shipsPanel[i].add(buyButton[i]);

        }
    }

}
