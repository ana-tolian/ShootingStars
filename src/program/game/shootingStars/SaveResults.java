package program.game.shootingStars;

import program.game.shootingStars.variables.constant.PathConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class SaveResults {

    private int score;
    private int distance;
    private File results;

    public SaveResults (int score, int distance) {
        this.score = score;
        this.distance = distance;
        this.results = new File (PathConstant.FILE_PATH_SAVED_RESULTS);

        save();
    }


    private void save () {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(results, true));) {
            bw.write("| score: " + score + " distance: " + distance + " | ");
            bw.newLine();
            bw.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
