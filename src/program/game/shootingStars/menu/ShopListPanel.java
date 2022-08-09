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
    private GPanel shipInfoPanel;
    private GPanel shipInfoLabelPanel;

    private GButton upButton;
    private GButton downButton;
    private GButton buyButton;

    private GLabel shipImageLabel;
    private GTextArea characteristicLabel;
    private GTextArea descriptionLabel;


    public ShopListPanel () {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(765, 400));

        shipsInStock = new GameInfoLoader().getShipsInStock();;
        BuyablePlayerShip stock = shipsInStock.get(0);

        shipInfoPanel = new GPanel();
        shipInfoPanel.setLayout(new FlowLayout());
        shipInfoPanel.setBorder(BorderFactory.createLineBorder(GameConstant.LINE_COLOR, GameConstant.LINE_THICKNESS));

        shipInfoLabelPanel = new GPanel();
        shipInfoLabelPanel.setLayout(new BoxLayout(shipInfoLabelPanel, BoxLayout.Y_AXIS));

        shipImageLabel = new GLabel();
        shipImageLabel.setIconTextGap(5);
        shipImageLabel.setIcon(stock.getIcon());

        characteristicLabel = new GTextArea();
        characteristicLabel.setText("Name: " + stock.getName() +
                                    "\nTurrets: " + stock.getNumberOfGuns() +
                                    "\nHealth: " + stock.getHealth() +
                                    "\nSpecial abilities: " + stock.getSpecial());

        descriptionLabel = new GTextArea();
        descriptionLabel.setText("Description: " + stock.getDescription());
        descriptionLabel.setColumns(30);

        buyButton = new GButton(stock.getCost() + "");
        buyButton.setIconTextGap(5);
        buyButton.setIcon(new ImageLoader().getCoinIcon());

        shipInfoLabelPanel.add(characteristicLabel);
        shipInfoLabelPanel.add(descriptionLabel);
        shipInfoLabelPanel.add(buyButton);

        shipInfoPanel.add(shipImageLabel);
        shipInfoPanel.add(shipInfoLabelPanel);



        upDownButtonPanel = new GPanel();
        upDownButtonPanel.setLayout(new BoxLayout(upDownButtonPanel, BoxLayout.Y_AXIS));

        upButton = new GButton("up");
        upButton.setPreferredSize(new Dimension(30, 20));
        downButton = new GButton("down");
        downButton.setPreferredSize(new Dimension(30, 20));

        upDownButtonPanel.add(upButton);
        upDownButtonPanel.add(downButton);


        add(shipInfoPanel);
        add(upDownButtonPanel);
    }

}
