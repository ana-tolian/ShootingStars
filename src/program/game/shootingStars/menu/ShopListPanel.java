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

    private GPanel upDownButtonPanel;
    private GPanel shipsPanel;
    private GPanel shipInfoLabelPanel;

    private GButton upButton;
    private GButton downButton;
    private GButton buyButton;

    private GLabel shipImageLabel;
    private GTextArea characteristicLabel;
    private GTextArea descriptionLabel;


    public ShopListPanel () {
        setPreferredSize(new Dimension(765, 400));
        ImageLoader imageLoader = new ImageLoader();

        shipsInStock = new GameInfoLoader().getShipsInStock();;
        BuyablePlayerShip stock = shipsInStock.get(0);

        shipsPanel = new GPanel();
        shipsPanel.setLayout(new FlowLayout());

        shipInfoLabelPanel = new GPanel();
        shipInfoLabelPanel.setLayout(new BoxLayout(shipInfoLabelPanel, BoxLayout.Y_AXIS));
        shipInfoLabelPanel.setBorder(BorderFactory.createLineBorder(GameConstant.LINE_COLOR, GameConstant.LINE_THICKNESS));

        shipImageLabel = new GLabel();
        shipImageLabel.setIconTextGap(20);
        shipImageLabel.setIcon(stock.getIcon());
        shipImageLabel.setPreferredSize(new Dimension(100, 200));
        shipImageLabel.setBorder(BorderFactory.createLineBorder(GameConstant.LINE_COLOR, GameConstant.LINE_THICKNESS));

        characteristicLabel = new GTextArea();
        characteristicLabel.setText("Name: " + stock.getName() +
                                    "\nTurrets: " + stock.getNumberOfGuns() +
                                    "\nHealth: " + stock.getHealth() +
                                    "\nSpecial abilities: " + stock.getSpecial());

        descriptionLabel = new GTextArea();
        descriptionLabel.setText("Description: " + stock.getDescription());
        descriptionLabel.setColumns(22);

        buyButton = new GButton(stock.getCost() + "");
        buyButton.setIconTextGap(5);
        buyButton.setIcon(imageLoader.getCoinIcon());
        buyButton.setPreferredSize(new Dimension(110, 200));

        shipInfoLabelPanel.add(characteristicLabel);
        shipInfoLabelPanel.add(descriptionLabel);

        shipsPanel.add(shipImageLabel);
        shipsPanel.add(shipInfoLabelPanel);
        shipsPanel.add(buyButton);



        upDownButtonPanel = new GPanel();
        upDownButtonPanel.setLayout(new BoxLayout(upDownButtonPanel, BoxLayout.Y_AXIS));

        upButton = new GButton();
        upButton.setPreferredSize(new Dimension(48, 48));
        upButton.setIcon(imageLoader.getUpArrowIcon());
        downButton = new GButton();
        downButton.setPreferredSize(new Dimension(48, 48));
        downButton.setIcon(imageLoader.getDownArrowIcon());

        upDownButtonPanel.add(upButton);
        upDownButtonPanel.add(downButton);


        add(shipsPanel);
        add(upDownButtonPanel);
    }

}
