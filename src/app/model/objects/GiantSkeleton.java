package app.model.objects;

import app.animation.SpriteAnimation;
import app.model.Direction;
import app.model.Model;
import app.model.items.Gold;
import app.model.items.Meat;
import app.model.items.PowerfulArtifact;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GiantSkeleton extends FightableObject {
    private SpriteAnimation animation;
    public GiantSkeleton(int x, int y, ImageView imageView, Image image, Direction direction) {
        super(x, y,107,107, 7, 30,imageView,image,direction);
        name="Гигантский\nСкелет";
        description="ЗДОРОВЬЕ: 30HP\nУРОН: 8";
        itemsToOffer="Можно предложить Мясо,\nЗолото";
        offerableItems.add(Meat.class);
        offerableItems.add(Gold.class);
        offerableItems.add(PowerfulArtifact.class);
        length = Model.FIELD_CELL_SIZE/13.0;
        viewport=new Rectangle2D(8,236,90,90);
        animation=new SpriteAnimation(this.imageView, Duration.millis(1000),8,8,0,0,(int)getWidth(),(int)getHeight());
        getChildren().add(this.imageView);
    }

    @Override
    public void playAnimation() {
        switch (direction) {
            case UP:
                animation.play();
                animation.setOffsetY(431);
                break;
            case DOWN:
                animation.play();
                animation.setOffsetY(645);
                break;
            case LEFT:
                animation.play();
                animation.setOffsetY(540);
                break;
            case RIGHT:
                animation.play();
                animation.setOffsetY(753);
                break;

        }

    }

    @Override
    protected void initParameters() {
        parameters.put("count",8);
        parameters.put("defaultCount",1);
        parameters.put("fightCount",8);
        parameters.put("defaultOffsetY",107);
        parameters.put("fightOffsetY",540);
        parameters.put("defaultDuration",1000);
        parameters.put("fightDuration",900);
        parameters.put("difficulty",7);
        parameters.put("forPunch",4);
    }
}
