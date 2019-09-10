package app.model.objects;

import app.animation.SpriteAnimation;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Door extends GameObject {
    private SpriteAnimation animation;
    private boolean isOpen;

    public Door(double x, double y, ImageView imageView) {
        super(x, y,55,55,imageView);
        isOpen=false;
        animation=new SpriteAnimation(this.imageView, Duration.millis(700),5,5,0,0,getWidth(),getHeight());
        getChildren().add(this.imageView);
    }

    public void open(){
        isOpen=true;
        animation.play();
    }

    public boolean isOpen() {
        return isOpen;
    }
}
