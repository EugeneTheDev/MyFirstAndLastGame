package app.view;

import app.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class SignController {
    @FXML
    private Label label;
    private Game game;

    @FXML
    private void handleOk(){
        game.cancel();
    }
    public void setText(String text){
        label.setText(text);
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
