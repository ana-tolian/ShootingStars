package program.game.shootingStars.entities;

import javax.swing.*;

public interface Buyable {

    String getName();
    String getDescription ();
    int getCost ();
    boolean isBought ();
    String getSpecial ();
    ImageIcon getIcon ();
}