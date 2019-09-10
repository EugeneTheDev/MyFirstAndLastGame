package app.view;

import app.Game;
import app.model.items.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ItemDescriptionController {
    @FXML
    private ImageView icon;
    @FXML
    private Label name;
    @FXML
    private Label description;
    private Game game;
    private int number;


    @FXML
    private void handleUse(){
        game.useItem(number);
    }
    @FXML
    private void handleCancel(){
        game.cancel();
    }
    public void initDescription(Item item,int number){
        this.number=number;
        icon.setImage(item.getImage());
        name.setText(item.getName());
        description.setText(item.getDescription());
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
