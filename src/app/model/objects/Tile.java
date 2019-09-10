package app.model.objects;


import app.model.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Eugene on 02.11.17.
 */
public class Tile extends GameObject {
    public Tile(double x, double y,ImageView imageView) {
        super(x, y,imageView);
        getChildren().add(this.imageView);
    }

    @Override
    public boolean isCollision(GameObject gameObject, Direction direction) {
        return false;
    }
}
