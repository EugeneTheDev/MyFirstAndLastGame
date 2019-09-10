package app.model.objects;



import app.animation.SpriteAnimation;
import app.model.Direction;
import app.model.Model;
import app.model.Movable;
import app.model.items.Item;
import javafx.geometry.BoundingBox;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by Eugene on 17.07.17.
 */
public class Hero extends FightableObject implements Movable {
    private SpriteAnimation animation;
    private double[] displacement;
    private ArrayList<Item> items;
    public Hero(int x, int y, ImageView imageView, Image image) {
        super(x, y,65,57,10,100,imageView,image,Direction.NOTHING);//65 57
        displacement=new double[2];
        isAlive=true;
        items=new ArrayList<>(5);
        length = Model.FIELD_CELL_SIZE/11.0;
        animation=new SpriteAnimation(this.imageView, Duration.millis(600),4,4,0,454,(int)getWidth(),(int)getHeight());
        getChildren().add(this.imageView);
        checkPosition();
        initialize();
    }
    @Override
    public void move(Direction direction) {
        switch (direction){
            case UP:
                setTranslateY(getTranslateY()-length);
                break;
            case DOWN:
                setTranslateY(getTranslateY()+length);
                break;
            case LEFT:
                setTranslateX(getTranslateX()-length);
                break;
            case RIGHT:
                setTranslateX(getTranslateX()+length);
                break;
        }
        draw(direction);
    }
    @Override
    @Deprecated
    public void playAnimation(){/*do nothing*/}

    @Override
    protected void initParameters() {
        parameters.put("count",11);
        parameters.put("defaultCount",4);
        parameters.put("fightCount",11);
        parameters.put("defaultOffsetY",454);
        parameters.put("fightOffsetY",516);
        parameters.put("defaultDuration",600);
        parameters.put("fightDuration",1000);
    }

    public double[] getDisplacement(){
        return displacement;
    }
    protected void draw(Direction direction){
        switch (direction) {
            case UP:
                animation.play();
                animation.setOffsetY(155);
                break;
            case DOWN:
                animation.play();
                animation.setOffsetY(0);
                break;
            case LEFT:
                animation.play();
                animation.setOffsetY(58);
                break;
            case RIGHT:
                animation.play();
                animation.setOffsetY(106);
                break;
            case NOTHING:
                animation.play();
                animation.setOffsetY(454);
                break;
        }
    }
    public void increaseHealth(int dHealth){
        if (dHealth>=(fullHealth-health)) health=fullHealth;
        else health+=dHealth;
    }
    private void initialize(){
        translateXProperty().addListener((obs, old, newValue)->{
            displacement[0]=400-newValue.doubleValue();
            if(displacement[0]>0) displacement[0]=0;

        });
        translateYProperty().addListener(((observable, oldValue, newValue) -> {
            displacement[1]=300-newValue.doubleValue();
            if(displacement[1]>0) displacement[1]=0;

        }));
    }
    private void checkPosition(){
        displacement[0]=400-getTranslateX();
        if(displacement[0]>0) displacement[0]=0;
        displacement[1]=300-getTranslateY();
        if(displacement[1]>0) displacement[1]=0;
    }
    public void changeLength(double multiplyEffect){
        length=length*multiplyEffect;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public boolean isCollision(GameObject gameObject, Direction direction) {
        if((gameObject instanceof Door&&((Door) gameObject).isOpen())||gameObject instanceof Portal) return false;

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
        boolean isCollision=new BoundingBox(currentX+15,currentY+15,getWidth()-30,getHeight()-24).intersects(gameObject.getBoundsInParent());
        //boolean isCollision=this.getBoundsInParent().intersects(gameObject.getBoundsInParent());
        setTranslateX(X);
        setTranslateY(Y);
        return isCollision;
    }
}
