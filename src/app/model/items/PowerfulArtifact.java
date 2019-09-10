package app.model.items;

import app.Game;
import javafx.scene.image.Image;

public class PowerfulArtifact extends Item {
    public PowerfulArtifact(Image image) {
        super(image, "Что-то", "Не знаю, что это, но\nвыглядит как мощный\nартефакт");
    }

    @Override
    public void doEffect(Game game, int number) {
        game.useOfferableItem(this,number);
    }
}
