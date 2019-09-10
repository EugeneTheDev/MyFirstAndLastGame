package app.model.items;

import app.Game;
import javafx.scene.image.Image;

public class Gold extends Item {
    public Gold(Image image) {
        super(image, "Золото", "Можно предложить\nГигантскому скелету");
    }

    @Override
    public void doEffect(Game game, int number) {
        game.useOfferableItem(this,number);
    }
}
