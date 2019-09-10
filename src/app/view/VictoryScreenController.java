package app.view;

import app.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VictoryScreenController {
    @FXML
    private Label label;
    private Game game;

    @FXML
    private void handleContinue(){
        game.startNextLevel();
    }
    @FXML
    private void handleMenu(){
        game.exit();
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public void updateLabel(String level){
        label.setText("Level "+level+" completed");
    }

}
