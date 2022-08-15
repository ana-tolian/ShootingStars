package program.game.shootingStars;

import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.variables.constant.GameConstant;
import program.game.shootingStars.variables.constant.PathConstant;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameGeneralDataIO {

    private ArrayList<BuyablePlayerShip> shipsInStock;


    public GameGeneralDataIO() {
        shipsInStock = new ArrayList<>();
        loadInfo();
    }

    private void loadInfo () {
        String equippedShip = new GamePlayerDataIO().loadEquipped();

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
                    gunPosX[i] = Integer.parseInt(s[3 + 2*i]);
                    gunPosY[i] = Integer.parseInt(s[4 + 2*i]);
                }

                BufferedImage br = ImageLoader.getSpriteByPath(s[4 + numOfGuns * 2]);

                shipsInStock.add(new BuyablePlayerShip(10, GameConstant.F_WIDTH / 2 - 50, GameConstant.F_HEIGHT / 2 - 50,
                        Integer.parseInt(s[1]), br, ImageLoader.fireAnimationSprites, numOfGuns, gunPosX, gunPosY, s[0],
                        s[5 + numOfGuns * 2], s[3 + numOfGuns * 2], Integer.parseInt(s[6 + numOfGuns * 2]),
                        Boolean.parseBoolean(s[7 + numOfGuns * 2]), s[4 + numOfGuns * 2]));

                if (s[0].equals(equippedShip)) {
                    shipsInStock.get(shipsInStock.size() - 1).equip();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInfo () {
        String s = getFirstLine() + "\n";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(PathConstant.FILE_PATH_SHIPS_INFO)))) {
            bw.write(s);

            for (int i = 0; i < shipsInStock.size(); i++)
                bw.write(shipsInStock.get(i).toString());
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFirstLine () {
        String firstLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(PathConstant.FILE_PATH_SHIPS_INFO)))) {
            firstLine = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstLine;
    }


    public ArrayList<BuyablePlayerShip> getShipsInStock () {
        return shipsInStock;
    }
}
