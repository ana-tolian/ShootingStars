package program.game.shootingStars;

import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.variables.constant.PathConstant;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameInfoLoader {

    public ArrayList<BuyablePlayerShip> shipsInStock;
    public ArrayList<PlayerShip> ownedShips;

    public GameInfoLoader () {
        shipsInStock = new ArrayList<>();
        ownedShips = new ArrayList<>();
        loadInfo();
    }

    private void loadInfo () {
        try (Scanner in = new Scanner(new File(PathConstant.FILE_PATH_SHIPS_INFO))) {
            in.nextLine();

            String s[];
            int numOfGuns;
            int gunPosX[];
            int gunPosY[];

            while (in.hasNext()) {
                s = in.nextLine().split("#");

                numOfGuns = Integer.parseInt(s[2]);
                gunPosX = new int[numOfGuns];
                gunPosY = new int[numOfGuns];

                for (int i = 0; i < numOfGuns; i++) {
                    gunPosX[i] = Integer.parseInt(s[3 + i]);
                    gunPosY[i] = Integer.parseInt(s[4 + i]);
                }

                BufferedImage br = ImageLoader.getSpriteByPath(s[4 + numOfGuns * 2]);

                shipsInStock.add(new BuyablePlayerShip(10, 0, 0, Integer.parseInt(s[1]), br,
                        ImageLoader.fireAnimationSprites, numOfGuns, gunPosX, gunPosY, s[0], s[5 + numOfGuns * 2],
                        s[3 + numOfGuns * 2], Integer.parseInt(s[6 + numOfGuns * 2]), Boolean.parseBoolean(s[7 + numOfGuns * 2])));
                System.out.println(shipsInStock.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BuyablePlayerShip> getShipsInStock () {
        return shipsInStock;
    }
}
