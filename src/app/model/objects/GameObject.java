package app.model.objects;


import app.model.Direction;
import app.model.Model;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



/**
 * Created by Eugene on 17.07.17.
 */
public abstract class GameObject extends Pane{
    protected double length =0.0;
    protected Direction direction;
    protected ImageView imageView;

    public GameObject(double x, double y, double width, double height,ImageView imageView) {
        setTranslateX(x);
        setTranslateY(y);
        setWidth(width);
        setHeight(height);
        this.imageView=imageView;
    }

    public GameObject(double x, double y,ImageView imageView) {
        setTranslateX(x);
        setTranslateY(y);
        setWidth(Model.FIELD_CELL_SIZE);
        setHeight(Model.FIELD_CELL_SIZE);
        this.imageView=imageView;
    }


    public double getLength() {
        return length;
    }


    public boolean isCollision(GameObject gameObject, Direction direction) {
        double currentX,currentY;

        switch (direction) {
            case LEFT:
                currentX=getTranslateX()- length;
                currentY=getTranslateY();
                break;
            case RIGHT:
                currentX=getTranslateX()+ length;
                currentY=getTranslateY();
                break;
            case UP:
                currentX=getTranslateX();
                currentY=getTranslateY()- length;
                break;
            case DOWN:
                currentX=getTranslateX();
                currentY=getTranslateY()+ length;
                break;
            default:
                currentX=getTranslateX();
                currentY=getTranslateY();
                break;
        }
        double X=getTranslateX(),Y=getTranslateY();
        setTranslateX(currentX);
        setTranslateY(currentY);
        boolean isCollision=getBoundsInParent().intersects(gameObject.getBoundsInParent());
        setTranslateX(X);
        setTranslateY(Y);
        return isCollision;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
