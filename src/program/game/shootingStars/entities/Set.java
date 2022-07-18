package program.game.shootingStars.entities;

import java.awt.Graphics;

public interface Set {
    public int move (PlayerShip pl);
    public void draw (Graphics g);
    public void generateEntity ();
}
