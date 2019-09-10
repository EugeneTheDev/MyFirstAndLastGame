package app.view;

import app.Game;
import javafx.fxml.FXML;

public class FinalScreenController {
    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }
    @FXML
    private void handleMenu(){
        game.exit();
    }
}
