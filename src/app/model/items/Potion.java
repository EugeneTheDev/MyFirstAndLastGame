package app.model.items;

import app.Game;
import javafx.scene.image.Image;

public class Potion extends Item {
    public Potion(Image image) {
        super(image, "Глазные капли","Можно предложить\nГлазастику");
    }

    @Override
    public void doEffect(Game game, int number) {
        game.useOfferableItem(this,number);
    }
}
