package app.model.objects;

import app.model.items.Item;
import javafx.scene.image.ImageView;

public class Chest extends GameObject {
    private Item item;

    public Chest(double x, double y,Item item,ImageView imageView) {
        super(x, y, imageView);
        this.item=item;
        getChildren().add(this.imageView);
    }
    public Item pickUpItem(){
        Item data=item;
        item=null;
        return data;
    }

    public Item getItem() {
        return item;
    }
}
