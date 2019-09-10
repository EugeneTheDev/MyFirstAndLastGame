package app.model.items;

import app.Game;
import javafx.scene.image.Image;

public class Leaf extends Item {
    public Leaf(Image image) {
        super(image, "Веселый листик", "Можно предложить\nПоехавшему пню");
    }

    @Override
    public void doEffect(Game game, int number) {
        game.useOfferableItem(this,number);
    }
}
