package program.game.shootingStars.menu;

import program.game.shootingStars.io.ImageLoader;
import program.game.shootingStars.Init;
import program.game.shootingStars.io.GamePlayerDataIO;
import program.game.shootingStars.ui.GButton;
import program.game.shootingStars.ui.GLabel;
import program.game.shootingStars.ui.GPanel;
import program.game.shootingStars.ui.GScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends GPanel implements ActionListener {

    private final GPanel balancePanel;
    private final GPanel goodsPanel;
    private final GPanel buttonPanel;
    private final GPanel backButtonPanel;

    private final GLabel balanceLabel;

    private final GButton upgradeShipMenuButton;
    private final GButton shipShopMenuButton;
    private final GButton backButton;

    private boolean subMenu = false;


    public ShopPanel () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        balancePanel = new GPanel();

        balanceLabel = new GLabel ();
        balanceLabel.setIcon(new ImageIcon(ImageLoader.coinSprite));
        balanceLabel.setIconTextGap(5);
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
        balancePanel.add(balanceLabel);

        goodsPanel = new GPanel();
        goodsPanel.setLayout(new BorderLayout());

        shipShopMenuButton = new GButton ("");
        shipShopMenuButton.setPreferredSize(new Dimension(340, 340));
        shipShopMenuButton.setIcon(new ImageIcon(ImageLoader.shopIcon));
        shipShopMenuButton.setActionCommand("shopping");
        shipShopMenuButton.addActionListener(this);

        GLabel nullLabel = new GLabel();
        nullLabel.setPreferredSize(new Dimension(30, 30));

        upgradeShipMenuButton = new GButton ("");
        upgradeShipMenuButton.setPreferredSize(new Dimension(340, 340));
        upgradeShipMenuButton.setIcon(new ImageIcon(ImageLoader.upgradeIcon));
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
        balanceLabel.setText(GamePlayerDataIO.loadMoney() + "$");
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
            changePanel(new GScrollPane(new ShopListPanel(this)));

        } else if (button.getActionCommand().equals("upgrade")) {
            changePanel(new UpgradePanel(this));
        }
    }

    private void changePanel (JComponent panel) {
        goodsPanel.removeAll();
        goodsPanel.add(panel);
        subMenu = !subMenu;
        revalidate();
    }

}
