package app.view;

import app.Game;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class DialogScreenController {
    @FXML
    private VBox box;
    private Game game;

    @FXML
    private void handleOk(){
        game.startPlay();
    }
    public void setStory(ArrayList<String> storiesList){
        box.getChildren().clear();
        storiesList.forEach(s -> {
            String character=s.split(": ")[0];
            Label label=new Label(s);
            switch (character){
                case "White Face":
                    label.setFont(new Font("Calisto MT",20));
                    label.setTextFill(Color.web("#ab090e"));
                    break;
                case "Голос":
                    label.setFont(new Font("Cambria Italic",20));
                    label.setTextFill(Color.web("#6f0c78"));
                    break;
                default:
                    label.setTextFill(Color.web("#e17c18"));
                    label.setFont(new Font("Comic Sans MS",18));
                    break;
            }
            box.getChildren().add(label);
        });
    }
    public void setGame(Game game) {
        this.game = game;
    }
    //#e17c18
    //Comic Sans MS
}
