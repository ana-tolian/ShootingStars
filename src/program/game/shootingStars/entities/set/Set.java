package program.game.shootingStars.entities.set;

import program.game.shootingStars.entities.PlayerShip;

import java.awt.Graphics;

public interface Set {
    public void move (PlayerShip pl);
    public void draw (Graphics g);
    public void generateEntity ();
}
