package app.model.objects;

import javafx.scene.image.ImageView;

public class Sign extends GameObject {
    private String id,text;
    public Sign(double x, double y,String id, ImageView imageView) {
        super(x, y, imageView);
        this.id=id;
        getChildren().add(this.imageView);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public boolean checkId(String id){
        return this.id.equals(id);
    }
}
