package app.model.fightgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Player extends Pane {
    private String image;
    private ImageView view;

    public Player(double x,double y,Image img,String image) {
        setWidth(80);
        setHeight(100);
        setTranslateX(x);
        setTranslateY(y);
        this.image=image;
        view=new ImageView(img);
        view.setFitHeight(getHeight());
        view.setFitWidth(getWidth());
        getChildren().add(view);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image,Image img) {
        this.image = image;
        view.setImage(img);
    }
}
