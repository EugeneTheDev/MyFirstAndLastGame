package app.view;

import app.Game;
import app.model.objects.FightableObject;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class EnemyDescriptionController {
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label itemsToOffer;
    @FXML
    private ImageView icon;
    private Game game;

    @FXML
    private void handleFight(){
        game.fight();
    }
    @FXML
    private void handleCancel(){
        game.cancel();
    }
    public void updateDescription(FightableObject enemy){
        name.setText(enemy.getName());
        description.setText(enemy.getDescription());
        itemsToOffer.setText(enemy.getItemsToOffer());
        icon.setImage(enemy.getImage());
        icon.setViewport(enemy.getViewport());
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
