package program.game.shootingStars;

import program.game.shootingStars.entities.BuyablePlayerShip;
import program.game.shootingStars.variables.constant.PathConstant;

import java.io.*;


public class GamePlayerDataIO {

    private File resultsFile;
    private File equippedPlayerShip;
    private File moneyFile;

    public GamePlayerDataIO() {
        this.resultsFile = new File (PathConstant.FILE_PATH_SAVED_RESULTS);
        this.moneyFile = new File (PathConstant.FILE_PATH_MONEY);
        this.equippedPlayerShip = new File (PathConstant.FILE_PATH_EQUIPPED);
    }


    public void save (int score, int distance) {
        saveRecord(score, distance);
        saveMoney(score);
    }

    public void changeBalanceAndSave (int cost) {
        saveMoney(-cost);
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

    private void saveRecord (int score, int distance) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultsFile, true))) {
            bw.write("| score: " + score + " distance: " + distance + " | ");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveMoney (int money) {
        money = loadMoney() + money;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(moneyFile, false))) {
            bw.write(money + "");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
