package app.model.items;

import app.Game;
import javafx.scene.image.Image;

public class FirstAidKit extends Item {

    public FirstAidKit(Image image) {
        super(image, "Сердечко", "Повышает здоровье");
    }

    @Override
    public void doEffect(Game game,int number) {
        game.increaseHealth(25,number);
    }
}
