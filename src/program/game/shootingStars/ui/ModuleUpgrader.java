package program.game.shootingStars.ui;

import program.game.shootingStars.io.ImageLoader;
import program.game.shootingStars.entities.BuyablePlayerShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModuleUpgrader extends GPanel {

    private GScaleLabel scaleLabel;
    private GButton incrementLevelButton;
    private GLabel nameLabel;
    private GLabel costUpgradeLabel;

    private BuyablePlayerShip equippedShip;


    public ModuleUpgrader (BuyablePlayerShip equippedShip, int level, String name) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        this.equippedShip = equippedShip;

        nameLabel = new GLabel(name);
        scaleLabel = new GScaleLabel(level);

        costUpgradeLabel = new GLabel();
        costUpgradeLabel.setIcon(new ImageIcon(ImageLoader.coinSprite));
        setCostToLabel();

        incrementLevelButton = new GButton();
        incrementLevelButton.setIcon(new ImageIcon(ImageLoader.plusImage));
        incrementLevelButton.setPreferredSize(new Dimension(40, 40));

        add(nameLabel);
        add(scaleLabel);
        add(incrementLevelButton);
        add(costUpgradeLabel);
    }

    public void upgrade () {
        scaleLabel.increment();
        setCostToLabel();
    }

    public boolean isMaxLevel () {
        return scaleLabel.isMaxLevel();
    }

    private void setCostToLabel () {
        if (!scaleLabel.isMaxLevel())
            costUpgradeLabel.setText(equippedShip.getCostOfUpgrade(scaleLabel.getLevel()) + "");
        else
            costUpgradeLabel.setText("Max");
        revalidate();
    }

    public void addActionListener (ActionListener listener) {
        incrementLevelButton.addActionListener(listener);
    }

    public void setActionCommand (String command) {
        incrementLevelButton.setActionCommand(command);
    }

}
