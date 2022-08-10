package program.game.shootingStars.menu;

import program.game.shootingStars.GameGeneralDataIO;
import program.game.shootingStars.ImageLoader;
import program.game.shootingStars.Init;
import program.game.shootingStars.GamePlayerDataIO;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.ui.GScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends GPanel implements ActionListener {

    private GameGeneralDataIO generalDataIO;
    private ImageLoader imageLoader;
    private GamePlayerDataIO balance;

    private GPanel balancePanel;
    private GPanel goodsPanel;
    private GPanel buttonPanel;
    private GPanel backButtonPanel;

    private GLabel balanceLabel;

    private GButton upgradeShipMenuButton;
    private GButton shipShopMenuButton;
    private GButton backButton;

    private boolean subMenu = false;


    public ShopPanel () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        imageLoader = new ImageLoader();
        balance = new GamePlayerDataIO();
        generalDataIO = new GameGeneralDataIO();

        balancePanel = new GPanel();

        balanceLabel = new GLabel ();
        balanceLabel.setIcon(imageLoader.getCoinIcon());
        balanceLabel.setIconTextGap(5);
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
        balancePanel.add(balanceLabel);

        goodsPanel = new GPanel();
        goodsPanel.setLayout(new BorderLayout());

        shipShopMenuButton = new GButton ("");
        shipShopMenuButton.setPreferredSize(new Dimension(340, 340));
        shipShopMenuButton.setIcon(imageLoader.getShopIcon());
        shipShopMenuButton.setActionCommand("shopping");
        shipShopMenuButton.addActionListener(this);

        GLabel nullLabel = new GLabel();
        nullLabel.setPreferredSize(new Dimension(30, 30));

        upgradeShipMenuButton = new GButton ("");
        upgradeShipMenuButton.setPreferredSize(new Dimension(340, 340));
        upgradeShipMenuButton.setIcon(imageLoader.getUpgradeIcon());
        upgradeShipMenuButton.setActionCommand("upgrade");
        upgradeShipMenuButton.addActionListener(this);

        buttonPanel = new GPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(upgradeShipMenuButton);
        buttonPanel.add(nullLabel);
        buttonPanel.add(shipShopMenuButton);
        goodsPanel.add(buttonPanel, "Center");

        backButtonPanel = new GPanel();
        backButtonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 50, 30));

        backButton = new GButton("Back");
        backButton.addActionListener(this);
        backButtonPanel.add(backButton);


        add(balancePanel, "North");
        add(goodsPanel, "Center");
        add(backButtonPanel, "South");
    }

    public void refreshBalanceLabel () {
        balanceLabel.setText(new GamePlayerDataIO().loadMoney() + "$");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getActionCommand().equals("Back")) {
            if (!subMenu)
                Init.setMainMenuPanel(Init.shopPanel);
            else
                changePanel(buttonPanel);

        } else if (button.getActionCommand().equals("shopping")) {
            changePanel(new GScrollPane(new ShopListPanel(this, imageLoader, balance, generalDataIO)));

        } else if (button.getActionCommand().equals("upgrade")) {
            changePanel(new UpgradePanel(this, imageLoader, balance, generalDataIO));
        }
    }

    private void changePanel (JComponent panel) {
        goodsPanel.removeAll();
        goodsPanel.add(panel);
        subMenu = !subMenu;
        revalidate();
    }

}
