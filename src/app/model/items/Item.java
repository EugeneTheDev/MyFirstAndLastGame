package app.model.items;

import app.Game;
import javafx.scene.image.Image;

public abstract class Item {
    private Image image;
    private String name,description;

    public Item(Image image,String name,String description) {
        this.image = image;
        this.name=name;
        this.description=description;
    }
    public abstract void doEffect(Game game,int number);

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
