package program.game.shootingStars.entities;

import java.awt.Graphics;

public interface Entity {

    public void draw(Graphics g);
    public void generateStartPosition();
    public boolean isEntityOnScreen();
    public int getX();
    public int getY();
}
