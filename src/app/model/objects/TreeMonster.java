package app.model.objects;

import app.animation.SpriteAnimation;
import app.model.Direction;
import app.model.Model;
import app.model.items.Leaf;
import app.model.items.PowerfulArtifact;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TreeMonster extends FightableObject {
    private SpriteAnimation animation;

    public TreeMonster(int x, int y,ImageView imageView, Image image, Direction direction) {
        super(x, y, 55, 53, 7, 50, imageView, image, direction);
        length= Model.FIELD_CELL_SIZE/10.0;
        name="Поехавший\nпень";
        description="ЗДОРОВЬЕ: 50HP\nУРОН: 9";
        itemsToOffer="Можно предложить\nВеселый листик";
        offerableItems.add(Leaf.class);
        offerableItems.add(PowerfulArtifact.class);
        animation=new SpriteAnimation(this.imageView, Duration.millis(700),4,4,108,0,(int)getWidth(),(int)getHeight());
        viewport=new Rectangle2D(0,0,getWidth(),getHeight());
        getChildren().add(this.imageView);
    }


    @Override
    public void playAnimation() {
        switch (direction) {
            case UP:
                animation.play();
                animation.setOffsetY(55);
                break;
            case DOWN:
                animation.play();
                animation.setOffsetY(0);
                break;
            case LEFT:
                animation.play();
                animation.setOffsetY(108);
                break;
            case RIGHT:
                animation.play();
                animation.setOffsetY(159);
                break;

        }
    }

    @Override
    protected void initParameters() {
        parameters.put("count",6);
        parameters.put("defaultCount",6);
        parameters.put("fightCount",6);
        parameters.put("defaultOffsetY",0);
        parameters.put("fightOffsetY",420);
        parameters.put("defaultDuration",800);
        parameters.put("fightDuration",900);
        parameters.put("difficulty",7);
        parameters.put("forPunch",4);
    }
}
