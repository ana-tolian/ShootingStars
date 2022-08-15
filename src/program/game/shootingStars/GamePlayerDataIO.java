package program.game.shootingStars;

import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.entities.PlayerShip;
import program.game.shootingStars.entities.PlayerShipModuleStats;
import program.game.shootingStars.variables.constant.PathConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class GamePlayerDataIO {

    private ArrayList<PlayerShipModuleStats> ownedShips;

    private File resultsFile;
    private File equippedPlayerShip;
    private File playerShipStats;
    private File moneyFile;


    public GamePlayerDataIO() {
        this.resultsFile = new File (PathConstant.FILE_PATH_SAVED_RESULTS);
        this.moneyFile = new File (PathConstant.FILE_PATH_MONEY);
        this.equippedPlayerShip = new File (PathConstant.FILE_PATH_EQUIPPED);
        this.playerShipStats = new File (PathConstant.FILE_PATH_PLAYER_SHIP_STATS);
        this.ownedShips = new ArrayList<>();
    }


    // Save results after game ends
    public void save (int score, int distance) {
        saveRecord(score, distance);
        saveMoney(score);
    }

    // Change player balance
    public void changeBalanceAndSave (int cost) {
        saveMoney(-cost);
    }


    //
    //  Save/load info about equipped ship
    //
    public String loadEquipped () {
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

    public void saveEquipped (BuyablePlayerShip ship) {
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
    public PlayerShipModuleStats getPlayerStats () {
        PlayerShipModuleStats stats = null;
        String equipped = loadEquipped();

        try (Scanner in = new Scanner(playerShipStats)) {
           in.nextLine();

           String temp;
           while (in.hasNext()) {
               temp = in.nextLine();
               ownedShips.add((readStats(temp)));

               if (temp.contains(equipped))
                   stats = ownedShips.get(ownedShips.size() - 1);
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stats;
    }

    private PlayerShipModuleStats readStats (String temp) {
        String s [] = temp.split("#");
        return new PlayerShipModuleStats(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), s[3]);
    }

    public void savePlayerStats (PlayerShipModuleStats stats) {
        String firstLine = getFirstLine();

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
    private void saveRecord (int score, int distance) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultsFile, true))) {
            bw.write("| score: " + score + " distance: " + distance + " | ");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    //  Save/load player's balance to file
    //
    private void saveMoney (int money) {
        money = loadMoney() + money;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(moneyFile, false))) {
            bw.write(money + "");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadMoney () {
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
    private boolean isFileExist (File file) {
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

    private String getFirstLine () {
        String firstLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(PathConstant.FILE_PATH_PLAYER_SHIP_STATS)))) {
            firstLine = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstLine;
    }

    public ArrayList<PlayerShipModuleStats> getOwnedShips () {
        return ownedShips;
    }
}
