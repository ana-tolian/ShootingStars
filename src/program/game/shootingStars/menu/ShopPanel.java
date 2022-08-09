package program.game.shootingStars.menu;

import program.game.shootingStars.ImageLoader;
import program.game.shootingStars.Init;
import program.game.shootingStars.SaveResults;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.variables.constant.GameConstant;
import program.game.shootingStars.variables.constant.PathConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends GPanel {

    private GPanel balancePanel;
    private GPanel goodsPanel;
    private GPanel backButtonPanel;

    private GLabel balanceLabel;

    private GButton upgradeShipMenuButton;
    private GButton shipShopMenuButton;
    private GButton backButton;


    public ShopPanel () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageLoader imageLoader = new ImageLoader();

        balancePanel = new GPanel();
//        balancePanel.setMaximumSize(new Dimension(765, 60));
//        balancePanel.setPreferredSize(new Dimension(765, 60));

        balanceLabel = new GLabel ();
        balanceLabel.setIcon(imageLoader.getCoinIcon());
        balanceLabel.setIconTextGap(5);
//        balanceLabel.setPreferredSize(new Dimension(765, 30));
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
        balancePanel.add(balanceLabel);

        goodsPanel = new GPanel();
//        goodsPanel.setPreferredSize(new Dimension(765, 400));

        shipShopMenuButton = new GButton ("");
        shipShopMenuButton.setPreferredSize(new Dimension(340, 340));
        shipShopMenuButton.setIcon(imageLoader.getShopIcon());
        shipShopMenuButton.setActionCommand("shopping");
        shipShopMenuButton.addActionListener(new ActionL());

        GLabel nullLabel = new GLabel();
        nullLabel.setPreferredSize(new Dimension(30, 30));

        upgradeShipMenuButton = new GButton ("");
        upgradeShipMenuButton.setPreferredSize(new Dimension(340, 340));
        upgradeShipMenuButton.setIcon(imageLoader.getUpgradeIcon());
        upgradeShipMenuButton.setActionCommand("upgrade");
        upgradeShipMenuButton.addActionListener(new ActionL());

        goodsPanel.add(upgradeShipMenuButton);
        goodsPanel.add(nullLabel);
        goodsPanel.add(shipShopMenuButton);;

        backButtonPanel = new GPanel();
        backButtonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 50, 30));

        backButton = new GButton("Back");
        backButton.addActionListener(new ActionL());
        backButtonPanel.add(backButton);

        add(balancePanel, "North");
        add(goodsPanel, "Center");
        add(backButtonPanel, "South");
    }

    public void refreshBalanceLabel () {
        balanceLabel.setText(new SaveResults().loadMoney() + "$");
    }


    private class ActionL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button.getActionCommand().equals("Back")) {
                Init.setMainMenuPanel(Init.shopPanel);

            } else if (button.getActionCommand().equals("shopping")) {
                goodsPanel.removeAll();
                goodsPanel.add(new JScrollPane(new ShopListPanel()));
                revalidate();
//                Init.shopListPanel);

            } else if (button.getActionCommand().equals("upgrade")) {
//                IInit.upgradePanel);
            }
        }
    }
}
