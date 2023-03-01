package program.game.shootingStars.io;

import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.PlayerShipModuleStats;
import program.game.shootingStars.variables.constant.PathConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class GamePlayerDataIO {

    public static ArrayList<PlayerShipModuleStats> ownedShips = new ArrayList<>();

    private static final File resultsFile = new File (PathConstant.FILE_PATH_SAVED_RESULTS);
    private static final File equippedPlayerShip = new File (PathConstant.FILE_PATH_EQUIPPED);
    private static final File playerShipStats = new File (PathConstant.FILE_PATH_PLAYER_SHIP_STATS);
    private static final File moneyFile  = new File (PathConstant.FILE_PATH_MONEY);


    public GamePlayerDataIO() {
        loadPlayerStats();
    }


    // Save results after game ends
    public static void save (int score, int distance) {
        saveRecord(score, distance);
        saveMoney(score);
    }

    // Change player balance
    public static void changeBalanceAndSave (int cost) {
        saveMoney(-cost);
    }


    //
    //  Save/load info about equipped ship
    //
    public static String loadEquipped () {
        String str = "";

        if (!isFileExist(equippedPlayerShip))
            return "Rocket";

        try (BufferedReader br = new BufferedReader(new FileReader(equippedPlayerShip))) {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void saveEquipped (BuyablePlayerShip ship) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(equippedPlayerShip))) {
            bw.write(ship.getName());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //
    //  Save/load player ship stats (weapon level, hull level etc)
    //
    public static PlayerShipModuleStats loadPlayerStats() {
        PlayerShipModuleStats stats = null;
        String equipped = loadEquipped();

        try (Scanner in = new Scanner(playerShipStats)) {
           in.nextLine();

           String temp;
           while (in.hasNext()) {
               temp = in.nextLine();

               if (temp.contains(equipped)) {
                   stats = readStats(temp);
                   ownedShips.add(stats);
               } else
                   ownedShips.add(readStats(temp));
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stats;
    }

    private static PlayerShipModuleStats readStats (String temp) {
        String [] s = temp.split("#");
        return new PlayerShipModuleStats(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), s[3]);
    }

    public static void savePlayerStats () {
        String firstLine = getFirstLine(playerShipStats);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(playerShipStats))) {
            bw.write(firstLine);
            bw.newLine();

            for (PlayerShipModuleStats p : ownedShips) {
                bw.write(p.toString());
                bw.newLine();
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //
    //  Save player record (distance and score)
    //
    private static void saveRecord (int score, int distance) {
        String [] records = (getLines(resultsFile, 10) + distance + "#" + score).split(" ");
        Arrays.sort(records);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultsFile))) {
            for (int i = 0; i < 5 && i < records.length; i++) {
                if (!records[i].equals("") && records[i] != null) {
                    bw.write(records[i] + " \n");
                }
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //  Save/load player's balance to file
    //
    private static void saveMoney (int money) {
        money = loadMoney() + money;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(moneyFile, false))) {
            bw.write(money + "");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadMoney () {
        int money = 0;

        if(!isFileExist(moneyFile))
            return money;

        try (BufferedReader br = new BufferedReader(new FileReader(moneyFile))) {
            String str = br.readLine();
            money = Integer.parseInt(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return money;
    }


    //
    //
    //
    private static boolean isFileExist (File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            }  catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    private static String getFirstLine (File file) {
        String firstLine = "";
        try (Scanner in = new Scanner (file)) {
            firstLine = in.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstLine;
    }

    private static String getLines (File file, int j) {
        String lines = "";
        try (Scanner in = new Scanner (file)) {
            for (int i = 0; i < j && in.hasNextLine(); i++)
                lines += in.nextLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static PlayerShipModuleStats getStats (String name) {
        for (PlayerShipModuleStats p : ownedShips)
            if (p.getName().equals(name))
                return p;
            return null;
    }

}
