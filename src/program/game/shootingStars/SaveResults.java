package program.game.shootingStars;

import program.game.shootingStars.variables.constant.PathConstant;

import java.io.*;


public class SaveResults {

    private int score;
    private int distance;
    private File resultsFile;
    private File moneyFile;

    public SaveResults () {
        this.score = 0;
        this.distance = 0;
        this.resultsFile = new File (PathConstant.FILE_PATH_SAVED_RESULTS);
        this.moneyFile = new File (PathConstant.FILE_PATH_MONEY);
    }

    public SaveResults (int score, int distance) {
        this.score = score;
        this.distance = distance;
        this.resultsFile = new File (PathConstant.FILE_PATH_SAVED_RESULTS);
        this.moneyFile = new File (PathConstant.FILE_PATH_MONEY);
    }


    public void save () {
        saveRecord();
        saveMoney();
    }

    private void saveRecord () {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultsFile, true))) {
            bw.write("| score: " + score + " distance: " + distance + " | ");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveMoney () {
        int money = loadMoney() + score;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(moneyFile, false))) {
            bw.write(money + "");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadMoney () {
        int money = 0;

        if(!isFileExist())
            return money;

        try (BufferedReader br = new BufferedReader(new FileReader(moneyFile))) {
            String str = br.readLine();
            money = Integer.parseInt(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return money;
    }

    private boolean isFileExist() {
        if (!moneyFile.exists()) {
            try {
                moneyFile.createNewFile();
            }  catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
