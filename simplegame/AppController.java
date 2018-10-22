package simplegame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AppController {

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton scissors,  paper, stone;

    @FXML
    private ImageView imgPlayer, imgComputer;

    @FXML
    private Label winL, loseL, remisL, scoreL;

    private String activePlayerChoise, activeComputerChoise;
    private Map<Integer, String> items = new HashMap<>();
    private Random random = new Random();
    private int win = 0, lose = 0, remis = 0, score = 0;

    public void initialize() {
        items.put(1, "Scissors");
        items.put(2, "Paper");
        items.put(3, "Stone");

        scissors.setUserData(items.get(1));
        paper.setUserData(items.get(2));
        stone.setUserData(items.get(3));

        activePlayerChoise = items.get(2);
        imgPlayer.setImage(new Image("img/2.png"));

    }

    public void show() {
        activePlayerChoise = group.getSelectedToggle().getUserData().toString();
        choise(activePlayerChoise, imgPlayer);
    }

    public void click() {
        activeComputerChoise = items.get(random.nextInt(3) + 1);
        choise(activeComputerChoise, imgComputer);
        check();
        score = win*3 - lose*2 + remis;
        scoreL.setText(String.valueOf(score));
    }

    private void choise(String s, ImageView img) {
        if (s.equals("Scissors"))
            img.setImage(new Image("img/1.png"));
        if (s.equals("Paper"))
            img.setImage(new Image("img/2.png"));
        if (s.equals("Stone"))
            img.setImage(new Image("img/3.png"));
    }
    private void check() {
        /**
         * check the winning for scissors
         * win vs paper
         * lose vs stone
         */
        if (activePlayerChoise.equals(items.get(1))) {
           if (activeComputerChoise.equals(items.get(2))) {
               win++;
               winL.setText(String.valueOf(win));
           } else  if (activeComputerChoise.equals(items.get(3))) {
               lose++;
               loseL.setText(String.valueOf(lose));
           } else {
               remis++;
               remisL.setText(String.valueOf(remis));
           }
        }
        /**
         * check the winning for paper
         * win vs stone
         * lose vs scissors
         */
        else if (activePlayerChoise.equals(items.get(2))) {
            if (activeComputerChoise.equals(items.get(3))) {
                win++;
                winL.setText(String.valueOf(win));
            } else if (activeComputerChoise.equals(items.get(1))) {
                lose++;
                loseL.setText(String.valueOf(lose));
            } else {
                remis++;
                remisL.setText(String.valueOf(remis));
            }
        }
        /**
         * check the winning for stone
         * win vs scissors
         * lose vs paper
         */
        else if (activePlayerChoise.equals(items.get(3))) {
            if (activeComputerChoise.equals(items.get(1))) {
                win++;
                winL.setText(String.valueOf(win));
            } else if (activeComputerChoise.equals(items.get(2))) {
                lose++;
                loseL.setText(String.valueOf(lose));
            } else {
                remis++;
                remisL.setText(String.valueOf(remis));
            }
        }
    }
}
