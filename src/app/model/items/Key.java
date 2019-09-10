package app.model.items;

import app.Game;
import javafx.scene.image.Image;

public class Key extends Item {
    public Key(Image image) {
        super(image, "Ключ", "Открывает двери\n(Нажимать USE рядом\nс дверью)");
    }

    @Override
    public void doEffect(Game game,int number) {
        game.openDoor(number);
    }
}
