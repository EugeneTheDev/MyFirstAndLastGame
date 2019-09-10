package app.model.fightgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cube extends Pane {
    private String image;
    private ImageView view;
    public Cube(double x,double y,double width,double height,Image img,String image) {
        setWidth(width);
        setHeight(height);
        setTranslateX(x);
        setTranslateY(y);
        this.image=image;
        view=new ImageView(img);
        view.setFitWidth(width);
        view.setFitHeight(height);
        getChildren().add(view);
    }

    public String getImage() {
        return image;
    }

    public void move(double dx){
        setTranslateX(getTranslateX()-dx);
    }
    public void updateRectangle(double height){
        view.setFitHeight(height);
    }
}
