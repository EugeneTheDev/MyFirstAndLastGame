package app.model.items;

import app.Game;
import javafx.scene.image.Image;

public class Meat extends Item {
    public Meat(Image image) {
        super(image, "Мясо", "Можно предложить\nГигантскому скелету\nи Голубой жиже");
    }

    @Override
    public void doEffect(Game game, int number) {
        game.useOfferableItem(this,number);
    }
}
