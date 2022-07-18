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

    private GButton backButton;


    public ShopPanel () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        balancePanel = new GPanel();
        balancePanel.setMaximumSize(new Dimension(765, 60));
        balancePanel.setPreferredSize(new Dimension(765, 60));

        balanceLabel = new GLabel ();
        balanceLabel.setIcon(new ImageLoader().getCoinIcon());
        balanceLabel.setIconTextGap(5);
        balanceLabel.setPreferredSize(new Dimension(765, 30));
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));
        balancePanel.add(balanceLabel);

        goodsPanel = new GPanel();

        backButtonPanel = new GPanel();

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
            }
        }
    }
}
