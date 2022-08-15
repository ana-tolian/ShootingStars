package program.game.shootingStars.io;

import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.EnemyShip;
import program.game.shootingStars.entities.PlayerShipModuleStats;
import program.game.shootingStars.variables.constant.GameConstant;
import program.game.shootingStars.variables.constant.PathConstant;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameGeneralDataIO {

    public static ArrayList<BuyablePlayerShip> shipsInStock;
    public static ArrayList<EnemyShip> typeOfEnemies;


    public GameGeneralDataIO() {
        shipsInStock = new ArrayList<>();
        typeOfEnemies = new ArrayList<>();
        loadPlayerShipsGeneralData();
        loadEnemyShipsGeneralData();
    }


    //
    //  Player general data IO
    //
    private static void loadPlayerShipsGeneralData() {
        String equippedShip = GamePlayerDataIO.loadEquipped();

        try (Scanner in = new Scanner(new File(PathConstant.FILE_PATH_SHIPS_INFO))) {
            in.nextLine();

            String [] s;
            int numOfGuns;
            int [] gunPosX;
            int [] gunPosY;

            while (in.hasNext()) {
                s = in.nextLine().split("#");

                numOfGuns = Integer.parseInt(s[2]);
                gunPosX = new int[numOfGuns];
                gunPosY = new int[numOfGuns];

                for (int i = 0; i < numOfGuns; i++) {
                    gunPosX[i] = Integer.parseInt(s[3 + 2*i]);
                    gunPosY[i] = Integer.parseInt(s[4 + 2*i]);
                }

                BufferedImage bi = ImageLoader.getSpriteByPath(s[4 + numOfGuns * 2]);
                PlayerShipModuleStats stats = GamePlayerDataIO.getStats(s[0]);

                shipsInStock.add(new BuyablePlayerShip(10, GameConstant.F_WIDTH / 2 - 50, GameConstant.F_HEIGHT / 2 - 50,
                        Integer.parseInt(s[1]), bi, ImageLoader.fireAnimationSprites, numOfGuns, gunPosX, gunPosY,
                        s[5 + numOfGuns * 2], stats, Integer.parseInt(s[6 + numOfGuns * 2]),
                        Boolean.parseBoolean(s[7 + numOfGuns * 2]), s[4 + numOfGuns * 2],
                        Integer.parseInt(s[8 + numOfGuns * 2])));

                if (s[0].equals(equippedShip)) {
                    shipsInStock.get(shipsInStock.size() - 1).equip();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePlayerGeneralData() {
        String s = getFirstLine() + "\n";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(PathConstant.FILE_PATH_SHIPS_INFO)))) {
            bw.write(s);

            for (BuyablePlayerShip ship : shipsInStock)
                bw.write(ship.toString());
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //  Enemies ship general data input
    //
    public static void loadEnemyShipsGeneralData () {
        try (Scanner in = new Scanner(new File(PathConstant.FILE_PATH_ENEMIES_INFO))) {
            in.nextLine();

            String [] s;
            int numOfGuns;
            int [] gunPosX;
            int [] gunPosY;

            while (in.hasNext()) {
                s = in.nextLine().split("#");

                numOfGuns = Integer.parseInt(s[2]);
                gunPosX = new int[numOfGuns];
                gunPosY = new int[numOfGuns];

                for (int i = 0; i < numOfGuns; i++) {
                    gunPosX[i] = Integer.parseInt(s[3 + 2 * i]);
                    gunPosY[i] = Integer.parseInt(s[4 + 2 * i]);
                }

                BufferedImage bi = ImageLoader.getSpriteByPath(s[4 + numOfGuns * 2]);

                typeOfEnemies.add(new EnemyShip(10, Integer.parseInt(s[1]), bi, numOfGuns,
                        gunPosX, gunPosY, Integer.parseInt(s[5 + numOfGuns * 2])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //
    //
    private static String getFirstLine () {
        String firstLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(PathConstant.FILE_PATH_SHIPS_INFO)))) {
            firstLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstLine;
    }

}
