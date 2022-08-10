package program.game.shootingStars.entities;

import javax.swing.*;

public interface Buyable {

    String getName();
    String getDescription ();
    int getCost ();
    boolean isBought ();
    void buy ();
    String getSpecial ();
    ImageIcon getIcon ();
}
