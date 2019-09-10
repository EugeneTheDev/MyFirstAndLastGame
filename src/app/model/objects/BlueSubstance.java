package app.model.objects;

import app.animation.SpriteAnimation;
import app.model.Direction;
import app.model.Model;
import app.model.items.Meat;
import app.model.items.PowerfulArtifact;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BlueSubstance extends FightableObject {
    private SpriteAnimation animation;

    public BlueSubstance(int x, int y,ImageView imageView, Image image) {
        super(x, y, 66, 57, 4, 35, imageView, image, Direction.NOTHING);
        name="Голубая\nжижа";
        description="ЗДОРОВЬЕ: 35HP\nУРОН: 4";
        itemsToOffer="Можно предложить мясо";
        offerableItems.add(Meat.class);
        length = Model.FIELD_CELL_SIZE/13.0;
        animation=new SpriteAnimation(this.imageView, Duration.millis(900),5,5,0,0,(int)getWidth(),(int)getHeight());
        viewport=new Rectangle2D(0,0,getWidth(),getHeight());
        getChildren().add(this.imageView);
    }

    @Override
    public void playAnimation() {
        animation.play();
    }

    @Override
    protected void initParameters() {
        parameters.put("count",5);
        parameters.put("defaultCount",5);
        parameters.put("fightCount",3);
        parameters.put("defaultOffsetY",0);
        parameters.put("fightOffsetY",67);
        parameters.put("defaultDuration",900);
        parameters.put("fightDuration",500);
        parameters.put("difficulty",5);
        parameters.put("forPunch",5);
    }
}