package program.game.shootingStars.entities.set;

import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.StaticEntity;

import java.awt.Graphics;

public interface Set {
    void move (PlayerShip pl);
    void draw (Graphics g);
    StaticEntity getEntity (int i);
    void removeEntity (int i);
    int getSize ();
}
