package app.view;

import app.Game;
import javafx.fxml.FXML;

public class MenuScreenController {
    private Game game;

    @FXML
    private void handleContinue(){
        game.continuePlay();
    }
    @FXML
    private void handleExit(){
        game.exit();
    }
    @FXML
    private void handleRestart(){
        game.restartFromMenu();
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
