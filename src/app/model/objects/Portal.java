package app.model.objects;

import app.model.Direction;
import app.model.Model;
import javafx.scene.image.ImageView;

public class Portal extends GameObject {
    public Portal(double x, double y, ImageView imageView) {
        super(x, y, Model.FIELD_CELL_SIZE,Model.FIELD_CELL_SIZE*1.3, imageView);
        getChildren().add(this.imageView);
    }

    @Override
    public boolean isCollision(GameObject gameObject, Direction direction) {
        return false;
    }
}
