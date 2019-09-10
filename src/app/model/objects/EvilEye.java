package app.model.objects;

import app.animation.SpriteAnimation;
import app.model.Direction;
import app.model.Model;
import app.model.items.Potion;
import app.model.items.PowerfulArtifact;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class EvilEye extends FightableObject {
    private SpriteAnimation animation;
    public EvilEye(int x, int y, ImageView imageView, Image image) {
        super(x, y,44,50, 10, 40, imageView, image, Direction.NOTHING);
        name="Глазастик";
        description="ЗДОРОВЬЕ: 40HP\nУРОН: 10";
        itemsToOffer="Можно предложить\nГлазные капли";
        offerableItems.add(Potion.class);
        offerableItems.add(PowerfulArtifact.class);
        length = Model.FIELD_CELL_SIZE/13.0;
        viewport=new Rectangle2D(0,0,36,getHeight());
        animation=new SpriteAnimation(this.imageView, Duration.millis(720),4,4,0,113,getWidth(),getHeight());
        getChildren().add(this.imageView);
    }

    @Override
    public void playAnimation() {
       animation.play();
    }

    @Override
    protected void initParameters() {
        parameters.put("count",6);
        parameters.put("defaultCount",3);
        parameters.put("fightCount",6);
        parameters.put("defaultOffsetY",298);
        parameters.put("fightOffsetY",244);
        parameters.put("defaultDuration",550);
        parameters.put("fightDuration",1000);
        parameters.put("difficulty",6);
        parameters.put("forPunch",4);
        parameters.put("width",51);
    }
}
