package app.view;

import app.Game;
import app.model.items.Item;
import app.model.objects.Chest;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class ForChestController {
    @FXML
    private ImageView icon;
    @FXML
    private Label name;
    @FXML
    private Label description;
    private Game game;
    private Chest chest;

    @FXML
    private void handlePickUp(){
        game.addItem(chest);
    }
    @FXML
    private void handleLeave(){
        game.cancel();
    }
    public void initDescription(Chest chest){
        this.chest=chest;
        Item item=chest.getItem();
        if(item!=null) {
            icon.setImage(item.getImage());
            name.setText(item.getName());
            description.setText(item.getDescription());
        }else{
            icon.setImage(null);
            name.setText("Ой");
            description.setText("Похоже, что этот\nсундук пустой");
        }
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
