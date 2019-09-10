package app.view;

import app.Game;
import javafx.fxml.FXML;

/**
 * Created by Eugene on 28.10.17.
 */
public class StartScreenController {
    private Game game;

    @FXML
    private void handleNewGame(){
        game.playGame();
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
